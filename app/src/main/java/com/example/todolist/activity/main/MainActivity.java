package com.example.todolist.activity.main;

import android.content.Intent;
import android.os.Bundle;

import com.example.todolist.R;
import com.example.todolist.activity.editor.SaveNotes;
import com.example.todolist.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;
//
public class MainActivity extends AppCompatActivity implements MainView{
    private final int EDIT = 1;
    private final int ADD = 2;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    MainPresenter mainPresenter;
    Adapter adapter;
    Adapter.ItemClickListener itemClickListener;
    List<Note> note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Toolbar toolbar = findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);

        swipeRefreshLayout = findViewById(R.id.swipe);
        recyclerView = findViewById(R.id.recycler_view_id);
        recyclerView.setLayoutManager(new LinearLayoutManager((this)));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
               Log.d("Check : ","1");
                Intent intent = new Intent(MainActivity.this, SaveNotes.class);
                 startActivityForResult(intent,ADD);
                Log.d("Check : ","2");
            }
        });
        mainPresenter = new MainPresenter(this);
        mainPresenter.getData();
        swipeRefreshLayout.setOnRefreshListener(
                () -> mainPresenter.getData()
        );
        itemClickListener = ((view, position) ->{
            //on Click
            int id = note.get(position).getId();
            String t = note.get(position).getTitle();
            String b = note.get(position).getBody();
            int c = note.get(position).getColor();
            Intent intent = new Intent(this,SaveNotes.class);
            intent.putExtra("title",t);
            intent.putExtra("body",b);
            intent.putExtra("color",c);
            intent.putExtra("id",id);
           // Toast.makeText(this,t,Toast.LENGTH_LONG).show();
            startActivityForResult(intent,EDIT);
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD && resultCode==RESULT_OK){
            mainPresenter.getData();
        }
        else if(requestCode == EDIT && resultCode==RESULT_OK){
            mainPresenter.getData();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);

    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void GetResult(List<Note> notes) {

        adapter = new Adapter(notes, this,itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        note = notes;

    }

    @Override
    public void ErrorLoading(String mssg) {
        Toast.makeText(this,mssg,Toast.LENGTH_LONG).show();
    }
}