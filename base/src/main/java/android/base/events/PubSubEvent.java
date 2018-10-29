package android.base.events;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;

public class PubSubEvent<T> extends MutableLiveData<T> {
    private static volatile PubSubEvent instance;

    private PubSubEvent() {

    }

    public static <T> PubSubEvent<T> getInstance() {
        if (instance == null) {
            synchronized (PubSubEvent.class) {
                if (instance == null) {
                    instance = new PubSubEvent<T>();
                }
            }
        }
        return instance;
    }

    @Deprecated
    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<T> observer) {
        super.observe(owner, observer);
    }
}
