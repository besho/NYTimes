package app.demo.nytimes.engine.responses;

import java.util.ArrayList;

import app.demo.nytimes.engine.entities.Article;

public class ArticlesResponse {

    private String status;
    private String copyright;
    private int num_results;
    private ArrayList<Article> results;

    public ArrayList<Article> getResults() {
        return results;
    }

    public void setResults(ArrayList<Article> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public int getNum_results() {
        return num_results;
    }

    public void setNum_results(int num_results) {
        this.num_results = num_results;
    }
}
