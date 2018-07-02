package apps.dabinu.com.secureafrica.networkutils;

import org.json.JSONObject;

import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Single;

public interface SecureAfricaService {
    @POST("alert/app_add")
    @FormUrlEncoded
    Single<SecureAfricaBase<JSONObject>> logEmergency(@Field("type") String type, @Field("long_lat") String longLat);
}
