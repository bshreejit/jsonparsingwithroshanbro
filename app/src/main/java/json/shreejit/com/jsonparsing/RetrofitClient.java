package json.shreejit.com.jsonparsing;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static Retrofit retrofit= null;

    public static Retrofit getRetrofit(){
        if(retrofit==null){
         retrofit= new Retrofit.Builder().baseUrl("http://vedisapp.berlin-webdesign-agentur.de/").addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}
