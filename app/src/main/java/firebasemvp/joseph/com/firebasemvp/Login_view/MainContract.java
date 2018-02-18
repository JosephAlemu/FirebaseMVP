package firebasemvp.joseph.com.firebasemvp.Login_view;

/**
 * Created by Admin on 2/16/2018.
 */

public class MainContract {



    interface View  {


        void onUserValidation(boolean isValid);



    }

    interface Presenter  {

        void validateUser(String email, String password);


        void removeView();


    }
}
