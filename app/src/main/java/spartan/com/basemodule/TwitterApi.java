package spartan.com.basemodule;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by clickapps on 29/6/17.
 */

public interface TwitterApi {

    @GET("search/tweets.json")
    Observable<Response<Object>> searchTweets(@Query("q") String query);

    @GET("trends/place.json")
    Observable<Response<List<Object>>> getTrends(@Query("id") String placeId);
}