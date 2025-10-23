package com.example;

public class ChiTietPhieuNhap {
    private String maHang;
    private String tenHang;
    private int soLuong;
    private double donGia;

    public ChiTietPhieuNhap() {}

    public ChiTietPhieuNhap(String maHang, String tenHang, int soLuong, double donGia) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public String getMaHang() { return maHang; }
    public void setMaHang(String maHang) { this.maHang = maHang; }

    public String getTenHang() { return tenHang; }
    public void setTenHang(String tenHang) { this.tenHang = tenHang; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    public double getDonGia() { return donGia; }
    public void setDonGia(double donGia) { this.donGia = donGia; }

    public double thanhTien() {
        return soLuong * donGia;
    }

    @Override
    public String toString() {
        return String.format("%-8s %-15s %6d %12.2f %12.2f", maHang, tenHang, soLuong, donGia, thanhTien());
    }
}