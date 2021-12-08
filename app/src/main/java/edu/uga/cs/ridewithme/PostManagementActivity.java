package edu.uga.cs.ridewithme;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentContainerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostManagementActivity extends AppCompatActivity {
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference fireBase = db.getReference();

    private Button createButton, viewButton;
    private LinearLayout entryLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        createButton = findViewById(R.id.createPost);
        viewButton = findViewById(R.id.viewPosts);
        entryLayout = findViewById(R.id.entryLayout);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostManagementActivity.this, CreateListing.class);
                startActivity(intent);
            }
        });

        //dont touch
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostManagementActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });

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
            Intent intent = new Intent(PostManagementActivity.this, MainActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.past_rides){
            Intent intent = new Intent(PostManagementActivity.this, Past_Rides.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.create_post){
            Intent intent = new Intent(PostManagementActivity.this, CreateListing.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



}
