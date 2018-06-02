package json.shreejit.com.jsonparsing;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        progressDialog=new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        getMenuJson();
    }

    public void getMenuJson() {
        RetrofitApi retrofitApi = RetrofitClient.getRetrofit().create(RetrofitApi.class);
        Call<List<MenuModelClass>> menuModelClassCall = retrofitApi.getMenu();
        menuModelClassCall.enqueue(new Callback<List<MenuModelClass>>() {
            @Override
            public void onResponse(Call<List<MenuModelClass>> call, Response<List<MenuModelClass>> response) {

                //first way
                List<MenuModelClass> menuModelClasses = response.body();
                RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, response.body());
                /*second way
                    RecyclerViewAdapter recyclerViewAdapter=new RecyclerViewAdapter(MainActivity.this,response.body());
                */

                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 2);
                /*LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(horizontalLayoutManager);*/
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(recyclerViewAdapter);
                recyclerViewAdapter.notifyDataSetChanged();


                if(progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<MenuModelClass>> call, Throwable t) {

            }
        });
    }
}