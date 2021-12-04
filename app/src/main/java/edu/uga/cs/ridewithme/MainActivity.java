package edu.uga.cs.ridewithme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private static final String DEBUG_TAG = "MainPage";

    private Button loginButton, registerButton, submitButton;
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
        authenticator = FirebaseAuth.getInstance();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInButtonsBox.setVisibility(View.GONE);
                loginBox.setVisibility(View.VISIBLE);

                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String email = ""+ emailBox.getText();
                        String password= "" + passBox.getText();

                        System.out.println("Here is the email: " + email);
                        System.out.println("Here is the password: " + password);

                        //to signout: FirebaseAuth.getInstance().signOut();
                    }
                });
            }
        });

    }
}