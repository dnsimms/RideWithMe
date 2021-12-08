package edu.uga.cs.ridewithme;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Past_Details extends AppCompatActivity {

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference fireBase = db.getReference("posts");
    private int pos = 0, points = 0, postCounter = 0;
    private TextView userTitle2, departCity2, departState2,
            arrivalCity2, arrivalState2, dateBox2, timeBox2, riderPoints2;
    private Button acceptButton2;
    private String userType, username, dC, dS, aC,
            aS, date, time, rPoints;
    private boolean isFound = false;
    private Menu menu;
    private MenuItem pointsLog;


    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.past_details);
        Intent intent = getIntent();

        pos = intent.getIntExtra("position", 1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        points = Track_Points.getInstance(this).getPoints();


        userTitle2 = findViewById(R.id.userTitle2);
        departCity2 = findViewById(R.id.departCity2);
        departState2 = findViewById(R.id.departState2);
        arrivalCity2 = findViewById(R.id.arrivalCity2);
        arrivalState2 = findViewById(R.id.arrivalState2);
        dateBox2 = findViewById(R.id.dateBox2);
        timeBox2 = findViewById(R.id.timeBox2);
        riderPoints2 = findViewById(R.id.riderPoints2);
        acceptButton2 = findViewById(R.id.accept2);
        getPastDetails();

        acceptButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isFound = false;
                postCounter = 0;

                //this is how you read through the database
                fireBase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot != null){
                            for (DataSnapshot data : snapshot.getChildren()) {// TODO do data.getKey() to get the username of the poster
                                for(DataSnapshot keyData : data.getChildren()){
                                    String username = "" + data.getKey();
                                    String acceptedUsername = "" + keyData.child("Accepted By").getValue();
                                    String currentUsername = "" + user.getDisplayName();
                                    String accepted = "" + keyData.child("Is Accepted").getValue();
                                    String confirmed = "" + keyData.child("Is Confirmed").getValue();
                                    String postPoints = "" + keyData.child("Points").getValue();
                                    if((accepted.equalsIgnoreCase("true")) && currentUsername.equals(acceptedUsername)){
                                        if(confirmed.equalsIgnoreCase("false")){
                                            if((postCounter == pos) && !isFound){
                                                String location = keyData.getKey();
                                                fireBase.child(username).child(location).child("Is Confirmed").setValue("true");
                                                isFound = true;

                                                int pointP = Integer.parseInt(postPoints);
                                                points = points + pointP;
                                                Track_Points.getInstance(getApplicationContext()).setPoints(points);
                                            }
                                        }else{
                                            continue;
                                        }
                                    }else{
                                        continue;
                                    }
                                    ++postCounter;

                                }
                            }

                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                Intent intent = new Intent(Past_Details.this, Past_Rides.class);
                startActivity(intent);
            }
        });


    }

    private void getPastDetails(){

        fireBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot != null){
                    for (DataSnapshot data : snapshot.getChildren()) {
                        for(DataSnapshot keyData : data.getChildren()){
                            String acceptedUsername = "" + keyData.child("Accepted By").getValue();
                            String currentUsername = "" + user.getDisplayName();
                            String accepted = "" + keyData.child("Is Accepted").getValue();
                            String confirmed = "" + keyData.child("Is Confirmed").getValue();
                            if((accepted.equalsIgnoreCase("true")) && currentUsername.equals(acceptedUsername)){
                                if(confirmed.equalsIgnoreCase("false")){
                                    if((postCounter == pos) && !isFound){
                                        username = "" + data.getKey();
                                        dS = "" + keyData.child("Depart State").getValue();
                                        dC = "" + keyData.child("Depart City").getValue();
                                        userType = "" + keyData.child("User Type").getValue();
                                        aS = "" + keyData.child("Arrival State").getValue();
                                        aC = "" + keyData.child("Arrival City").getValue();
                                        date = "" + keyData.child("Date").getValue();
                                        time = "" + keyData.child("Time").getValue();
                                        setTexts();
                                        isFound = true;
                                    }
                                }else{
                                    continue;
                                }
                            }else{
                                continue;
                            }
                            ++postCounter;
                        }
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void setTexts(){
        userTitle2.setText(userType + " | " + username);
        departCity2.setText(dC);
        departState2.setText(dS);
        arrivalCity2.setText(aC);
        arrivalState2.setText(aS);
        dateBox2.setText(date);
        timeBox2.setText(time);
        riderPoints2.setText(rPoints);
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
            Intent intent = new Intent(Past_Details.this, MainActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.past_rides){
            Intent intent = new Intent(Past_Details.this, Past_Rides.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.create_post){
            Intent intent = new Intent(Past_Details.this, CreateListing.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
