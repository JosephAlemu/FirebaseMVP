package firebasemvp.joseph.com.firebasemvp;

/**
 * Created by Admin on 2/16/2018.
 */

public interface BasePresenter<V extends BaseView> {

    void addView(V View);
    void removeView();
}
