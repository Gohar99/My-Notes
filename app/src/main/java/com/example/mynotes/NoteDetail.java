package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mynotes.model.Notice;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class NoteDetail extends AppCompatActivity {

    TextView titleView,noteView ;  // passwordView;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //set content view for activity 3
        setContentView(R.layout.activity_3);
       //use intent to get data from the main activity

        Intent i=getIntent();

        String title=i.getExtras().getString("title");
        String note=i.getExtras().getString("note");
     //   String password=i.getExtras().getString("password");

       titleView=findViewById(R.id.title);
       noteView=findViewById(R.id.note);
      //  passwordView=findViewById(R.id.password);
        titleView.setText(title);
     noteView.setText(note);
      //  passwordView.setText(password);
    }
}