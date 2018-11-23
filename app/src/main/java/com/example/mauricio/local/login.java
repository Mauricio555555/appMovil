package com.example.mauricio.local;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class login extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

   private LinearLayout prof;
   private Button signout, contin;
   private SignInButton signInButton;
   private TextView nombre, Email;
   private ImageView prof_v;
   private GoogleApiClient googleApiClient;
   private  static final int REQ_CODE = 9001;

    String name = null;
    String email = null;

   private LoginButton loginButton;
   private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        prof = (LinearLayout) findViewById(R.id.prof);
        prof_v = (ImageView) findViewById(R.id.img);
        nombre = (TextView) findViewById(R.id.t1);
        Email = (TextView) findViewById(R.id.t2);
        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signout = (Button) findViewById(R.id.signout);

        contin = (Button) findViewById(R.id.continuar);
        contin.setOnClickListener(this);

        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                //nombre.setText("Login completado \n"
                     //   + loginResult.getAccessToken().getUserId() + "\n" + loginResult.getAccessToken().getToken());
                }

            @Override
            public void onCancel() {
                // App code
            //    nombre.setText("Login cancelado ");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });



        signInButton.setOnClickListener(this);
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();
        contin.setVisibility(View.GONE);
        nombre.setVisibility(View.GONE);
        Email.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {

        try
        {
            switch (v.getId())
            {
                case R.id.sign_in_button:
                    signIn();
                    break;
                case R.id.signout:
                    signOut();
                    break;
                case R.id.continuar:
                    Intent intent = new Intent(this, MainActivity.class);
                    finish();
                    startActivity(intent);
                    break;
            }
        }
        catch (Exception e)
        {
            Email.setText("Error");
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void signIn()
    {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, REQ_CODE);
    }

    private void signOut()
    {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                update(false);
            }
        });
    }

    private void handleResult(GoogleSignInResult result)
    {
        if (result.isSuccess())
        {
            GoogleSignInAccount account = result.getSignInAccount();
            name = account.getDisplayName();
            email = account.getEmail();
            //String img_url = account.getPhotoUrl().toString();
            nombre.setText(nombre());
            Email.setText(correo());
            //Glide.vich(this).load(img_url).into(prof_v);
            update(true);
        }
        else
        {
            update(false);
        }
    }

    private void update(boolean isLogin)
    {
        if (isLogin)
        {
           prof.setVisibility(View.GONE);
           signInButton.setVisibility(View.GONE);
           loginButton.setVisibility(View.GONE);
           contin.setVisibility(View.VISIBLE);
           nombre.setVisibility(View.VISIBLE);
           Email.setVisibility(View.VISIBLE);

        }
        else
        {
            prof.setVisibility(View.VISIBLE);
            signInButton.setVisibility(View.VISIBLE);
            loginButton.setVisibility(View.VISIBLE);
            contin.setVisibility(View.GONE);
            nombre.setVisibility(View.GONE);
            Email.setVisibility(View.GONE);
        }
    }

    @Override
    protected  void onActivityResult(int requestCode, int resulCode, Intent data)
    {
        super.onActivityResult(requestCode, resulCode, data);

        if(requestCode==REQ_CODE)
        {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }
    }

    public String nombre()
    {
        return name;
    }

    public String correo()
    {
        return email;
    }
}
