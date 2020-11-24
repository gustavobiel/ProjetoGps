package com.example.projetogps;

import java.util.Date;

public class Localizacao {
    public String local;
            public Date data;
            public String lat;
            public String lon;
    public String desc;

    public Localizacao(String local) {
        this.local = local;
    }

    public Localizacao() {

    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
