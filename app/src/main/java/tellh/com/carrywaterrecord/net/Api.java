package tellh.com.carrywaterrecord.net;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by tlh on 2016/10/18 :)
 */

public interface Api {
    @GET("members")
    Call<List<Member>> listAllMembers();

    @GET("records")
    Call<List<Record>> listRecords(@Query("page") int page, @Query("pageSize") int pageSize);

    @FormUrlEncoded
    @POST("records")
    Call<Message> addRecord(@Field("mid") int mid);

    @GET("member/turn")
    Call<Member> getNextTurn();
}
