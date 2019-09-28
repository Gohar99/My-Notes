package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynotes.model.Notice;
import com.google.android.material.snackbar.Snackbar;

import io.realm.Realm;
import io.realm.RealmResults;

public class UpdateNote extends AppCompatActivity {
    TextView T_title;
    EditText edit_title;
    EditText edit_note;
    Button update;
    TextView titleView,noteView ;
    Realm realm;
    String title,note;

  //  Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);
        Realm.init(UpdateNote.this);
        realm = Realm.getDefaultInstance();
        T_title =(TextView) findViewById(R.id.title);
        edit_title =(EditText)  findViewById(R.id.edit);
        edit_note =(EditText)  findViewById(R.id.edit1);
        update    =(Button)   findViewById(R.id.update);
        titleView=findViewById(R.id.title);
        noteView=findViewById(R.id.note);
        Intent i=getIntent();
        title=i.getExtras().getString("title");
        note=i.getExtras().getString("note");
        edit_title.setText(title);
        edit_note.setText(note);



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edit_title.getText().toString().matches("")) {

                    edit_title.requestFocus();
                    Snackbar.make(view, "Please enter  title", Snackbar.LENGTH_SHORT)
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
                else
                {

                    Log.i("ye hai title",title);
                    Notice notice = realm.where(Notice.class)
                            .equalTo("title", title)
                            .findFirst();
                    realm.beginTransaction();
                    if (notice == null) {
                        Log.i("if",edit_title.getText().toString()+"  "+edit_note.getText().toString());
                        Notice notice1 = realm.createObject(Notice.class);
                        notice1.setTitle(edit_title.getText().toString());
                        notice1.setNote(edit_note.getText().toString());
                        realm.commitTransaction();
                    } else {
                        Log.i("else",edit_title.getText().toString()+"  "+edit_note.getText().toString());
                        notice.setTitle(edit_title.getText().toString());
                        notice.setNote(edit_note.getText().toString());
                        realm.commitTransaction();
                    }

                    Intent intent=new Intent(UpdateNote.this,MainActivity.class);
                    startActivity(intent);
                }


            }

        });
    }
    void update(String title,String note) {
        // This query is fast because "character" is an indexed field
        Notice notice = realm.where(Notice.class)
                .equalTo("title", title)
                .findFirst();
        realm.beginTransaction();
        if (notice == null) {
            Notice notice1 = realm.createObject(Notice.class);
            notice1.setTitle(title);
            notice1.setNote(note);
        } else {
            notice.setTitle(notice.getTitle() + 1);
        }
        realm.commitTransaction();
   }
}