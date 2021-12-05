package edu.uga.cs.ridewithme;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {

    private Button regSubmit;
    private EditText regEmail, regPass, username;
    private FirebaseAuth authenticator;

    private static final String DEBUG_TAG = "RegisterActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regSubmit = findViewById(R.id.regSubmit);
        authenticator = FirebaseAuth.getInstance();
        regEmail = findViewById(R.id.regEmail);
        regPass = findViewById(R.id.regPass);
        username = findViewById(R.id.username);

        regSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = "" + regEmail.getText();
                String password = "" + regPass.getText();
                String userName = "" + username.getText();
                createUser(email, password, userName);
            }
        });


    }

    private void createUser(String email, String password, String userName){
        authenticator.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = authenticator.getCurrentUser();
                            addUsername(userName, user);
                            Intent intent = new Intent(RegisterActivity.this, PostManagementActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(RegisterActivity.this, "Failed to Register",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void addUsername(String username, FirebaseUser user){
        UserProfileChangeRequest addUsername = new UserProfileChangeRequest.Builder()
                .setDisplayName(username)
                .build();
        user.updateProfile(addUsername)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Log.d(DEBUG_TAG, "Username added");
                        }else{
                            Log.d(DEBUG_TAG, "Username failed to be added");
                        }
                    }
                });

    }
}
