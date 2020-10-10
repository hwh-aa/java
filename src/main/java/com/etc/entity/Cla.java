package com.etc.entity;

public class Cla {

    private Integer cid;
    private String cname;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Cla(Integer cid, String cname) {
        this.cid = cid;
        this.cname = cname;
    }

    public Cla() {
    }
}
