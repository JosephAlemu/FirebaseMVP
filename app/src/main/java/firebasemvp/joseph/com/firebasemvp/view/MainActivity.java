package firebasemvp.joseph.com.firebasemvp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import firebasemvp.joseph.com.firebasemvp.R;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    EditText etEmail = (EditText)findViewById(R.id.etEmail);
    EditText etPassword = (EditText)findViewById(R.id.etPassword);

    private String email;
    private String password;

  
    LoginPresenter presenter;

    public static final String TAG = "LoginActivityTag";
    private boolean isNotificationReceived;
    private String articleName;
    private String articleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupDaggerComponent();


        //handle notification
        articleName = getIntent().getStringExtra("name");
        articleId = getIntent().getStringExtra("id");

        if (articleName != null) {
            isNotificationReceived = true;
        }

    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
        MainPresenter.attachView(this);
        MainPresenter.checkSession();

    }

    private void setupDaggerComponent() {
        FireBaseApplication
                .get(this)
                .getLoginComponent(this)
                .inject(this);
    }

    private void getCredentials() {
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();
    }


    @Override
    public void showError(String error) {
        showToast(error);
    }

    @Override
    public void onSignOut(Boolean isSignedOut) {

    }

    @Override
    public void onUserCreation(boolean isCreated) {

        Log.d(TAG, "onUserCreation: " + isCreated);

        if (isCreated)
            showToast("User created, please sign in");
        else
            showToast("User not created");
    }

    @Override
    public void onUserValidation(boolean isValid) {

        Log.d(TAG, "onUserValidation: " + isValid);
        if (isValid) {

            showToast("Signed In");
            startMovieActivity();
        } else
            showToast("Sign in failed");
    }

    @Override
    public void isSessionValid(boolean isValid) {
        if (isValid) {
            if (isNotificationReceived) {
                startNotificationActivity(articleName, articleId);
            }
            else {
                startMovieActivity();
            }
        }

    }
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.detachView();
    }

    public void onSignIn(View view) {
        getCredentials();
        MainPresenter.validateUser(email, password);
    }

    public void onSignUp(View view) {

        getCredentials();
        MainPresenter.createUser(email, password);
    }
}