package com.example;

import java.util.*;
import java.io.*;
public class NhaXuatBan {
    private String ma_nxb;
    private String ten_nxb;
    public NhaXuatBan(){
        
    }
    public NhaXuatBan(String ma_nxb,String ten_nxb){
        this.ma_nxb=ma_nxb;
        this.ten_nxb=ten_nxb;
    }
    
    public String getMa_nxb() {
        return ma_nxb;
    }

    public void setMa_nxb(String ma_nxb) {
        this.ma_nxb = ma_nxb;
    }

    public String getTen_nxb() {
        return ten_nxb;
    }

    public void setTen_nxb(String ten_nxb) {
        this.ten_nxb = ten_nxb;
    }
    @Override
    public String toString() {
        return ma_nxb + " | " + ten_nxb;
    }
    public String toFileString(){
        return ma_nxb + ", " + ten_nxb;
    }
}
