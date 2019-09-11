package com.example.watchnow_project.Model.Entity;

public class Category {
    private String id;
    private String publisher_ID;
    private int content_Type;
    private String tab;
    private String title;
    private String avatar;
    private int status;
    private boolean deleted;
    private String user_Created;
    private String user_Modified;
    private String date_Created;
    private String date_Modified;
    private String parent_ID;
    private int lft;
    private int rgt;
    private int level;
    private String short_Code;
    private String command_Code;
    private double price;
    private String finished_Message;
    private String help_Message;
    private String icash;
    private String thumb;

    public Category() {
    }

    public Category(String id, String publisher_ID, int content_Type, String tab, String title, String avatar, int status, boolean deleted, String user_Created, String user_Modified, String date_Created, String date_Modified, String parent_ID, int lft, int rgt, int level, String short_Code, String command_Code, double price, String finished_Message, String help_Message, String icash, String thumb) {
        this.id = id;
        this.publisher_ID = publisher_ID;
        this.content_Type = content_Type;
        this.tab = tab;
        this.title = title;
        this.avatar = avatar;
        this.status = status;
        this.deleted = deleted;
        this.user_Created = user_Created;
        this.user_Modified = user_Modified;
        this.date_Created = date_Created;
        this.date_Modified = date_Modified;
        this.parent_ID = parent_ID;
        this.lft = lft;
        this.rgt = rgt;
        this.level = level;
        this.short_Code = short_Code;
        this.command_Code = command_Code;
        this.price = price;
        this.finished_Message = finished_Message;
        this.help_Message = help_Message;
        this.icash = icash;
        this.thumb = thumb;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublisher_ID() {
        return publisher_ID;
    }

    public void setPublisher_ID(String publisher_ID) {
        this.publisher_ID = publisher_ID;
    }

    public int getContent_Type() {
        return content_Type;
    }

    public void setContent_Type(int content_Type) {
        this.content_Type = content_Type;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
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
        if(deleted ==0)
            this.deleted = false;
        else
            this.deleted = true;
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

    public String getParent_ID() {
        return parent_ID;
    }

    public void setParent_ID(String parent_ID) {
        this.parent_ID = parent_ID;
    }

    public int getLft() {
        return lft;
    }

    public void setLft(int lft) {
        this.lft = lft;
    }

    public int getRgt() {
        return rgt;
    }

    public void setRgt(int rgt) {
        this.rgt = rgt;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getShort_Code() {
        return short_Code;
    }

    public void setShort_Code(String short_Code) {
        this.short_Code = short_Code;
    }

    public String getCommand_Code() {
        return command_Code;
    }

    public void setCommand_Code(String command_Code) {
        this.command_Code = command_Code;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFinished_Message() {
        return finished_Message;
    }

    public void setFinished_Message(String finished_Message) {
        this.finished_Message = finished_Message;
    }

    public String getHelp_Message() {
        return help_Message;
    }

    public void setHelp_Message(String help_Message) {
        this.help_Message = help_Message;
    }

    public String getIcash() {
        return icash;
    }

    public void setIcash(String icash) {
        this.icash = icash;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
