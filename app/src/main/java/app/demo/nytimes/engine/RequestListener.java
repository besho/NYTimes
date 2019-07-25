package app.demo.nytimes.engine;

import java.util.Hashtable;

/**
 * Created by Beshoy Adel on 03/06/2019.
 */

public interface RequestListener {

    /*Path query data to service in case get/post methods*/
    Hashtable<String, String> getUiData(int serviceId);

    /*In case that service returned OK*/
    void sendDataTobeShown(Object data, int serviceId);

    /*In case that service returned Failure*/
    void handleException(int statusCode, int serviceId);
}
