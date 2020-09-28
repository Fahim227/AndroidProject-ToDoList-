package com.example.todolist.activity.editor;

public interface EditorView {

    void showProgess();
    void hideProgress();
    void onAddSuccess(String message);
    void onAddError(String message);

}
