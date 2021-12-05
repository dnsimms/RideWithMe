package edu.uga.cs.ridewithme;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostManagementActivity extends AppCompatActivity {
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference fireBase = db.getReference();

    private Button createButton, viewButton;

    //TODO: Implement Once Logged In here
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_page);

        //TODO: initialize the layout stuff & listeners
        //TODO to write things to the database do it is kind of similar to a list
        //TODO for example when a user creates a post you do:
        //TODO fireBase.child("posts").child(postID).setValue(POSTOBJECT);
    }

    //TODO you can make the time a spinner or a text box idc
    //TODO write a method to create a new post using the post class

    public class Posts{
        public String date, time, depart, arrival;

        public Posts(){
            //keep empty
        }

        public Posts(String date, String time, String depart, String arrival ){
            this.date = date;
            this.time = time;
            this.depart = depart;
            this.arrival = arrival;
        }
    }

}
