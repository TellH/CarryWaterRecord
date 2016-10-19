package tellh.com.carrywaterrecord.net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tlh on 2016/10/18 :)
 */

public class NetWorkHelper {
    private static Api api;

    public static Api getClient() {
        if (api == null) {
            api = new Retrofit.Builder()
                    .baseUrl("http://182.254.233.29/carry-water-record/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(Api.class);
        }
        return api;
    }
}
