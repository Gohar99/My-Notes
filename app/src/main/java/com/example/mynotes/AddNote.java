  package com.example.mynotes;

import androidx.annotation.IntDef;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynotes.model.Notice;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;


public class AddNote extends AppCompatActivity {

    TextView T_title;
    EditText edit_title;
    EditText edit_note;
   // EditText edit_password;
  //  TextView data;
    Button save;
    //Button btn;
    Button open;

  //  Button update;
    // EditText text;
    Realm realm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);
        T_title = (TextView) findViewById(R.id.title);
        edit_title = (EditText) findViewById(R.id.edit);
        edit_note = (EditText) findViewById(R.id.edit1);
     //  edit_password = (EditText) findViewById(R.id.password);
        //   text = (EditText) findViewById(R.id.edit2);
    //    data = (TextView) findViewById(R.id.abc);
        save = (Button) findViewById(R.id.button);
    //  update =(Button) findViewById(R.id.update;
        //  btn = (Button) findViewById(R.id.button1);
       // delete =(Button)  findViewById(R.id.button2);
        Realm.init(this);
        realm = Realm.getDefaultInstance();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if (edit_title.getText().toString().matches("")) {

                        edit_title.requestFocus();
                        Snackbar.make(view, "Please enter title", Snackbar.LENGTH_SHORT)
                                .show();
                    }
                    else if (edit_note.getText().toString().length() < 6) {
                        Snackbar.make(view, "title must be atleast 5 characters", Snackbar.LENGTH_SHORT)
                                .show();
                    }
                else if (edit_note.getText().toString().matches("")) {
                    edit_note.requestFocus();
                    Snackbar.make(view, "Please enter note", Snackbar.LENGTH_SHORT)
                            .show();
                } else if (edit_note.getText().toString().length() < 6) {
                    Snackbar.make(view, "Note must be atleast 6 characters", Snackbar.LENGTH_SHORT)
                            .show();
                }

                else {
                    //for insertion
                    realm.beginTransaction();
                    Notice notice = realm.createObject(Notice.class);
                    notice.setTitle(edit_title.getText().toString());
                    notice.setNote(edit_note.getText().toString());
                   // notice.setPassword(edit_password.getText().toString());
                    realm.commitTransaction();
                     //realm.close();
                    Log.d("database ", "data inserted");
                 //for deletion data in database

                        realm.beginTransaction();

                }
                Log.d("abc", "data saved");
                //class name notice
                RealmResults<Notice> realmResults = realm.where(Notice.class).findAll();
                Log.i("data",realmResults.toString());
                //Toast.makeText(AddNote.this, realmResults.asJSON(), Toast.LENGTH_SHORT).show();
                Log.i("JSON","Realm JSON "+ realmResults.asJSON());
                for (Notice notice : realmResults) {
                 //to show save data in Textview  the code is
                   //data.setText("");
                    //data.append(notice.getTitle() + "\n" + notice.getNote());
                }

          //    Intent intent = new Intent(AddNote.this, MainActivity.class);
            //  startActivity(intent);


                }

        });

        }
    }