package com.example.watchnow_project.Model.Entity;

public class Video {
    private String id;
    private String provider_ID;
    private String category_ID;
    private String title;
    private String avatar;
    private double price;
    private int status;
    private boolean deleted;
    private String copyRight;
    private String artist_Name;
    private String album_Name;
    private String file_Mp4;
    private double file_Mp4_Size;
    private double file_3gp_Size;
    private String total_Downloaded;
    private String description;
    private String duration;
    private String date_Created;
    private String date_Modified;
    private String date_Published;
    private String user_Created;
    private String user_Modified;
    private String convert_Status;
    private String convert_Time;
    private String youtube_Url;
    private String tags;
    private int download_Status;
    private String fb_Download;
    private String icash;
    private String fb_Url;
    private String aws_Status;
    private String icash_2;
    private double price_2;
    private String publisher_Category_ID;
    private String view_Clip_Gold;
    private String view_Clip_Icash;
    private int download_Clip_Gold;
    private int download_Clip_Icash;

    public Video() {
    }

    public Video(String id, String provider_ID, String category_ID, String title, String avatar, double price, int status, boolean deleted, String copyRight, String artist_Name, String album_Name, String file_Mp4, double file_Mp4_Size, double file_3gp_Size, String total_Downloaded, String description, String duration, String date_Created, String date_Modified, String date_Published, String user_Created, String user_Modified, String convert_Status, String convert_Time, String youtube_Url, String tags, int download_Status, String fb_Download, String icash, String fb_Url, String aws_Status, String icash_2, double price_2, String publisher_Category_ID, String view_Clip_Gold, String view_Clip_Icash, int download_Clip_Gold, int download_Clip_Icash) {
        this.id = id;
        this.provider_ID = provider_ID;
        this.category_ID = category_ID;
        this.title = title;
        this.avatar = avatar;
        this.price = price;
        this.status = status;
        this.deleted = deleted;
        this.copyRight = copyRight;
        this.artist_Name = artist_Name;
        this.album_Name = album_Name;
        this.file_Mp4 = file_Mp4;
        this.file_Mp4_Size = file_Mp4_Size;
        this.file_3gp_Size = file_3gp_Size;
        this.total_Downloaded = total_Downloaded;
        this.description = description;
        this.duration = duration;
        this.date_Created = date_Created;
        this.date_Modified = date_Modified;
        this.date_Published = date_Published;
        this.user_Created = user_Created;
        this.user_Modified = user_Modified;
        this.convert_Status = convert_Status;
        this.convert_Time = convert_Time;
        this.youtube_Url = youtube_Url;
        this.tags = tags;
        this.download_Status = download_Status;
        this.fb_Download = fb_Download;
        this.icash = icash;
        this.fb_Url = fb_Url;
        this.aws_Status = aws_Status;
        this.icash_2 = icash_2;
        this.price_2 = price_2;
        this.publisher_Category_ID = publisher_Category_ID;
        this.view_Clip_Gold = view_Clip_Gold;
        this.view_Clip_Icash = view_Clip_Icash;
        this.download_Clip_Gold = download_Clip_Gold;
        this.download_Clip_Icash = download_Clip_Icash;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvider_ID() {
        return provider_ID;
    }

    public void setProvider_ID(String provider_ID) {
        this.provider_ID = provider_ID;
    }

    public String getCategory_ID() {
        return category_ID;
    }

    public void setCategory_ID(String category_ID) {
        this.category_ID = category_ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        if (deleted == 0) {
            this.deleted = false;
        } else {
            this.deleted = true;
        }
    }

    public String getCopyRight() {
        return copyRight;
    }

    public void setCopyRight(String copyRight) {
        this.copyRight = copyRight;
    }

    public String getArtist_Name() {
        return artist_Name;
    }

    public void setArtist_Name(String artist_Name) {
        this.artist_Name = artist_Name;
    }

    public String getAlbum_Name() {
        return album_Name;
    }

    public void setAlbum_Name(String album_Name) {
        this.album_Name = album_Name;
    }

    public String getFile_Mp4() {
        return file_Mp4;
    }

    public void setFile_Mp4(String file_Mp4) {
        this.file_Mp4 = file_Mp4;
    }

    public double getFile_Mp4_Size() {
        return file_Mp4_Size;
    }

    public void setFile_Mp4_Size(double file_Mp4_Size) {
        this.file_Mp4_Size = file_Mp4_Size;
    }

    public double getFile_3gp_Size() {
        return file_3gp_Size;
    }

    public void setFile_3gp_Size(double file_3gp_Size) {
        this.file_3gp_Size = file_3gp_Size;
    }

    public String getTotal_Downloaded() {
        return total_Downloaded;
    }

    public void setTotal_Downloaded(String total_Downloaded) {
        this.total_Downloaded = total_Downloaded;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDate_Created() {
        return date_Created;
    }

    public void setDate_Created(String date_Created) {
        this.date_Created = date_Created;
    }

    public String getDate_Modified() {
        return date_Modified;
    }

    public void setDate_Modified(String date_Modified) {
        this.date_Modified = date_Modified;
    }

    public String getDate_Published() {
        return date_Published;
    }

    public void setDate_Published(String date_Published) {
        this.date_Published = date_Published;
    }

    public String getUser_Created() {
        return user_Created;
    }

    public void setUser_Created(String user_Created) {
        this.user_Created = user_Created;
    }

    public String getUser_Modified() {
        return user_Modified;
    }

    public void setUser_Modified(String user_Modified) {
        this.user_Modified = user_Modified;
    }

    public String getConvert_Status() {
        return convert_Status;
    }

    public void setConvert_Status(String convert_Status) {
        this.convert_Status = convert_Status;
    }

    public String getConvert_Time() {
        return convert_Time;
    }

    public void setConvert_Time(String convert_Time) {
        this.convert_Time = convert_Time;
    }

    public String getYoutube_Url() {
        return youtube_Url;
    }

    public void setYoutube_Url(String youtube_Url) {
        this.youtube_Url = youtube_Url;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getDownload_Status() {
        return download_Status;
    }

    public void setDownload_Status(int download_Status) {
        this.download_Status = download_Status;
    }

    public String getFb_Download() {
        return fb_Download;
    }

    public void setFb_Download(String fb_Download) {
        this.fb_Download = fb_Download;
    }

    public String getIcash() {
        return icash;
    }

    public void setIcash(String icash) {
        this.icash = icash;
    }

    public String getFb_Url() {
        return fb_Url;
    }

    public void setFb_Url(String fb_Url) {
        this.fb_Url = fb_Url;
    }

    public String getAws_Status() {
        return aws_Status;
    }

    public void setAws_Status(String aws_Status) {
        this.aws_Status = aws_Status;
    }

    public String getIcash_2() {
        return icash_2;
    }

    public void setIcash_2(String icash_2) {
        this.icash_2 = icash_2;
    }

    public double getPrice_2() {
        return price_2;
    }

    public void setPrice_2(double price_2) {
        this.price_2 = price_2;
    }

    public String getPublisher_Category_ID() {
        return publisher_Category_ID;
    }

    public void setPublisher_Category_ID(String publisher_Category_ID) {
        this.publisher_Category_ID = publisher_Category_ID;
    }

    public String getView_Clip_Gold() {
        return view_Clip_Gold;
    }

    public void setView_Clip_Gold(String view_Clip_Gold) {
        this.view_Clip_Gold = view_Clip_Gold;
    }

    public String getView_Clip_Icash() {
        return view_Clip_Icash;
    }

    public void setView_Clip_Icash(String view_Clip_Icash) {
        this.view_Clip_Icash = view_Clip_Icash;
    }

    public int getDownload_Clip_Gold() {
        return download_Clip_Gold;
    }

    public void setDownload_Clip_Gold(int download_Clip_Gold) {
        this.download_Clip_Gold = download_Clip_Gold;
    }

    public int getDownload_Clip_Icash() {
        return download_Clip_Icash;
    }

    public void setDownload_Clip_Icash(int download_Clip_Icash) {
        this.download_Clip_Icash = download_Clip_Icash;
    }
}
