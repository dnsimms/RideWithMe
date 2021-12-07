package edu.uga.cs.ridewithme;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

        //TODO implement the firebase.addValueEventListener to read through the database
        //TODO add each post to the pastTitles adapter using listPosts

        //TODO MAKE SURE YOU ADD createRecycleViewer() at the BOTTOM OF THE if(snapshot != null) statement
        //TODO make another activity called Past_details that is basically cpy past of Post_details
        //TODO the layout for past_details is cp/paste of post_details
        //TODO past_details will have the same display info, but the button will confirm if the user went on the ride
        //TODO if the user clicks the button, change their Is Confirmed value to "true"
        //TODO the post should disappear and riderpoints are appropriately allocated

    }

    private void listPosts(String ps){
        pastTitles.add(ps);
    }

    private void createRecycleViewer(){
   //     RecyclerView rView = findViewById(R.id.recycleView2);
    //    RecyclerViewAdapter adapter = new RecyclerViewAdapter(pastTitles, this);
  //      rView.setAdapter(adapter);
    //    rView.setLayoutManager(new LinearLayoutManager(this));
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

        //TODO implement past rides menu button
        //TODO if( item.getItemId() == R.id.past_rides)
        //TODO send it to a new past rides activity

        //for toolbar, implement later
        return super.onOptionsItemSelected(item);
    }



}
