package com.example.todolist.activity.main;

import com.example.todolist.model.Note;

import java.util.List;

public interface MainView {

    void showLoading();
    void hideLoading();
    void GetResult(List<Note> notes);
    void ErrorLoading(String mssg);


}
