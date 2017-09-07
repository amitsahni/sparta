package spartan.com.basemodule;

import android.base.util.ApplicationUtils;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.privacystreams.communication.Contact;
import com.github.privacystreams.core.UQI;
import com.github.privacystreams.core.exceptions.PSException;
import com.github.privacystreams.core.purposes.Purpose;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApplicationUtils.Log.d("Date = ", ApplicationUtils.Date.convertLocalDateToUTC(new Date().getTime()).toString());
        try {
            List<List<String>> contactEmails = new UQI(this)
                    .getData(Contact.getAll(), Purpose.SOCIAL("recommend friends"))
                    .asList(Contact.EMAILS);
        } catch (PSException e) {
            e.printStackTrace();
        }
    }
}
