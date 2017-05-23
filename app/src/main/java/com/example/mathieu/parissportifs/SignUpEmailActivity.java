package com.example.mathieu.parissportifs;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpEmailActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText inputEmail, inputPassword, verifyPassword, inputUserName;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private static final String TAG = "EmailPassword";
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog progress;
    private TextView mStatusTextView, mDetailTextView;
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private FirebaseDatabase userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_email);

        //Get Firebase auth instance
        mAuth = FirebaseAuth.getInstance();


        //EditText et ProgressBar

        inputUserName = (EditText) findViewById(R.id.eTextuserName);
        inputEmail = (EditText) findViewById(R.id.emailText);
        inputPassword = (EditText) findViewById(R.id.passwordText);
        verifyPassword = (EditText) findViewById(R.id.editTextVerifyPass);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        //Button
        findViewById(R.id.buttonSignUp).setOnClickListener(this);
        findViewById(R.id.buttonBack).setOnClickListener(this);

        // [START auth_state_listener]
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());


                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // [START_EXCLUDE]
                // [END_EXCLUDE]
            }
        };
        // [END auth_state_listener]
    }

    // [START on_start_add_listener]
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    // [END on_start_add_listener]

    // [START on_stop_remove_listener]
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void pushUserOnFirebase(){
        if (!validateForm()) {
            return;
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users");
        FirebaseAuth.getInstance().getCurrentUser().getUid();

        String UserId = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        String UserPseudo = inputUserName.getText().toString();

        userDatabase  = FirebaseDatabase.getInstance(); //APPELLE LA BASE DE DONNEES
        myRef = userDatabase.getReference("Users");

        UserModel user = new UserModel(UserId, UserPseudo, null, 0);
        myRef.push().setValue(user);
    }


    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        progress = ProgressDialog.show(this, "Creating Account",
                "Loading...", true);

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                        Toast.makeText(SignUpEmailActivity.this, R.string.toastcheckyourmail, Toast.LENGTH_LONG).show();

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignUpEmailActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }
                       /* if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(SignUpActivity.this,
                                    "User with this email already exist.", Toast.LENGTH_SHORT).show();} */
                        progress.dismiss();
                    }
                });
        // [END create_user_with_email]
    }


    // [END send_email_verification]

    private boolean validateForm() {
        boolean valid = true;

        String UserPseudo = inputUserName.getText().toString();
        if (TextUtils.isEmpty(UserPseudo)) {
            inputUserName.setError("Required.");
            valid = false;
        } else {
            inputUserName.setError(null);
        }

        String email = inputEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            inputEmail.setError("Required.");
            valid = false;
        } else {
            inputEmail.setError(null);
        }
        String verify = verifyPassword.getText().toString();
        if (TextUtils.isEmpty(verify)) {
            verifyPassword.setError("One does not simply don't check his password");
            valid = false;
        }
        if (verifyPassword == inputPassword) {
            verifyPassword.setError("Your pass does not match");
            inputPassword.setError("Your pass does not match");
            valid = false;
        } else {
            verifyPassword.setError(null);
        }

        String password = inputPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            inputPassword.setError("Required.");
            valid = false;
        } else {
            inputPassword.setError(null);
        }

        return valid;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.buttonSignUp) {

            pushUserOnFirebase();

            createAccount(inputEmail.getText().toString().trim(), inputPassword.getText().toString().trim());
            SignUpEmailActivity.this.finish();
            //Toast.makeText(SignUpActivity.this, "Confirmer votre E-Mail pour vous logger", Toast.LENGTH_LONG).show();
        }
        if (i == R.id.buttonBack) {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));

        }
    }


}


