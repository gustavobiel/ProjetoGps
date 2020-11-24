package com.example.projetogps;

public class Model {
    String id, local, data, lat, lon, descricao;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Model(String id, String local, String data, String lat, String lon, String descricao) {
        this.id = id;
        this.local = local;
        this.data = data;
        this.lat = lat;
        this.lon = lon;
        this.descricao = descricao;
    }
}

