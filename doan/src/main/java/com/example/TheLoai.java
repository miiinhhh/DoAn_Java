package com.example;

import java.util.*;
import java.io.*;
public class TheLoai {
    private String ma_the_loai;
    private String ten_the_loai;
    private String mo_ta;
    private ArrayList<Sach> dsSach = new ArrayList<>();
    public TheLoai(){
        
    }
    public TheLoai(String ma_the_loai,String ten_the_loai, String mo_ta){
        this.ma_the_loai = ma_the_loai;
        this.ten_the_loai=ten_the_loai;
        this.mo_ta=mo_ta;
    }

    public String getMa_the_loai() {
        return ma_the_loai;
    }

    public void setMa_the_loai(String ma_the_loai) {
        this.ma_the_loai = ma_the_loai;
    }

    public String getTen_the_loai() {
        return ten_the_loai;
    }

    public void setTen_the_loai(String ten_the_loai) {
        this.ten_the_loai = ten_the_loai;
    }

    public String getMo_ta() {
        return mo_ta;
    }

    public void setMo_ta(String mo_ta) {
        this.mo_ta = mo_ta;
    }
    @Override
    public String toString() {
        return ma_the_loai + " | " + ten_the_loai + " | " + mo_ta;
    }


}
