package br.com.rftemer.githubviewer.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GitHubItem implements Parcelable {
    
    @SerializedName("id")
    @Expose
    private String id;
    
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("language")
    @Expose
    private String language;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("owner")
    @Expose
    private Owner owner;

    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String name) {
        this.name = name;
    }
    
    public String getLanguage() {
        return language;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.url);
        dest.writeString(this.name);
        dest.writeString(this.language);
        dest.writeParcelable(this.owner, flags);
    }

    protected GitHubItem(Parcel in) {
        this.id = in.readString();
        this.url = in.readString();
        this.name = in.readString();
        this.language = in.readString();
        this.owner = in.readParcelable(Owner.class.getClassLoader());
    }

    public static final Parcelable.Creator<GitHubItem> CREATOR = new Parcelable.Creator<GitHubItem>() {
        @Override
        public GitHubItem createFromParcel(Parcel source) {
            return new GitHubItem(source);
        }

        @Override
        public GitHubItem[] newArray(int size) {
            return new GitHubItem[size];
        }
    };
}
