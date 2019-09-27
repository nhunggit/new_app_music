package com.example.new_music;

public class baiHat {
    int stt;
    String tenbh;
    int bia;
    int file;

    public baiHat(int stt, String tenbh, int bia, int file) {
        this.stt = stt;
        this.tenbh = tenbh;
        this.bia = bia;
        this.file = file;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getTenbh() {
        return tenbh;
    }

    public void setTenbh(String tenbh) {
        this.tenbh = tenbh;
    }

    public int getBia() {
        return bia;
    }

    public void setBia(int bia) {
        this.bia = bia;
    }

    public int getFile() {
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }
}
