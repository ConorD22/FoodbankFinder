package com.example.foodbankfinder;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Button SignOut;

    Button info;

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentuser = mAuth.getCurrentUser();
    }

    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
                @Override
                public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
                    onSignInResult(result);
                }
            }
    );



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        createSignInIntent();

        SignOut = (Button) findViewById(R.id.btn_signout);
        SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        Button maps_btn = (Button) findViewById(R.id.btn_maps);
        maps_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(i);

            }
        });

        info = (Button) findViewById(R.id.btn_info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, InformationPage.class);
                startActivity(i);
            }
        });
    }

    public void createSignInIntent(){

        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.AnonymousBuilder().build());

        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.drawable.logo)
                .build();
        signInLauncher.launch(signInIntent);
    }

    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        IdpResponse response = result.getIdpResponse();
        if(result.getResultCode() == RESULT_OK) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            Toast.makeText(this, user.getEmail(), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Failed Login", Toast.LENGTH_SHORT).show();
        }
    }

    private void signOut(){
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        createSignInIntent();
                    }
                });
    }
}