package spartan.com.basemodule;

import android.base.activity.BaseAppCompatActivity;
import android.base.http.Builder;
import android.base.http.RetrofitManager;
import android.base.http.WebConnect;
import android.base.http.WebParam;
import android.base.util.ApplicationUtils;

import com.github.privacystreams.communication.Contact;
import com.github.privacystreams.core.UQI;
import com.github.privacystreams.core.exceptions.PSException;
import com.github.privacystreams.core.purposes.Purpose;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends BaseAppCompatActivity {

    @Override
    protected void initUI() {
        ApplicationUtils.Log.d("Date = ", ApplicationUtils.Date.convertLocalDateToUTC(new Date().getTime()).toString());
        try {
            List<List<String>> contactEmails = new UQI(this)
                    .getData(Contact.getAll(), Purpose.SOCIAL("recommend friends"))
                    .asList(Contact.EMAILS);
        } catch (PSException e) {
            e.printStackTrace();
        }
        Builder builder = WebConnect.with(this,"").callback(this);
        Observable<Response<Object>> observable = builder.connect(TwitterApi.class)
                .searchTweets("d");
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RetrofitManager.CallBack<>(builder.getWebParam()));
    }


}
