package spartan.com.basemodule;


import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

/**
 * Created by clickapps on 5/2/18.
 */

public class ServiceWithoutO extends JobService {

    @Override
    public boolean onStartJob(JobParameters job) {
       Log.i(ServiceWithoutO.class.getName(), "onStartJob");
        jobFinished(job,true);
        return false; // Answers the question: "Is there still work going on?"
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        Log.i(ServiceWithoutO.class.getName(), "onStopJob");
        return false; //"Should this job be retried?"
    }


}
