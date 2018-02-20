package spartan.com.basemodule;

import android.app.Application;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;

/**
 * Created by clickapps on 5/2/18.
 */

public class AppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
        Job myJob = dispatcher.newJobBuilder()
                .setService(ServiceWithoutO.class) // the JobService that will be called
                .setTag(ServiceWithoutO.class.getName())        // uniquely identifies the job
                .build();
        dispatcher.mustSchedule(myJob);
    }
}
