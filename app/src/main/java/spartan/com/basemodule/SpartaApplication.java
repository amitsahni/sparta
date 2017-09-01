package spartan.com.basemodule;

import android.base.application.BaseApplication;
import android.content.Context;
import android.support.multidex.MultiDex;


/**
 * Created by clickapps on 28/2/17.
 */

public class SpartaApplication extends BaseApplication {


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
