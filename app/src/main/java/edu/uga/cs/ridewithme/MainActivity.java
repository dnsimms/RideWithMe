package edu.uga.cs.ridewithme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final String DEBUG_TAG = "MainPage";

    private Button loginButton, registerButton, submitButton, registerButton2;
    private LinearLayout loginBox, signInButtonsBox;
    private EditText emailBox, passBox;
    private FirebaseAuth authenticator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBox = findViewById(R.id.loginBox);
        signInButtonsBox = findViewById(R.id.signInButtonsBox);
        emailBox = findViewById(R.id.emailBox);
        passBox = findViewById(R.id.passBox);

        submitButton = findViewById(R.id.submitButton);

        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        registerButton2 = findViewById(R.id.registerButton2);
        authenticator = FirebaseAuth.getInstance();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInButtonsBox.setVisibility(View.GONE);
                loginBox.setVisibility(View.VISIBLE);
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        registerButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });



        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = ""+ emailBox.getText();
                String password= "" + passBox.getText();
                signInUser(email, password);
                //to signout: FirebaseAuth.getInstance().signOut();
            }
        });




    }

    private void signInUser(String email, String password){
        authenticator.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = authenticator.getCurrentUser();
                            updateUI(user);
                        }else{
                            updateUI(null);
                        }
                    }
                });
    }



    private void updateUI(FirebaseUser user){
        if(user != null){
            Intent intent = new Intent(this, PostManagementActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(), "Sign-in failed!", Toast.LENGTH_SHORT)
                    .show();
        }

    }
}