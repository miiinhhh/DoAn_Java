package com.example;
import java.util.Scanner;

public class PhieuPhat{
    private String maphieuphat;
    private String madocgia;
    private String maphieumuon;
    private String maphat;
    private int tienphat;
    public PhieuPhat(){
        maphieuphat="";
        madocgia="";
        maphieumuon="";
        maphat="";
        tienphat=0;
    }
public PhieuPhat(String maphieuphat, String madocgia, String maphieumuon, String maphat, int tienphat){
    this.maphieuphat = maphieuphat.trim();
    this.madocgia = madocgia.trim();
    this.maphieumuon = maphieumuon.trim();
    this.maphat = maphat.trim();
    this.tienphat = tienphat;
}
    public PhieuPhat(PhieuPhat pp){
        this.maphieuphat = pp.maphieuphat;
        this.madocgia = pp.madocgia;
        this.maphieumuon = pp.maphieumuon;
        this.maphat = pp.maphat;
        this.tienphat = pp.tienphat;
    }
    public String getMaPhieuPhat(){ return maphieuphat;}
    public String getMaDocGia(){ return madocgia; }
    public String getMaPhieuMuon(){ return maphieumuon; }
    public String getMaPhat(){ return maphat; }
    public int getTienPhat(){ return tienphat; }
    public void setMaPhieuPhat(String maphieuphat){ this.maphieuphat = maphieuphat; }
    public void setMaDocGia(String madocgia){this.madocgia= madocgia;}
    public void setMaPhieuMuon(String maphieumuon){this.maphieumuon= maphieumuon;}
    public void setMaPhat(String maphat, DanhSachQuyDinhPhat dsqdp){
        this.maphat = maphat;
        QuyDinhPhat qdp = dsqdp == null ? null : dsqdp.layQuyDinhPhatTuMa(maphat);
        this.tienphat = qdp != null ? qdp.getTienPhat() : 0;
    }
    public void setTienPhat(int tienphat){this.tienphat= tienphat;}

    Scanner sc = new Scanner(System.in);
public void nhap(DanhSachQuyDinhPhat dsqdp, DanhSachDocGia dsDG, DanhSachPhieuMuon dspm){ 
   Scanner sc = new Scanner(System.in);
   System.out.print("Nhap ma phieu phat: ");
   maphieuphat = sc.nextLine().trim();
   while (true) {
       System.out.print("Nhap ma doc gia: ");
       madocgia = sc.nextLine().trim();
       if (dsDG.timkiemma(madocgia) == -1) {
           System.out.println("Loi: Ma doc gia KHONG TON TAI. Vui long nhap lai!");
       } else {
           break;
       }
   }
   while (true) {
       System.out.print("Nhap ma phieu muon: ");
       maphieumuon = sc.nextLine().trim();
       if (dspm.timkiemma(maphieumuon) == -1) {
           System.out.println("Loi: Ma phieu muon KHONG TON TAI. Vui long nhap lai!");
       } else {
           break;
       }
   }
   while (true) {
       System.out.print("Nhap ma phat: ");
       maphat = sc.nextLine().trim();
       QuyDinhPhat qdp = dsqdp == null ? null : dsqdp.layQuyDinhPhatTuMa(maphat);

       if (qdp != null) {
           tienphat = qdp.getTienPhat();
           break;
       } else {
           System.err.println("Canh bao: Ma phat KHONG TON TAI. Vui long nhap lai!");
           tienphat = 0;
       }
   }
}
    public void xuat(){
        System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-10d |%n", maphieuphat,madocgia,maphieumuon,maphat,tienphat);
    }
    public String toFile(){
        return String.join(",", maphieuphat == null ? "" : maphieuphat, madocgia == null ? "" : madocgia, maphieumuon == null ? "" : maphieumuon,maphat == null ? "" : maphat, String.valueOf(tienphat));
    } 
}