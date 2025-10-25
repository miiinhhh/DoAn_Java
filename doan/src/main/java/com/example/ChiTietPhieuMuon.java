package com.example;
import java.util.Scanner;

public class ChiTietPhieuMuon{
     private String maphieumuon;
     private String masach;
     private int soluong;
     public ChiTietPhieuMuon(){
        maphieumuon ="";
        masach ="";
        soluong =0;
     }
     public ChiTietPhieuMuon(String maphieumuon,String masach, int soluong){
        this.maphieumuon = maphieumuon;
        this.masach = masach;
        this.soluong = soluong;
     }
     public ChiTietPhieuMuon(ChiTietPhieuMuon ctpm){
        this.maphieumuon = ctpm.maphieumuon;
        this.masach = ctpm.masach;
        this.soluong = ctpm.soluong;
     }
     public String getMaPhieuMuon(){ return maphieumuon; }
     public String getMaSach(){ return masach; }
     public int getSoLuong(){ return soluong; }
     public void setMaPhieuMuon(String maphieumuon){ this.maphieumuon = maphieumuon; }
     public void setMaSach(String masach){ this.masach = masach; }
     public void setSoLuong(int soluong) { this.soluong = soluong;}

     Scanner sc = new Scanner(System.in);
public void nhap(DanhSachPhieuMuon dspm, DanhSachSach dss){
    System.out.print("Nhap ma phieu muon: ");
    while (true) {
        maphieumuon = sc.nextLine().trim();
        if (maphieumuon.isEmpty()) {
            System.out.print("Khong duoc de trong. Nhap ma phieu muon: ");
            continue;
        }
        if (dspm != null && dspm.timkiemma(maphieumuon) == -1) {
            System.out.print("Ma phieu muon khong ton tai. Nhap lai: ");
            continue;
        }
        break;
    }
    

    System.out.print("Nhap ma sach: ");
    while (true) {
        masach = sc.nextLine().trim();
        if (masach.isEmpty()) {
            System.out.print("Khong duoc de trong. Nhap ma sach: ");
            continue;
        }
        if (dss != null && dss.timkiemma(masach) == -1) { 
            System.out.print("Ma sach khong ton tai. Nhap lai: ");
            continue;
        }
        break;
      }
    while(true){
        System.out.print("Nhap so luong: ");
        String s = sc.nextLine().trim();
        try{
            soluong = Integer.parseInt(s);
            if(soluong < 0){ 
                System.out.println("Vui long nhap so luong >= 0 !!"); 
                continue;
            }
            break;
        }catch(NumberFormatException e){
            System.out.println("Vui long nhap so nguyen");
        }
      }
   }
     public void xuat(){
        System.out.printf("| %-15s | %-15s | %-10d |%n", maphieumuon, masach, soluong);
    } 
     public String toFile(){
        String a = maphieumuon == null ? "" : maphieumuon;
        String b = masach == null ? "" : masach;
        String c = String.valueOf(soluong);
        return String.join(",", a, b, c);
     }
    @Override
     public String toString(){
        return String.format("%s | %s | %d", maphieumuon, masach, soluong);
     }
}