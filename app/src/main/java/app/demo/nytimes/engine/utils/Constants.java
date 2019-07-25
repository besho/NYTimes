package app.demo.nytimes.engine.utils;

public class Constants {

    public static final String API_KEY = "UzXStYj8PozkQAG91xoXvEklkUb9Srrp";
    public static final String SERVER_BASE_URL = "http://api.nytimes.com/svc/";
    public static final long API_TIMEOUT = 60;

    //EndPoints IDs
    public static final int ID_GET_MOST_POPULAR_ARTICLES_API = 1;

    //EndPoints queries
    public static final String API_GetMostPopularArticlesUrl = "mostpopular/v2/mostviewed/all-sections/7.json?api-key=%s";
}
