package com.example.mynotes;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotes.model.Notice;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    private Context context;
   // private AddNote AddNote;
    private List<Notice> data;
    String edit_title;
    Realm realm;
    public Adapter(Context context, List<Notice> data) {
        this.context = context;
        this.data = data;
        Realm.init(context);
        realm = Realm.getDefaultInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater=LayoutInflater.from(context);
        view=inflater.inflate(R.layout.row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





             //click on single item and  pass data to next activity

                Log.i("view", "you clicked the view");
                Intent intent=new Intent(v.getContext(), NoteDetail.class);
                intent.putExtra("title",data.get(position).getTitle());
                intent.putExtra("note",data.get(position).getNote());
                v.getContext().startActivity(intent);
              //  Toast.makeText(context,"yeah",Toast.LENGTH_LONG).show();

            }
        });
        holder.title.setText(data.get(position).getTitle());
       // holder.note.setText(data.get(position).getNote());

     //for delete button click

      holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               // Toast.makeText(context, "data updated", Toast.LENGTH_SHORT).show();

                //use intent  put extra keyword to transfer data from one activity to another activity..
                Intent intent = new Intent(context, UpdateNote.class);
               intent.putExtra("title",data.get(position).getTitle());
               intent.putExtra("note",data.get(position).getNote());
             context.startActivity(intent);
                //  intent.putExtra("password",data.get(position).getPassword());

            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Adapter.this.context);
                builder.setMessage("Are you sure to Delete this Note ?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        deleteNote(data.get(position).getTitle());
                        data.remove(position);
                        notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    public void deleteNote(final String title)
    {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Notice> result=realm.where(Notice.class)
                        .equalTo("title",title).findAll();
                result.deleteAllFromRealm();

            }
        });
    }
}

/**
 * A Simple ViewHolder for the RecyclerView
 */
class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

    TextView title;
    //   TextView note;
    // Button button;
    Button update;
    Button delete;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setTag(this);
        itemView.setOnClickListener(this);

        title = itemView.findViewById(R.id.title);
      //   itemView.setClickable(true);
        //     note =itemView.findViewById(R.id.note);
        //button=itemView.findViewById(R.id.button);
      update = itemView.findViewById(R.id.update);
      delete = itemView.findViewById(R.id.delete);
    }

            @Override

            public void onClick(View v) {

            }
        }