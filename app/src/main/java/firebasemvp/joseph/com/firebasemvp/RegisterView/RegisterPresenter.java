package firebasemvp.joseph.com.firebasemvp.RegisterView;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import firebasemvp.joseph.com.firebasemvp.Login_view.MainActivity;

/**
 * Created by Admin on 2/17/2018.
 */

public class RegisterPresenter implements RegisterContract.Presenter {
    private String DOMAIN_NAME = "gmail.com";



    RegisterContract.View view;
    private static final String TAG = "RegisterPresenter";
    private String msg ="";


    public RegisterPresenter(RegisterContract.View view) {
        this.view = view;
    }

    @Override
    public void createUser(String email, String password, String confirmPassword) {


        //check for null valued EditText fields
        if(!isEmpty(email) && !isEmpty(password) && !isEmpty(confirmPassword))
        {
            //check if user has a company email address
            if(isValidDomain(email))
            {
                //check if passwords match
                if(doStringsMatch(password, confirmPassword))
                {
                    //Initiate registration task
                    registerNewEmail(email, password);
                }else{
                    System.out.println("Passwords do not Match");
                    view.isCreated(false,msg);
                }
            }else{


                System.out.println("Please Register with Company Email");
                view.isCreated(false,msg);
            }

        }else{


            System.out.println("You must fill out all the fields");
            view.isCreated(false, msg);
        }

    }

    public void registerNewEmail(final String email, String password){

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        if (task.isSuccessful()){
                            Log.d(TAG, "onComplete: AuthState: " + FirebaseAuth.getInstance().getCurrentUser().getEmail());
                            msg = "  Registered"+FirebaseAuth.getInstance().getCurrentUser().getEmail();
                            FirebaseAuth.getInstance().signOut();

                            //redirect the user to the login screen
                            view.isCreated(true,msg);
                                    //redirectLoginScreen();
                        }
                        if (!task.isSuccessful()) {
                            msg = "Unable to Register";
                            view.isCreated(false,msg);
                            System.out.println("Unable to Register");

                        }

                    }
                });
    }

    private boolean isEmpty(String string){
        return string.equals("");
    }

    private boolean doStringsMatch(String s1, String s2){
        return s1.equals(s2);
    }

    private boolean isValidDomain(String email){
        Log.d(TAG, "isValidDomain: verifying email has correct domain: " + email);
        String domain = email.substring(email.indexOf("@") + 1).toLowerCase();
        Log.d(TAG, "isValidDomain: users domain: " + domain);
        return domain.equals(DOMAIN_NAME);
    }
}
