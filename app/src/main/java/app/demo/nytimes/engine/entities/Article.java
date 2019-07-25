package app.demo.nytimes.engine.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Article {

    private String url;

    @SerializedName("adx_keywords")
    private String adxKeywords;

    private String column;

    @SerializedName("count_type")
    private String countType;

    @SerializedName("email_count")
    private int emailCount;

    @SerializedName("eta_id")
    private int etaId;

    private double id;

    private String nytdsection;

    @SerializedName("published_date")
    private String publishedDate;

    private ArrayList<Media> media;

    private String section;

    private String source;

    private String byline;

    private String subsection;

    private String title;

    private String type;

    private String updated;

    private String uri;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAdxKeywords() {
        return adxKeywords;
    }

    public void setAdxKeywords(String adxKeywords) {
        this.adxKeywords = adxKeywords;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getCountType() {
        return countType;
    }

    public void setCountType(String countType) {
        this.countType = countType;
    }

    public int getEmailCount() {
        return emailCount;
    }

    public void setEmailCount(int emailCount) {
        this.emailCount = emailCount;
    }

    public int getEtaId() {
        return etaId;
    }

    public void setEtaId(int etaId) {
        this.etaId = etaId;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getNytdsection() {
        return nytdsection;
    }

    public void setNytdsection(String nytdsection) {
        this.nytdsection = nytdsection;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public ArrayList<Media> getMedia() {
        return media;
    }

    public void setMedia(ArrayList<Media> media) {
        this.media = media;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSubsection() {
        return subsection;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }
}
