package app.demo.nytimes.engine.clients;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

import static app.demo.nytimes.engine.utils.Constants.API_TIMEOUT;

/*Responsible for init Okhttp client for "GET" method based on all auth types*/
public class InitGetClient {

    private String fullURL;
    private OkHttpClient client;
    private Request request;

    public InitGetClient(String fullURL) {
        this.fullURL = fullURL;
    }

    public OkHttpClient getClient() {
        return client;
    }

    public Request getRequest() {
        return request;
    }

    public InitGetClient invoke() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        client = new OkHttpClient.Builder()
                .connectTimeout(API_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(API_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(API_TIMEOUT, TimeUnit.SECONDS)
                .cache(null)//clear cache
                .addInterceptor(logging)
                .build();

        request = new Request.Builder().url(fullURL).addHeader("Cache-Control", "no-cache").build();
        return this;
    }
}
