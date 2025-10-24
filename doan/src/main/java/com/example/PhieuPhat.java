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
    public PhieuPhat(String maphieuphat, String madocgia, String maphieumuon, String maphat, DanhSachQuyDinhPhat dsqdp){
        this.maphieuphat=maphieuphat;
        this.madocgia=madocgia;
        this.maphieumuon=maphieumuon;
        this.maphat=maphat;
        QuyDinhPhat qdp = dsqdp.layQuyDinhPhatTuMa(maphat);
        if(qdp != null){
            this.tienphat = qdp.getTienPhat();
        }
        else{
            this.tienphat =0;
            System.err.println("Canh bao ma phat " + maphat + " khong hop le, tien phat bang 0.");
        }
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
    public void nhap(DanhSachQuyDinhPhat dsqdp){
       System.out.print("Nhap ma phieu phat: ");
       maphieuphat = sc.nextLine().trim();
       System.out.print("Nhap ma doc gia: ");
       madocgia = sc.nextLine().trim();
       System.out.print("Nhap ma phieu muon: ");
       maphieumuon = sc.nextLine().trim();
       System.out.print("Nhap ma phat: ");
       maphat = sc.nextLine().trim();
       QuyDinhPhat qdp = dsqdp == null ? null : dsqdp.layQuyDinhPhatTuMa(maphat);
       if(qdp != null){
        tienphat = qdp.getTienPhat();
       }
       else {
        tienphat = 0;
        System.err.println("Canh bao ma phat "+ maphat +" khong hop le, tien phat bang 0.");
       }
    }
    public void xuat(){
        System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-10d |%n", maphieuphat,madocgia,maphieumuon,maphat,tienphat);
    }
    @Override public String toString(){
        return String.join(",", maphieuphat == null ? "" : maphieuphat,
                                 madocgia == null ? "" : madocgia,
                                 maphieumuon == null ? "" : maphieumuon,
                                 maphat == null ? "" : maphat,
                                 String.valueOf(tienphat));
    }
    public String toFile(){
        return String.join(",", maphieuphat == null ? "" : maphieuphat, madocgia == null ? "" : madocgia, maphieumuon == null ? "" : maphieumuon,maphat == null ? "" : maphat, String.valueOf(tienphat));
    } 
}