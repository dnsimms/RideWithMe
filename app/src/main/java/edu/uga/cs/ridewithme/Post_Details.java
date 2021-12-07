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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Post_Details extends AppCompatActivity {
    private TextView detailsText;
    private int pos = 0, userCounter = 0, postCounter = 0;
    private BufferedReader reader = null;
    private InputStream streamer = null;
    private String userType, username, dC, dS, aC,
    aS, date, time, rPoints;
    private boolean isFound = false;
    private TextView userTitle, departCity, departState,
    arrivalCity, arrivalState, dateBox, timeBox, riderPoints;
    private Button acceptButton;

    //Both variables are need to get the instance of the database
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference fireBase = db.getReference("posts");

    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_details);
        Intent intent = getIntent();
        pos = intent.getIntExtra("position", 1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        userTitle = findViewById(R.id.userTitle);
        departCity = findViewById(R.id.departCity);
        departState = findViewById(R.id.departState);
        arrivalCity = findViewById(R.id.arrivalCity);
        arrivalState = findViewById(R.id.arrivalState);
        dateBox = findViewById(R.id.dateBox);
        timeBox = findViewById(R.id.timeBox);
        riderPoints = findViewById(R.id.riderPoints);
        acceptButton = findViewById(R.id.accept);
        populateInfo();

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uName = user.getDisplayName();
                isFound = false;
                postCounter = 0;
                userCounter = 0;

                //this is how you read through the database
                fireBase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot != null){
                            for (DataSnapshot data : snapshot.getChildren()) {// TODO do data.getKey() to get the username of the poster
                                for(DataSnapshot keyData : data.getChildren()){
                                    if((postCounter == pos) && !isFound){
                                        String location = keyData.getKey();
                                        fireBase.child(uName).child(location).child("Is Accepted").setValue("true");
                                        isFound = true;
                                    }
                                    ++postCounter;

                                }
                                ++userCounter;
                                postCounter = 0;
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                Intent intent = new Intent(Post_Details.this, DashboardActivity.class);
                startActivity(intent);
            }
        });

    }

    private void populateInfo(){


        fireBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot != null){
                    for (DataSnapshot data : snapshot.getChildren()) {
                        for(DataSnapshot keyData : data.getChildren()){

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
                            ++postCounter;

                        }
                        ++userCounter;
                        postCounter = 0;
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void setTexts(){
        userTitle.setText(userType + " | " + username);
        departCity.setText(dC);
        departState.setText(dS);
        arrivalCity.setText(aC);
        arrivalState.setText(aS);
        dateBox.setText(date);
        timeBox.setText(time);
        riderPoints.setText(rPoints);
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
