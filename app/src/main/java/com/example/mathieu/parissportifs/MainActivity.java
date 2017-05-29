package com.example.mathieu.parissportifs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.facebook.CallbackManager;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;

import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.facebook.Profile;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private String uid;
    private FirebaseAuth firebaseAuth;
    private StorageReference mStorage;
    private StorageReference mUserRef;
    private FirebaseUser userId;
    private CallbackManager mCallbackManager;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ImageView imageViewPhotoFacebook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button4;
        button4 = (Button) findViewById(R.id.button4);

        imageViewPhotoFacebook = (ImageView) findViewById(R.id.imageViewPhotoFacebook);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout(v);
            }
        });


        FirebaseUser currentProfile = FirebaseAuth.getInstance().getCurrentUser();
        if (currentProfile != null) {
            Uri uri = currentProfile.getPhotoUrl();
            Glide.with(this).load(uri).into(imageViewPhotoFacebook);
        }
    }
        public void logout(View view) {
            FirebaseAuth.getInstance().signOut();
            LoginManager.getInstance().logOut();
            goLoginScreen();
        }
        public void goLoginScreen(){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
                MainActivity.this.finish();

    }


}