package firebasemvp.joseph.com.firebasemvp.view;

import firebasemvp.joseph.com.firebasemvp.utils.BasePresenter;
import firebasemvp.joseph.com.firebasemvp.utils.BaseView;

/**
 * Created by Admin on 2/16/2018.
 */

public class MainContract {



    interface View extends BaseView {

        void onUserCreation(boolean isCreated);

        void onUserValidation(boolean isValid);

        void isSessionValid(boolean isValid);





    }

    interface Presenter extends BasePresenter<View> {

        void validateUser(String email, String password);

        void createUser(String email, String password);

        void checkSession();



    }
}
