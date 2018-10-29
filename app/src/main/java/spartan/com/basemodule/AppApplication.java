package spartan.com.basemodule;

import android.arch.lifecycle.Observer;
import android.base.events.PubSubEvent;
import android.support.annotation.Nullable;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

/**
 * Created by clickapps on 5/2/18.
 */

public class AppApplication extends MultiDexApplication {
    FirebaseJobDispatcher dispatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        PubSubEvent<Boolean> pubSubEvent = PubSubEvent.getInstance();
        pubSubEvent.observeForever(new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                Log.i(AppApplication.class.getSimpleName(), "OnChanged = " + aBoolean);
            }
        });
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(AppApplication.class.getSimpleName(), "OnTerminate");
    }

    public FirebaseJobDispatcher getDispatcher() {
        if (dispatcher == null) {
            dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
        }
        return dispatcher;
    }

    public void runJobService() {

        getDispatcher().cancelAll();
        Job myJob = getDispatcher().newJobBuilder()
                .setService(ServiceWithoutO.class) // the JobService that will be called
                .setTag(ServiceWithoutO.class.getName())        // uniquely identifies the job
                .setTrigger(Trigger.executionWindow(0, 1))
                .setRetryStrategy(RetryStrategy.DEFAULT_LINEAR)
                .setLifetime(Lifetime.FOREVER)
//                .setRecurring(true)
                .build();

        getDispatcher().mustSchedule(myJob);
    }
}
