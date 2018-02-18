package firebasemvp.joseph.com.firebasemvp.Login_view;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Admin on 2/16/2018.
 */
public class MainPresenter implements MainContract.Presenter{


    MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }


    @Override
    public void validateUser(String email, String password) {


        if(!isEmpty(email) && !isEmpty(password))
        {

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email ,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            view.onUserValidation(true);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    view.onUserValidation(false);
                }
            });
        }

        else
        {



        }

    }

    private boolean isEmpty(String string){
        return string.equals("");
    }



    @Override
    public void removeView() {

    }
}