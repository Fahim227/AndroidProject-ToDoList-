package com.example.todolist.activity.main;

import com.example.todolist.api.JsonplaceHolderApi;
import com.example.todolist.model.Note;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainPresenter {

    MainView mainView;
    JsonplaceHolderApi jsonplaceHolderApi;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
    }
    void getData(){
        mainView.showLoading();
        //get data from server;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.103/android/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonplaceHolderApi = retrofit.create(JsonplaceHolderApi.class);
        Call<List<Note>> call = jsonplaceHolderApi.getNotes();

        call.enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                mainView.hideLoading();
                if(response.isSuccessful() && response.body()!=null){

                    mainView.GetResult(response.body());

                }
            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {
                mainView.hideLoading();
                mainView.ErrorLoading(t.getLocalizedMessage());
            }
        });



    }
}
