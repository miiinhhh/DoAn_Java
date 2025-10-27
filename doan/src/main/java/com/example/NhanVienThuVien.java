package com.example;

import java.util.Scanner;

public class NhanVienThuVien extends NhanVien {
    private String chucVu;

    public NhanVienThuVien() {
        super();
    }

    public NhanVienThuVien(String mnv, String ho, String ten, String gioitinh, String ngaysinh, String sdt, String chucVu) {
        super(mnv, ho, ten, gioitinh, ngaysinh, sdt);
        this.chucVu = chucVu;
    }

    public String getChucVu() { return chucVu; }
    public void setChucVu(String chucVu) { this.chucVu = chucVu; }

    @Override
    public void nhap() {
        super.nhap();
        System.out.print("Nhap chuc vu: ");
        chucVu = sc.nextLine();
    }

    @Override
    public void xuat() {
        System.out.printf("| %-10s | %-10s | %-15s | %-9s | %-12s | %-15s | %-12s |\n",
                mnv, ho, ten, gioitinh, ngaysinh, sdt, chucVu == null ? "" : chucVu);
    }

    @Override
    public String toString() {
        return super.toString() + "," + (chucVu == null ? "" : chucVu);
    }

    public static NhanVienThuVien parse(String[] parts) {
        String mnv = parts[0].trim();
        String ho = parts[1].trim();
        String ten = parts[2].trim();
        String gioitinh = parts[3].trim();
        String ngaysinh = parts[4].trim();
        String sdt = parts[5].trim();
        String chucVu = parts.length >= 7 ? parts[6].trim() : "";
        return new NhanVienThuVien(mnv, ho, ten, gioitinh, ngaysinh, sdt, chucVu);
    }
}


