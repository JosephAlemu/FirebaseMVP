package firebasemvp.joseph.com.firebasemvp.RegisterView;

/**
 * Created by Admin on 2/17/2018.
 */

public interface RegisterContract {

    interface View  {
        void isCreated(boolean isValid, String msg);
    }

    interface Presenter  {
        void createUser(String email, String password, String confirmPassword);

    }
}
