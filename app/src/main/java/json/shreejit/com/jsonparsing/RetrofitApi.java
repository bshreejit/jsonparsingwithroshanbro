package json.shreejit.com.jsonparsing;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApi {
    @GET("api.php")
    Call<List<MenuModelClass>> getMenu();


}
