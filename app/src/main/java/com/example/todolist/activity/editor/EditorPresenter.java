package com.example.todolist.activity.editor;

import android.util.Log;
import android.widget.Toast;

import com.example.todolist.api.JsonplaceHolderApi;
import com.example.todolist.model.Note;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditorPresenter {

    private EditorView editorView;
    private JsonplaceHolderApi jsonplaceHolderApi;

    public EditorPresenter(EditorView view){
        this.editorView = view;
    }
    void saveNotes(final String t,final String b,final int color){

        //editorView.showProgess();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.103/android/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonplaceHolderApi = retrofit.create(JsonplaceHolderApi.class);



        Call<Note> call = jsonplaceHolderApi.saveNote(t,b,color);

        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
                 editorView.hideProgress();

                Log.d("Check: ","respones");

                if(response.isSuccessful() && response.body() != null){
                    Boolean success = response.body().getSuccess();
                    if(success){
                        Log.d("Check: ","Success");
                       editorView.onAddSuccess(response.body().getMessage());

                    }
                    else{
                        Log.d("Check: ","not success");
                        editorView.onAddError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {
                Log.d("Check: ","not respones");
               editorView.onAddError(t.getLocalizedMessage());

            }
        });

    }
    void updateNotes(int id,String title,String body,int color){
        editorView.showProgess();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.103/android/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonplaceHolderApi = retrofit.create(JsonplaceHolderApi.class);



        Call<Note> call = jsonplaceHolderApi.updateNotes(id,title,body,color);

        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
                editorView.hideProgress();
                if(response.isSuccessful() && response.body() != null){
                    Boolean success = response.body().getSuccess();
                    if(success){
                        Log.d("Check: ","Success");
                        editorView.onAddSuccess(response.body().getMessage());

                    }
                    else{
                        Log.d("Check: ","not success");
                        editorView.onAddError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {
                Log.d("Check: ","not respones");
                editorView.onAddError(t.getLocalizedMessage());

            }
        });
    }
    void deleteNotes(int id){
        editorView.showProgess();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.103/android/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonplaceHolderApi = retrofit.create(JsonplaceHolderApi.class);



        Call<Note> call = jsonplaceHolderApi.deleteNotes(id);

        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
                editorView.hideProgress();
                Boolean success = response.body().getSuccess();
                if(success){
                    Log.d("Check: ","Success");
                    editorView.onAddSuccess(response.body().getMessage());

                }
                else{
                    Log.d("Check: ","not success");
                    editorView.onAddError(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {
                Log.d("Check: ","not respones");
                editorView.onAddError(t.getLocalizedMessage());

            }
        });
    }
}
