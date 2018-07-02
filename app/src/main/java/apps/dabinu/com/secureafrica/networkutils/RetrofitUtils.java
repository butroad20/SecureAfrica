package apps.dabinu.com.secureafrica.networkutils;

import apps.dabinu.com.secureafrica.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    private static Retrofit INSTANCE;

    public static Retrofit getRetrofitInstance(){
        if (INSTANCE == null){
            INSTANCE = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }

        return INSTANCE;
    }

}
