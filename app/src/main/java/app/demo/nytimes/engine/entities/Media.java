package app.demo.nytimes.engine.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Media {

    private String type;

    private String subtype;

    private String caption;

    private String copyright;

    @SerializedName("approved_for_syndication-metadata")
    private int approvedForSyndication;

    @SerializedName("media-metadata")
    private ArrayList<MediaMetaData> metaData;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public int getApprovedForSyndication() {
        return approvedForSyndication;
    }

    public void setApprovedForSyndication(int approvedForSyndication) {
        this.approvedForSyndication = approvedForSyndication;
    }

    public ArrayList<MediaMetaData> getMetaData() {
        return metaData;
    }

    public void setMetaData(ArrayList<MediaMetaData> metaData) {
        this.metaData = metaData;
    }
}
