package edu.uga.cs.ridewithme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
    public int points = 0;
    ImageButton homeButton;


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

        homeButton = (ImageButton) findViewById(R.id.imageButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateListing.this, MainActivity.class);
                startActivity(intent);
            }
        });


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
                // post.setDepartState(adapterView.getItemAtPosition(i).toString());
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
                createPost();
                //make it transition to dashboardfrag
                Switch switchButton = (Switch) findViewById(R.id.userSwitch);
                //int points;
                String pointString;
                if(switchButton.isChecked()) {
                    TextView pointsBox = findViewById(R.id.textView);
                    Spinner stateSpinner = findViewById(R.id.stateSpinner);
                    Spinner stateSpinner2 = findViewById(R.id.stateSpinner2);
                    String stateSpinnerContent;
                    String stateSpinnerContent2;
                    stateSpinnerContent = stateSpinner.getSelectedItem().toString();
                    stateSpinnerContent2 = stateSpinner2.getSelectedItem().toString();
                    if(stateSpinnerContent.equalsIgnoreCase(stateSpinnerContent2)) {
                        points = points + 50;
                        pointString = Integer.toString(points);
                        pointsBox.setText(pointString);
                    }
                    else {
                        points = points + 100;
                        pointString = Integer.toString(points);
                        pointsBox.setText(pointString);
                    }
                }
                if(switchButton.isChecked() == false) {
                    Spinner stateSpinner = findViewById(R.id.stateSpinner);
                    Spinner stateSpinner2 = findViewById(R.id.stateSpinner2);
                    String stateSpinnerContent;
                    String stateSpinnerContent2;
                    TextView pointsBox = findViewById(R.id.textView);
                    stateSpinnerContent = stateSpinner.getSelectedItem().toString();
                    stateSpinnerContent2 = stateSpinner2.getSelectedItem().toString();
                    if(stateSpinnerContent.equalsIgnoreCase(stateSpinnerContent2)) {
                        points = points - 50;
                        pointString = Integer.toString(points);
                        pointsBox.setText(pointString);
                    }
                    else {
                        points = points - 100;
                        pointString = Integer.toString(points);
                        pointsBox.setText(pointString);
                    }
                }
            }
        });
    }



    //Adds the rest of the values to the post object and then adds the post object to the database
    private void createPost(){
        String date = "" + dateField.getText();
        String time = "" + timeField.getText();
        int points = 0;

        post.setDate(date);
        post.setTime(time);
        String username = user.getDisplayName();
        String locationKey = fireBase.push().getKey();
        Switch switchButton = (Switch) findViewById(R.id.userSwitch);
        if(switchButton.isChecked()){
            post.setUserType("driver");
        }

        Map<String, Object> postContents = post.toMap();
        Map<String, Object> updater =new HashMap<>();


        updater.put("/posts/" + username + "/" + locationKey, postContents);

        //this one line is how you add to the data base
        //it will always be this
        fireBase.updateChildren(updater);

    }
}
