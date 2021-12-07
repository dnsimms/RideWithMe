package edu.uga.cs.ridewithme;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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

public class DashboardActivity extends AppCompatActivity {

    private int position = 0, counter = 0;
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

        fireBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot != null) {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        for(DataSnapshot keyData : data.getChildren()){
                            String key = keyData.getKey();
                            String state = "" + keyData.child("Depart State").getValue();
                            System.out.println(state);
                            String city = "" + keyData.child("Depart City").getValue();
                            String userType = "" + keyData.child("User Type").getValue();
                            String title = "Departing from: " + city + ", " + state + " | " + userType;
                            namePosts(title);
                            ++counter;
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
}
