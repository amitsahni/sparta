package android.base.pubnub;

import android.base.util.ApplicationUtils;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.models.consumer.PNPublishResult;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;

import java.text.MessageFormat;
import java.util.Arrays;


/**
 * The type Pub nub.
 */
public class Pubnub {
    private static volatile PubNub sPubnub;

    private Pubnub() {

    }


    /**
     * Instantiates a new Pub nub.
     *
     * @param pubNubParam the pub nub param
     */
    public Pubnub(PubNubParam pubNubParam) {
        if (sPubnub == null) {
            synchronized (PubNubManager.class) {
                if (sPubnub == null) {
                    PNConfiguration pnConfiguration = new PNConfiguration();
                    pnConfiguration.setSubscribeKey(pubNubParam.subscribe_key);
                    pnConfiguration.setPublishKey(pubNubParam.publish_key);
                    pnConfiguration.setSecure(false);
                    sPubnub = new com.pubnub.api.PubNub(pnConfiguration);
                    sPubnub.addListener(new PubNubCallback(pubNubParam));
                }
            }
        }


    }

    /**
     * Gets pub nub.
     *
     * @return the pub nub
     */
    public com.pubnub.api.PubNub getPubNub() {
        return sPubnub;
    }

    /**
     * Handle event.
     *
     * @param pubNubParam the pub nub param
     */
    public Object handleEvent(PubNubParam pubNubParam) {
        switch (pubNubParam.event) {
            case SUB:
                sPubnub.subscribe().channels(Arrays.asList(pubNubParam.channels)).execute();
                break;
            case UNSUB:
                sPubnub.unsubscribe().channels(Arrays.asList(pubNubParam.channels)).execute();
                ApplicationUtils.Log.i("All SubScribed:" + sPubnub.getSubscribedChannels().toString());
                break;
            case UNSUBALL:
                sPubnub.unsubscribeAll();
                break;
            case SUB_LIST:
                return sPubnub.getSubscribedChannels();
            case PUB:
                sPubnub.publish().message(pubNubParam.message).channel(pubNubParam.channels[0]).async(new PNCallback<PNPublishResult>() {
                    @Override
                    public void onResponse(PNPublishResult result, PNStatus status) {
                        if (!status.isError()) {
                            ApplicationUtils.Log.i(Pubnub.this.getClass().getSimpleName() + ": " + status.toString());
                        } else {
                            ApplicationUtils.Log.e(Pubnub.this.getClass().getSimpleName() + ": Error = " + status.isError());
                        }
                    }
                });
                break;
            default:
                break;
        }
        return null;
    }

    private class PubNubCallback extends SubscribeCallback {
        private PubNubParam pubNubParam;
        private static final String FORMAT = "Channel : {0}\nMessage = {1}";

        private PubNubCallback(PubNubParam param) {
            this.pubNubParam = param;
        }

        @Override
        public void status(com.pubnub.api.PubNub pubnub, PNStatus status) {
//            ApplicationUtils.Log.i(MessageFormat.format(FORMAT, status.getStatusCode(), status.getErrorData().getInformation()));
            // the status object returned is always related to subscribe but could contain
            // information about subscribe, heartbeat, or errors
            // use the operationType to switch on different options
            switch (status.getOperation()) {
                // let's combine unsubscribe and subscribe handling for ease of use
                case PNSubscribeOperation:
                case PNUnsubscribeOperation:
                    // note: subscribe statuses never have traditional
                    // errors, they just have categories to represent the
                    // different issues or successes that occur as part of subscribe
                    switch (status.getCategory()) {
                        case PNConnectedCategory:
                            // this is expected for a subscribe, this means there is no error or issue whatsoever
                            ApplicationUtils.Log.i("PNConnectedCategory = " + status.getAffectedChannels());
                            break;
                        case PNReconnectedCategory:
                            // this usually occurs if subscribe temporarily fails but reconnects. This means
                            // there was an error but there is no longer any issue
                            ApplicationUtils.Log.i("PNReconnectedCategory = " + status.getAffectedChannels());
                            break;
                        case PNDisconnectedCategory:
                            // this is the expected category for an unsubscribe. This means there
                            // was no error in unsubscribing from everything
                            ApplicationUtils.Log.i("PNDisconnectedCategory = " + status.getAffectedChannels());
                            break;
                        case PNUnexpectedDisconnectCategory:
                            // this is usually an issue with the internet connection, this is an error, handle appropriately
                            break;
                        case PNAccessDeniedCategory:
                            // this means that PAM does allow this client to subscribe to this
                            // channel and channel group configuration. This is another explicit error
                            break;
                        case PNAcknowledgmentCategory:
                            ApplicationUtils.Log.i("PNAcknowledgmentCategory = " + status.getAffectedChannels());
                            break;
                        default:
                            // More errors can be directly specified by creating explicit cases for other
                            // error categories of `PNStatusCategory` such as `PNTimeoutCategory` or `PNMalformedFilterExpressionCategory` or `PNDecryptionErrorCategory`
                            ApplicationUtils.Log.i("default = " + status.getAffectedChannels());
                            break;
                    }
                    break;
                case PNHeartbeatOperation:
                    // heartbeat operations can in fact have errors, so it is important to check first for an error.
                    // For more information on how to configure heartbeat notifications through the status
                    // PNObjectEventListener callback, consult <link to the PNCONFIGURATION heartbeart config>
                    if (status.isError()) {
                        // There was an error with the heartbeat operation, handle here
                    } else {
                        // heartbeat operation was successful
                    }
                    break;
                default: {
                    // Encountered unknown status type
                }
            }
        }

        @Override
        public void message(com.pubnub.api.PubNub pubnub, PNMessageResult message) {
            ApplicationUtils.Log.i("Message = " + MessageFormat.format(FORMAT, message.getChannel(), message.getMessage().toString()));
            if (pubNubParam.listener != null
                    && message != null) {
                pubNubParam.listener.onSuccess(message.getChannel(), message.getMessage());
            }
            if (pubNubParam.context != null) {

                pubNubParam.context.sendBroadcast(
                        new Intent(PubNubConstant.BROADCAST)
                                .putExtra(PubNubConstant.BUNDLE_MESSAGE, message.getMessage().toString())
                                .putExtra(PubNubConstant.BUNDLE_CHANNEL, message.getChannel()));
                // Local Broadcast
                //send broadcast for application
                LocalBroadcastManager.getInstance(pubNubParam.context)
                        .sendBroadcast(new Intent(PubNubConstant.BROADCAST)
                                .putExtra(PubNubConstant.BUNDLE_MESSAGE, message.getMessage().toString())
                                .putExtra(PubNubConstant.BUNDLE_CHANNEL, message.getChannel()));

            }
            ApplicationUtils.Log.i("---------------------------------------------------------------------------");
        }

        @Override
        public void presence(com.pubnub.api.PubNub pubnub, PNPresenceEventResult presence) {
//            ApplicationUtils.Log.i(MessageFormat.format(FORMAT, presence.getChannel(), presence.getState().toString()));
        }
    }
}
