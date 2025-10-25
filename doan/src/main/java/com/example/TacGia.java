package com.example;

import java.util.*;
import java.io.*;
public class TacGia {
    private String ma_tac_gia;
    private String ho_ten;
    private String gioi_tinh;
    private Ngay ngay_sinh;
    private ArrayList<Sach> dsSach = new ArrayList<>(); //danh sach sach cua tac gia
    public TacGia(){
        
    }
    public TacGia(String ma_tac_gia,String ho_ten,String gioi_tinh,Ngay ngay_sinh){
        this.ma_tac_gia = ma_tac_gia;
        this.ho_ten=ho_ten;
        this.gioi_tinh=gioi_tinh;
        this.ngay_sinh=ngay_sinh;
    }
    
    //giu rang buoc 2 chieu
    public void themSach(Sach s){
        if(!dsSach.contains(s)) dsSach.add(s);
    }

    public void xoaSach(Sach s){
        dsSach.remove(s);
    }

    public ArrayList<Sach> getDsSach(){
        return dsSach;
    }
    
    public void xoaSachTheoMa(String maSach) {
        if (maSach == null) return;
        Iterator<Sach> it = dsSach.iterator();
        while (it.hasNext()) {
            Sach x = it.next();
            if (x.getMa_sach().equals(maSach)) {
                it.remove();
                break; // nếu chỉ có 1 quyển cần xóa, break; nếu có thể có nhiều thì bỏ break
            }
        }
    }
    
    public String getMa_tac_gia() {
        return ma_tac_gia;
    }

    public void setMa_tac_gia(String ma_tac_gia) {
        this.ma_tac_gia = ma_tac_gia;
    }

    public String getHo_ten() {
        return ho_ten;
    }

    public void setHo_ten(String ho_ten) {
        this.ho_ten = ho_ten;
    }

    public String getGioi_tinh() {
        return gioi_tinh;
    }

    public void setGioi_tinh(String gioi_tinh) {
        this.gioi_tinh = gioi_tinh;
    }

    public Ngay getNgay_sinh() {
        return ngay_sinh;
    }

    public void setNgay_sinh(Ngay ngay_sinh) {
        this.ngay_sinh = ngay_sinh;
    }
    @Override
    public String toString() {
        return ma_tac_gia + " | " + ho_ten + " | " + gioi_tinh + " | " + ngay_sinh;
    }

}
