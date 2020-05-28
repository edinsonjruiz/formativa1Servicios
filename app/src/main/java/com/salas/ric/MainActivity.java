package com.salas.ric;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ListView list;
    ArrayList<String> titles = new ArrayList<>();
    ArrayAdapter arr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arr = new ArrayAdapter(this,android.R.layout.simple_list_item_1,titles);
        list = findViewById(R.id.list);

        list.setAdapter(arr);
        get();
    }

    private void get() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Serv serv = retrofit.create(Serv.class);
        Call< List<POST> > call = serv.getPost();

        call.enqueue(new Callback<List<POST>>() {
            @Override
            public void onResponse(Call<List<POST>> call, Response<List<POST>> response) {
                for(POST x : response.body()) {
                    titles.add("Titulo: " + x.getTitle() + "\n" + "Cuerpo: " + x.getBody());
                }
                arr.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<POST>> call, Throwable t) {
            }
        });
    }
}
