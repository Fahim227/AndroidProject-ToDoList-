package com.example.todolist.api;

import com.example.todolist.model.Note;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonplaceHolderApi {

   /* @GET("android")
    Call<List<Post>> getPost();

    @FormUrlEncoded
    @POST("savePost.php")
    Call<NewsFeed> savePost(
            @Field("body") String p,
            @Field("user_id") int i
    );*/

    @FormUrlEncoded
    @POST("saveNote.php")
    Call<Note> saveNote(
            @Field("title") String t,
            @Field("body") String b,
            @Field("color") int color
    );
    @GET("allNotes.php")
    Call<List<Note>> getNotes();

    @FormUrlEncoded
    @POST("updateNotes.php")
    Call<Note> updateNotes(
            @Field("id") int id,
            @Field("title") String t,
            @Field("body") String b,
            @Field("color") int color
    );
    @FormUrlEncoded
    @POST("deleteNotes.php")
    Call<Note> deleteNotes(
            @Field("id") int id
    );
}
