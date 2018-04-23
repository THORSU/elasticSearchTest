package com.cxd.pojo;

/**
 * Created by root on 17-7-13.
 */
public class User {
    private String sid;
    private String sname;
    private String spassword;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSpassword() {
        return spassword;
    }

    public void setSpassword(String spassword) {
        this.spassword = spassword;
    }

    public String getSmobile() {
        return smobile;
    }

    public void setSmobile(String smobile) {
        this.smobile = smobile;
    }

    private String smobile;

    @Override
    public String toString() {
        return "User{" +
                "sid='" + sid + '\'' +
                ", sname='" + sname + '\'' +
                ", spassword='" + spassword + '\'' +
                ", smobile='" + smobile + '\'' +
                '}';
    }
}
