package edu.uga.cs.ridewithme;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class CreateListing extends AppCompatActivity {

    private Button createButton;
    private EditText dateField, timeField;
    private Spinner states, cities, states2, cities2;
    private ArrayAdapter<CharSequence> cityAdapter = null;
    private Posts post = new Posts();

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference fireBase = db.getReference();

    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private Toolbar toolbar;
    private Track_Points tracker = new Track_Points();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_listing);

        createButton = findViewById(R.id.create);
        dateField = findViewById(R.id.dateField);
        timeField = findViewById(R.id.timeField);
        states = (Spinner) findViewById(R.id.stateSpinner);
        cities = (Spinner) findViewById(R.id.citySpinner);
        states2 = (Spinner) findViewById(R.id.stateSpinner2);
        cities2 = (Spinner) findViewById(R.id.citySpinner2);

        toolbar = (Toolbar) findViewById(R.id.toolbar0);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        //This attaches the values to the spinner container, states.
        ArrayAdapter<CharSequence> stateAdapter = ArrayAdapter.createFromResource(this,
                R.array.states, android.R.layout.simple_spinner_item);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        states.setAdapter(stateAdapter);
        states2.setAdapter(stateAdapter);

        //This populates the city spinner container with the city values once you choose a statew
        states.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 1){//if ga
                    cityAdapter = ArrayAdapter.createFromResource(CreateListing.this,
                            R.array.gaCities, android.R.layout.simple_spinner_item);
                    cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    cities.setAdapter(cityAdapter);
                }else if (i == 2){//if fl
                    cityAdapter = ArrayAdapter.createFromResource(CreateListing.this,
                            R.array.flCities, android.R.layout.simple_spinner_item);
                    cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    cities.setAdapter(cityAdapter);
                }else if (i == 3){//if tn
                    cityAdapter = ArrayAdapter.createFromResource(CreateListing.this,
                            R.array.tnCities, android.R.layout.simple_spinner_item);
                    cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    cities.setAdapter(cityAdapter);
                }else if (i == 4){//if al
                    cityAdapter = ArrayAdapter.createFromResource(CreateListing.this,
                            R.array.alCities, android.R.layout.simple_spinner_item);
                    cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    cities.setAdapter(cityAdapter);

                }else if (i == 5){// if ms
                    cityAdapter = ArrayAdapter.createFromResource(CreateListing.this,
                            R.array.msCities, android.R.layout.simple_spinner_item);
                    cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    cities.setAdapter(cityAdapter);

                }else if (i == 6){// if tx
                    cityAdapter = ArrayAdapter.createFromResource(CreateListing.this,
                            R.array.txCities, android.R.layout.simple_spinner_item);
                    cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    cities.setAdapter(cityAdapter);
                }

                //Set the selected state to the post
                post.setDepartState(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                //probably keep empty
            }
        });


        cities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Set the selected city to the post
                post.setDepartCity(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        states2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 1){//if ga
                    cityAdapter = ArrayAdapter.createFromResource(CreateListing.this,
                            R.array.gaCities, android.R.layout.simple_spinner_item);
                    cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    cities2.setAdapter(cityAdapter);
                }else if (i == 2){//if fl
                    cityAdapter = ArrayAdapter.createFromResource(CreateListing.this,
                            R.array.flCities, android.R.layout.simple_spinner_item);
                    cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    cities2.setAdapter(cityAdapter);
                }else if (i == 3){//if tn
                    cityAdapter = ArrayAdapter.createFromResource(CreateListing.this,
                            R.array.tnCities, android.R.layout.simple_spinner_item);
                    cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    cities2.setAdapter(cityAdapter);
                }else if (i == 4){//if al
                    cityAdapter = ArrayAdapter.createFromResource(CreateListing.this,
                            R.array.alCities, android.R.layout.simple_spinner_item);
                    cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    cities2.setAdapter(cityAdapter);

                }else if (i == 5){// if ms
                    cityAdapter = ArrayAdapter.createFromResource(CreateListing.this,
                            R.array.msCities, android.R.layout.simple_spinner_item);
                    cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    cities2.setAdapter(cityAdapter);

                }else if (i == 6){// if tx
                    cityAdapter = ArrayAdapter.createFromResource(CreateListing.this,
                            R.array.txCities, android.R.layout.simple_spinner_item);
                    cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    cities2.setAdapter(cityAdapter);
                }

                //Set the selected state to the post
                post.setArrivalState(adapterView.getItemAtPosition(i).toString());
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                //probably keep empty
            }
        });

        cities2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Set the selected city to the post
                post.setArrivalCity(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Switch switchButton = (Switch) findViewById(R.id.userSwitch);


                if(switchButton.isChecked()) {//check if theyre a driver
                    TextView pointsBox = findViewById(R.id.textView);
                    Spinner stateSpinner = findViewById(R.id.stateSpinner);
                    Spinner stateSpinner2 = findViewById(R.id.stateSpinner2);
                    String stateSpinnerContent;
                    String stateSpinnerContent2;
                    stateSpinnerContent = stateSpinner.getSelectedItem().toString();
                    stateSpinnerContent2 = stateSpinner2.getSelectedItem().toString();
                    if(stateSpinnerContent.equalsIgnoreCase(stateSpinnerContent2)) {//give the post a -50 point value
                        post.addPoints(-50);
                        String points = "+50 points";
                        pointsBox.setText(points);
                    }
                    else {//100 if out of state
                        post.addPoints(-100);
                        String points = "+100 points";
                        pointsBox.setText(points);
                    }
                }
                if(!switchButton.isChecked()) {//if theyre a rider
                    Spinner stateSpinner = findViewById(R.id.stateSpinner);
                    Spinner stateSpinner2 = findViewById(R.id.stateSpinner2);
                    String stateSpinnerContent;
                    String stateSpinnerContent2;
                    TextView pointsBox = findViewById(R.id.textView);
                    stateSpinnerContent = stateSpinner.getSelectedItem().toString();
                    stateSpinnerContent2 = stateSpinner2.getSelectedItem().toString();
                    if(stateSpinnerContent.equalsIgnoreCase(stateSpinnerContent2)) {
                        post.addPoints(50);
                        String points = "-50 points";
                        pointsBox.setText(points);
                    }
                    else {
                        post.addPoints(100);
                        String points = "-100 points";
                        pointsBox.setText(points);
                    }
                }
                createPost();
            }
        });
    }



    //Adds the rest of the values to the post object and then adds the post object to the database

    /**
     * This method sets the values of the created post to the Posts object and adds it to the database
     */
    private void createPost(){
        String date = "" + dateField.getText();
        String time = "" + timeField.getText();

        post.setDate(date);
        post.setTime(time);
        String username = user.getDisplayName(); //get current user's display name
        String locationKey = fireBase.push().getKey();
        Switch switchButton = (Switch) findViewById(R.id.userSwitch);
        if(switchButton.isChecked()){//if they made a drive offer, give them points
            post.setUserType("driver");
            int postPoints = post.getPoints();
            int nextPoints = tracker.getPoints();
            if(postPoints == -50){//50 for in state
                nextPoints = nextPoints + 50;
            }else{//100 for out of state
                nextPoints = nextPoints +100;
            }
            tracker.setPoints(nextPoints);
        }else{//else if theyre a rider

            int postPoints = post.getPoints();
            int nextPoints = tracker.getPoints();
            if(postPoints == -50){
                nextPoints = nextPoints - 50;
            }else{
                nextPoints = nextPoints - 100;
            }
            tracker.setPoints(nextPoints);
        }

        Map<String, Object> postContents = post.toMap();
        Map<String, Object> updater =new HashMap<>();


        //find the path to log the post info
        updater.put("/posts/" + username + "/" + locationKey, postContents);

        //this one line is how you add to the data base
        //it will always be this
        fireBase.updateChildren(updater);

        //once created, go to dash
        Intent intent = new Intent(CreateListing.this, DashboardActivity.class);
        startActivity(intent);

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
        if(item.getItemId() == R.id.signOut){//if they click, sign them out
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(CreateListing.this, MainActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.past_rides){
            Intent intent = new Intent(CreateListing.this, Past_Rides.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.create_post){
                Intent intent = new Intent(CreateListing.this, CreateListing.class);
            startActivity(intent);
        }

        //for toolbar, implement later
        return super.onOptionsItemSelected(item);
    }
}
