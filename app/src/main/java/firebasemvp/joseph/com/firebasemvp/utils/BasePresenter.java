package firebasemvp.joseph.com.firebasemvp.utils;

/**
 * Created by Admin on 2/16/2018.
 */

public interface BasePresenter <V extends MVPView>  {
    public void attachView(V baseView);
    public void detachView();
}
