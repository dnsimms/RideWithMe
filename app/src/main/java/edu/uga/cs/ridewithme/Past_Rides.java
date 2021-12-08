package edu.uga.cs.ridewithme;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

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

import java.util.ArrayList;

public class Past_Rides extends AppCompatActivity {
    private ArrayList<String> pastTitles = new ArrayList<>();
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference fireBase = db.getReference("posts");
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.past_rides);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView pointsTotal = findViewById(R.id.pointTotal);
        String pointsLabel = "Points Total: ";
        pointsTotal.setText(pointsLabel + Track_Points.getInstance(getApplicationContext()).getPoints());

        fireBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot != null) {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        for(DataSnapshot keyData : data.getChildren()){
                            String key = keyData.getKey();
                            String acceptedUsername = "" + keyData.child("Accepted By").getValue();
                            String currentUsername = "" + user.getDisplayName();
                            String accepted = "" + keyData.child("Is Accepted").getValue();
                            String confirmed = "" + keyData.child("Is Confirmed").getValue();
                            if((accepted.equalsIgnoreCase("true")) && currentUsername.equals(acceptedUsername)){
                                if(confirmed.equalsIgnoreCase("false")){
                                    String state = "" + keyData.child("Depart State").getValue();
                                    String city = "" + keyData.child("Depart City").getValue();
                                    String userType = "" + keyData.child("User Type").getValue();
                                    String aState = "" + keyData.child("Arrival State").getValue();
                                    String aCity = "" + keyData.child("Arrival City").getValue();
                                    String date = "" + keyData.child("Date").getValue();
                                    String title = city + ", " + state + " -> " + aCity + ", " + aState +
                                            " on " + date + " | " + userType;
                                    listPosts(title);
                                }
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


        //TODO if the user clicks the button, change their Is Confirmed value to "true"
        //TODO the post should disappear and riderpoints are appropriately allocated

    }

    private void listPosts(String ps){
        pastTitles.add(ps);
    }

    private void createRecycleViewer(){
        RecyclerView rView = findViewById(R.id.recycleView2);
        PastRidesRecycleView adapter = new PastRidesRecycleView(pastTitles, this);
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

        if(item.getItemId() == R.id.signOut){
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(Past_Rides.this, MainActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.past_rides){
            Intent intent = new Intent(Past_Rides.this, Past_Rides.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.create_post){
            Intent intent = new Intent(Past_Rides.this, CreateListing.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }



}
