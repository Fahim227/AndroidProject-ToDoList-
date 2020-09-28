package com.example.todolist.activity.editor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todolist.R;
import com.example.todolist.api.JsonplaceHolderApi;
import com.example.todolist.model.Note;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thebluealliance.spectrum.SpectrumPalette;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SaveNotes extends AppCompatActivity implements EditorView{
    private TextView textView;
    private EditText body,title;
    private Button submit;
    ProgressDialog progressDialog;
    EditorPresenter presenter;
    private SpectrumPalette palette;
    int color, id;
    String t,n;

    Menu actionMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_notes);

       // textView = findViewById(R.id.bodyID);
        title = findViewById(R.id.titleID);
        body = findViewById(R.id.bodyID);
        palette = findViewById(R.id.palette);




        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        palette.setOnColorSelectedListener(
                clr -> color = clr
        );
        palette.setSelectedColor(getResources().getColor(R.color.white));
        color = getResources().getColor(R.color.white);

        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        t = intent.getStringExtra("title");
        n = intent.getStringExtra("body");
        color = intent.getIntExtra("color",getResources().getColor(R.color.white));

        presenter = new EditorPresenter(this);

        setDataFromIntent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sample_menu, menu);
        actionMenu = menu;

        if(id!=0){
            actionMenu.findItem(R.id.Edit).setVisible(true);
            actionMenu.findItem(R.id.save).setVisible(false);
            actionMenu.findItem(R.id.delete).setVisible(true);
            actionMenu.findItem(R.id.update).setVisible(false);
        }
        else{
            actionMenu.findItem(R.id.Edit).setVisible(false);
            actionMenu.findItem(R.id.save).setVisible(true);
            actionMenu.findItem(R.id.delete).setVisible(false);
            actionMenu.findItem(R.id.update).setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String t = title.getText().toString();
        String b = body.getText().toString();
        int color = this.color;
        if(item.getItemId() == R.id.save){
            //Save Notes

             presenter.saveNotes(t,b,color);
            return true;
        }
        if(item.getItemId() == R.id.delete){

            presenter.deleteNotes(id);

        }
        if(item.getItemId() == R.id.Edit){

            edit();
            actionMenu.findItem(R.id.Edit).setVisible(false);
            actionMenu.findItem(R.id.save).setVisible(false);
            actionMenu.findItem(R.id.delete).setVisible(false);
            actionMenu.findItem(R.id.update).setVisible(true);


        }
        if(item.getItemId() == R.id.update){

            presenter.updateNotes(id,t,n,color);

        }

        return super.onOptionsItemSelected(item);

    }


    @Override
    public void showProgess() {

       progressDialog.show();

    }

    @Override
    public void hideProgress() {

        progressDialog.hide();
        finish();

    }

    @Override
    public void onAddSuccess(String message) {

        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onAddError(String message) {

        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

    }

    private void setDataFromIntent() {

        if(id!=0){
            title.setText(t);
            body.setText(n);
            palette.setSelectedColor(color);

            getSupportActionBar().setTitle("Update Note..");
            read();
        }
        else{
            palette.setSelectedColor(getResources().getColor(R.color.white));
            color = getResources().getColor(R.color.white);
            edit();
        }
    }

    private void edit() {
        title.setFocusableInTouchMode(true);
        body.setFocusableInTouchMode(true);
        palette.setEnabled(true);
    }

    private void read() {
        title.setFocusableInTouchMode(false);
        body.setFocusableInTouchMode(false);
        title.setFocusable(false);
        body.setFocusable(false);
        palette.setEnabled(false);

    }

}