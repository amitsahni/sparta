package android.base.events;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.annotation.NonNull;

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
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        super.observe(owner, observer);
    }
}
