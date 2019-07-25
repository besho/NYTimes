package app.demo.nytimes.engine.entities;

/**
 * Created by Beshoy Adel on 10/9/2017.
 * Used to set error code in Case OkHttp call onFailure method
 */

public class Error {

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
