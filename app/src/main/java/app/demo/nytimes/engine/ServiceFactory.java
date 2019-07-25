package app.demo.nytimes.engine;

import android.annotation.SuppressLint;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Hashtable;

import app.demo.nytimes.engine.clients.InitGetClient;
import app.demo.nytimes.engine.entities.Error;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Beshoy Adel on 10/8/2017.
 * This Class is the Main Factory for handling any API call during App.
 */

public class ServiceFactory {

    /*Used in Get method with none Auth*/
    public void callServiceWithAuthNone(final int serviceId, final RequestListener listener)
    {
        callGetService(serviceId,listener,listener.getUiData(serviceId));
    }

    /*Responsible for handling "GET" methods for SportsEngine Auth types*/
    private void callGetService(final int serviceId, final RequestListener listener, final Hashtable<String, String> queryData) {

        Observable<Object> requestObservable = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@io.reactivex.annotations.NonNull final ObservableEmitter<Object> emitter) throws Exception {

                String fullURL = ServiceHelper.getServiceURL(serviceId, queryData);

                InitGetClient initGetClient = new InitGetClient(fullURL).invoke();
                OkHttpClient client = initGetClient.getClient();
                Request request = initGetClient.getRequest();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                        emitter.onError(e.getCause());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        if (response.code() == HttpURLConnection.HTTP_OK ) {
                            emitter.onNext(response);
                            emitter.onComplete();
                        }
                        else {
                            returnErrorObject(response, emitter);
                        }
                    }
                });

            }

        });

        subscribeObserable(serviceId, listener, requestObservable);
    }

    @SuppressLint("CheckResult")
    private void subscribeObserable(final int serviceId, final RequestListener listener, Observable<Object> requestObservable) {
        requestObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Object>() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable error) {

                        listener.handleException(HttpURLConnection.HTTP_INTERNAL_ERROR, serviceId);
                    }

                    @Override
                    public void onNext(Object object) {

                        if (object instanceof Error)
                            listener.handleException(((Error) object).getCode(), serviceId);
                        else
                        {
                            Object objectResult = ServiceHelper.getMappedObject(serviceId,object);
                            if (objectResult instanceof Error)
                                listener.handleException(((Error) objectResult).getCode(), serviceId);
                            else
                                listener.sendDataTobeShown(objectResult, serviceId);
                        }

                    }
                });
    }

    /*Used in case failure response "returns code not equal OK 200"*/
    private static void returnErrorObject(Response response, @io.reactivex.annotations.NonNull ObservableEmitter<Object> emitter) {
        Error error = new Error();
        error.setCode(response.code());
        emitter.onNext(error);
        emitter.onComplete();
    }

}
