package spartan.com.basemodule;

import android.app.Application;
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
