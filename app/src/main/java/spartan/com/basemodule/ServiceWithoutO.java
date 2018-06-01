package spartan.com.basemodule;


import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.firebase.jobdispatcher.SimpleJobService;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by clickapps on 5/2/18.
 */

public class ServiceWithoutO extends SimpleJobService {

//    @Override
//    public boolean onStartJob(JobParameters job) {
//        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
//        Log.i(ServiceWithoutO.class.getName(), "onStartJob = " + currentDateTimeString);
//        // jobFinished(job, true);
////        if (getApplication() instanceof AppApplication) {
////            ((AppApplication) getApplication()).runJobService();
////        }
//        jobFinished(job, job.isRecurring());
//        return true; // Answers the question: "Is there still work going on?"
//    }
//
//    @Override
//    public boolean onStopJob(JobParameters job) {
//        Log.i(ServiceWithoutO.class.getName(), "onStopJob");
//        return false; //"Should this job be retried?"
//    }

    @Override
    public int onRunJob(JobParameters job) {
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        Log.i(ServiceWithoutO.class.getName(), "onStartJob = " + currentDateTimeString);
        return job.getTag().hashCode();
    }


}
