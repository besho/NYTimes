package app.demo.nytimes.ui.articleslist;

import java.util.Hashtable;

import app.demo.nytimes.engine.RequestListener;
import app.demo.nytimes.engine.ServiceFactory;
import app.demo.nytimes.engine.responses.ArticlesResponse;
import app.demo.nytimes.engine.utils.AppParametersConstants;
import app.demo.nytimes.engine.utils.Constants;

public class ArticlesPresenter implements ArticlesMvpPresenter {

    private ArticleView mArticleView;
    private ServiceFactory mServiceFactory;

    public ArticlesPresenter(ArticleView sectionView) {
        mArticleView = sectionView;
        mServiceFactory = new ServiceFactory();
    }

    @Override
    public void getArticles() {

        mArticleView.showLoadingProgress();
        mServiceFactory.callServiceWithAuthNone(Constants.ID_GET_MOST_POPULAR_ARTICLES_API, new RequestListener() {
            @Override
            public Hashtable<String, String> getUiData(int serviceId) {
                Hashtable<String, String> uiData = new Hashtable<>();
                uiData.put(AppParametersConstants.INTENT_KEY_API_KEY,Constants.API_KEY);
                return uiData;
            }

            @Override
            public void sendDataTobeShown(Object data, int serviceId) {
                mArticleView.hideLoadingProgress();
                if (serviceId== Constants.ID_GET_MOST_POPULAR_ARTICLES_API) {
                    ArticlesResponse response = (ArticlesResponse) data;
                    if (response != null && response.getResults() != null) {
                        mArticleView.bindArticlesList(response.getResults());
                    }
                }
            }

            @Override
            public void handleException(int statusCode, int serviceId) {
                mArticleView.hideLoadingProgress();
                mArticleView.showReloadView();
            }
        });

    }
}
