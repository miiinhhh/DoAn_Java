package com.example;

import java.util.*;
import java.io.*;
class Ngay {
    int ngay;
    int thang;
    int nam;
    public Ngay(){
        
    }
    public Ngay(int ngay,int thang,int nam){
        this.ngay = ngay;
        this.thang = thang;
        this.nam = nam;
    }
    public static Ngay parseNgay(String s) {
    String[] parts = s.split("/");
    if (parts.length != 3) {
        System.out.println("Dinh dang ngay khong hop le: " + s);
        return null; 
    }

    int d = Integer.parseInt(parts[0]);
    int m = Integer.parseInt(parts[1]);
    int y = Integer.parseInt(parts[2]);

    if (m < 1 || m > 12 || d < 1 || d > 31) {
        System.out.println("Ngay hoac thang khong hop le: " + s);
        return null;
    }

    if ((m == 4 || m == 6 || m == 9 || m == 11) && d > 30) {
        System.out.println("Thang " + m + " chi co 30 ngay!");
        return null;
    }

    if (m == 2) {
        boolean namNhuan = (y % 400 == 0) || (y % 4 == 0 && y % 100 != 0);
        int maxNgay = namNhuan ? 29 : 28;
        if (d > maxNgay) {
            System.out.println("Thang 2 nam " + y + " chi co " + maxNgay + " ngay!");
            return null;
        }
    }

    return new Ngay(d, m, y);
}

    @Override
    public String toString() {
        return ngay + "/" + thang + "/" + nam;
    }
}
public class Sach {
    private String ma_sach;
    private String ten_sach;
    private String ma_the_loai;
    private String ma_tac_gia;
    private String ma_nxb;
    private Ngay ngay_xuat_ban;
    public Sach(){
        
    }
    public Sach(String ma_sach,String ten_sach,String ma_the_loai,String ma_tac_gia,String ma_nxb,Ngay ngay_xuat_ban){
        this.ma_sach = ma_sach;
        this.ten_sach = ten_sach;
        this.ma_the_loai = ma_the_loai;
        this.ma_tac_gia = ma_tac_gia;
        this.ma_nxb = ma_nxb;
        this.ngay_xuat_ban = ngay_xuat_ban;
    }
    public String getMa_sach() {
        return ma_sach;
    }

    public void setMa_sach(String ma_sach) {
        this.ma_sach = ma_sach;
    }

    public String getTen_sach() {
        return ten_sach;
    }

    public void setTen_sach(String ten_sach) {
        this.ten_sach = ten_sach;
    }

    public String getMa_the_loai() {
        return ma_the_loai;
    }

    public void setMa_the_loai(String ma_the_loai) {
        this.ma_the_loai = ma_the_loai;
    }

    public String getMa_tac_gia() {
        return ma_tac_gia;
    }

    public void setMa_tac_gia(String ma_tac_gia) {
        this.ma_tac_gia = ma_tac_gia;
    }


    public String getMa_nxb() {
        return ma_nxb;
    }

    public void setMa_nxb(String ma_nxb) {
        this.ma_nxb = ma_nxb;
    }

    public Ngay getNgay_xuat_ban() {
        return ngay_xuat_ban;
    }

    public void setNgay_xuat_ban(Ngay ngay_xuat_ban) {
        this.ngay_xuat_ban = ngay_xuat_ban;
    }
    public String toFileString() {
        return "Thuong, " + ma_sach + ", " + ten_sach + ", " + ma_the_loai + ", " + ma_tac_gia
            + ", " + ma_nxb + ", " + ngay_xuat_ban + ", -";
    }
    @Override
    public String toString() {
        // Định dạng hiển thị cơ bản cho Sách Thường
        return String.format("| %-6s | %-20s | %-4s | %-4s | %-4s | %s |", 
            this.ma_sach, this.ten_sach, this.ma_the_loai, this.ma_tac_gia, 
            this.ma_nxb, this.ngay_xuat_ban.toString());
    }
}
