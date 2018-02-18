package firebasemvp.joseph.com.firebasemvp.RegisterView;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import firebasemvp.joseph.com.firebasemvp.Login_view.MainActivity;
import firebasemvp.joseph.com.firebasemvp.R;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View {


    RegisterPresenter presenter;
    private EditText mEmail, mPassword, mConfirmPassword;
    private Button mRegister;
    private String email,password,confirmPassword;
    private String TAG;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEmail = (EditText) findViewById(R.id.input_email);
        mPassword = (EditText) findViewById(R.id.input_password);
        mConfirmPassword = (EditText) findViewById(R.id.input_confirm_password);
        presenter = new  RegisterPresenter(this);

    }
    public void onRegister(View view) {
        getInputValues();
        presenter.createUser(email,password,confirmPassword);

    }

    private void getInputValues() {

        email = mEmail.getText().toString();
        password = mPassword.getText().toString();
        confirmPassword=mConfirmPassword.getText().toString() ;

    }

    private void redirectLoginScreen(){
        Log.d(TAG, "redirectLoginScreen: redirecting to Login screen.");

        Intent intent = new Intent( RegisterActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void isCreated(boolean isValid, String msg) {

        if(isValid){
            redirectLoginScreen();
        }else{


        }

    }
}
