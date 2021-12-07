package edu.uga.cs.ridewithme;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    private int position = 0, counter = 0, pid = 1;
    private boolean viewing = false;
    private ArrayList<String> postTitles = new ArrayList<>();
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference fireBase = db.getReference("posts");
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        //String[] postContents = new String[20];
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fireBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot != null) {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        for(DataSnapshot keyData : data.getChildren()){
                            String key = keyData.getKey();
                            String username = "" + snapshot.getKey();
                            String accepted = "" + keyData.child("Is Accepted").getValue();
                            if(!(accepted.equalsIgnoreCase("true"))){
                                String state = "" + keyData.child("Depart State").getValue();
                                String city = "" + keyData.child("Depart City").getValue();
                                String userType = "" + keyData.child("User Type").getValue();
                                String aState = "" + keyData.child("Arrival State").getValue();
                                String aCity = "" + keyData.child("Arrival City").getValue();
                                String date = "" + keyData.child("Date").getValue();
                                String title = city + ", " + state + " -> " + aCity + ", " + aState +
                                        " on " + date + " | " + userType;
                                namePosts(title);
                                ++counter;
                                ++pid;
                            }
                        }

                    }
                    createRecycleViewer();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }



    private void namePosts(String ps){
        postTitles.add(ps);
    }

    private void createRecycleViewer(){
        RecyclerView rView = findViewById(R.id.recycleView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(postTitles, this);
        rView.setAdapter(adapter);
        rView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.buttons, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //for toolbar, implement later
        return super.onOptionsItemSelected(item);
    }
}
