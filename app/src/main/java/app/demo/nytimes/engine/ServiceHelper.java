package app.demo.nytimes.engine;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Hashtable;

import app.demo.nytimes.engine.entities.Error;
import app.demo.nytimes.engine.responses.ArticlesResponse;
import app.demo.nytimes.engine.utils.AppParametersConstants;
import app.demo.nytimes.engine.utils.Constants;
import okhttp3.Response;


public class ServiceHelper {

    /*Responsible for generate Service URL
     * In "Get" method:- getServiceURL method will concatenate Url of endpoint with its query params
     * In "Get" method:- getServiceURL method will return only URL of endPoint ("Post body will send in PostClient in ServiceFactory")
    */
    public static String getServiceURL(int serviceId, Hashtable<String, String> queryData) {

        String endPoint = Constants.SERVER_BASE_URL;

        String queryString = "";
        switch (serviceId) {
            case Constants.ID_GET_MOST_POPULAR_ARTICLES_API:
                queryString = String.format(Constants.API_GetMostPopularArticlesUrl
                        ,queryData.get(AppParametersConstants.INTENT_KEY_API_KEY));
                break;
        }

        return endPoint + queryString;
    }

    /*Response for mapping response with expected object*/
    public static Object getMappedObject(int serviceId, Object object) {

        Response response = (Response)object;
        String responseStr= null;
        try {
            responseStr = response.body().string();

            switch (serviceId) {
                case Constants.ID_GET_MOST_POPULAR_ARTICLES_API:
                    return new Gson().fromJson(responseStr, ArticlesResponse.class);
            }

        } catch (IOException e) {
            return parseError();
        }
        catch (IllegalStateException | JsonSyntaxException exception)
        {
            return parseError();
        }

        return parseError();
    }

    private static Error parseError()
    {
        Error error = new Error();
        error.setCode(HttpURLConnection.HTTP_BAD_REQUEST);
        return error;
    }
}
