package app.demo.nytimes.ui.articleslist;

import java.util.ArrayList;

import app.demo.nytimes.engine.entities.Article;

public interface ArticleView {

    void setupView();

    void showLoadingProgress();

    void hideLoadingProgress();

    void bindArticlesList(ArrayList<Article> articlesList);

    void showReloadView();

    void hideReloadView();


}
