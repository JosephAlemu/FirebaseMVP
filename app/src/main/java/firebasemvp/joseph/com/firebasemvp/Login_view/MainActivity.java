package firebasemvp.joseph.com.firebasemvp.Login_view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import firebasemvp.joseph.com.firebasemvp.R;
import firebasemvp.joseph.com.firebasemvp.RegisterView.RegisterActivity;

public class MainActivity extends AppCompatActivity implements MainContract.View ,View.OnClickListener{

    private static final String TAG = "MainActivity";
    // widgets
    private EditText mEmail, mPassword;

    private  MainPresenter mainPresenter;

    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmail = (EditText)findViewById(R.id.email);
        mPassword = (EditText)findViewById(R.id.password);

        mainPresenter = new MainPresenter(this);

        Button signIn = (Button) findViewById(R.id.email_sign_in_button);
        signIn.setOnClickListener( this);
        TextView register = (TextView) findViewById(R.id.link_register);
        register.setOnClickListener(this);


    }


    @Override
    protected void onStart() {
        super.onStart();

       // FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
    }

    @Override
    public void onUserValidation(boolean isValid) {

        Log.d(TAG, "onUserValidation: " + isValid);
        if (isValid) {

            Toast.makeText(this, "Signed In", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "redirectRegisterScreen: redirecting to Register screen.");

            Intent intent = new Intent(MainActivity.this, SignedInActivity.class);
            startActivity(intent);
            finish();


        } else
            Toast.makeText(this, "Sign in failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
           case R.id.email_sign_in_button :{

                           mainPresenter.validateUser(mEmail.getText().toString(),
                                                      mPassword.getText().toString());}
              break;

           case R.id.link_register:
                               redirectLoginScreen();

              break;

           default :
        }
    }



    private void redirectLoginScreen(){
        Log.d(TAG, "redirectRegisterScreen: redirecting to Register screen.");

        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}