package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mynotes.model.Notice;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    Adapter adapter;
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    //object name   notes
    List<Notice> notes = new ArrayList<Notice>();
    //Button delete;
    Realm realm;
    //Realm realm;
   // TextView edit_title;
    //TextView edit_note;
   // Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(this);
        realm = Realm.getDefaultInstance();
//function call of get-all-notes with class name
        notes=getAllNotes();
     //   save= findViewById(R.id.button);
      //  edit_title= (TextView) findViewById(R.id.edit);
       // edit_note= (TextView) findViewById(R.id.edit1);
        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNote.class);
                startActivity(intent);
            }


        });
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(MainActivity.this, notes);
        recyclerView.setAdapter(adapter);

    }
    //notice is schema of realm and notes is the adapter model class
    //create get all function in main activity
    public List<Notice> getAllNotes()
    {
        RealmResults<Notice> realmResults = realm.where(Notice.class).findAll();
        List<Notice> allNotes = realm.copyFromRealm(realmResults);
        return allNotes;
    }
}




