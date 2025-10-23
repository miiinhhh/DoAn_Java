package com.example;

import java.util.Date;
import java.text.SimpleDateFormat;

public class PhieuNhap {
    private String maPhieu;
    private String ngayNhap; // dùng String để đơn giản (format "yyyy-MM-dd")
    private NhaCungCap nhaCungCap;

    private ChiTietPhieuNhap[] chiTiet = new ChiTietPhieuNhap[500];
    private int soChiTiet = 0;

    public PhieuNhap() {}

    public PhieuNhap(String maPhieu, String ngayNhap, NhaCungCap nhaCungCap) {
        this.maPhieu = maPhieu;
        this.ngayNhap = ngayNhap;
        this.nhaCungCap = nhaCungCap;
    }

    public String getMaPhieu() { return maPhieu; }
    public void setMaPhieu(String maPhieu) { this.maPhieu = maPhieu; }

    public String getNgayNhap() { return ngayNhap; }
    public void setNgayNhap(String ngayNhap) { this.ngayNhap = ngayNhap; }

    public NhaCungCap getNhaCungCap() { return nhaCungCap; }
    public void setNhaCungCap(NhaCungCap nhaCungCap) { this.nhaCungCap = nhaCungCap; }

    public void themChiTiet(ChiTietPhieuNhap ct) {
        if (soChiTiet < chiTiet.length) {
            chiTiet[soChiTiet++] = ct;
        } else {
            System.out.println("Danh sach chi tiet da day!");
        }
    }

    public ChiTietPhieuNhap[] getChiTiet() { return chiTiet; }
    public int getSoChiTiet() { return soChiTiet; }

    public double tinhTongTien() {
        double s = 0;
        for (int i = 0; i < soChiTiet; i++) {
            s += chiTiet[i].thanhTien();
        }
        return s;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-12s %-10s %12.2f", maPhieu, ngayNhap, (nhaCungCap!=null? nhaCungCap.getMaNCC() : "null"), tinhTongTien());
    }

    public void hienThiChiTiet() {
        if (soChiTiet == 0) {
            System.out.println("   (Khong co chi tiet)");
            return;
        }
        System.out.printf("   %-8s %-15s %6s %12s %12s\n", "Ma HH", "TTen hang", "SL", "Don gia", "Thanh tien");
        for (int i = 0; i < soChiTiet; i++) {
            System.out.println("   " + chiTiet[i]);
        }
    }
}
