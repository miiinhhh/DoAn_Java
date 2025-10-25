package com.example;

import java.util.Scanner;

//import java.util.Date;
//import java.text.SimpleDateFormat;

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
    public void suaChiTiet(Scanner sc) {
        if (soChiTiet == 0) {
            System.out.println("Không có chi tiết nào để sửa!");
            return;
        }

        System.out.print("Nhập mã hàng cần sửa: ");
        String maHang = sc.nextLine();

        int idx = -1;
        for (int i = 0; i < soChiTiet; i++) {
            if (chiTiet[i].getMaHang().equalsIgnoreCase(maHang)) {
                idx = i;
                break;
            }
        }

        if (idx == -1) {
            System.out.println(" Khong tim thay hang co ma: " + maHang);
            return;
        }

        ChiTietPhieuNhap ct = chiTiet[idx];
        int chon;
        do {
            System.out.println("\n--- MENU SUA CHI TIET ---");
            System.out.println("1. Sua ten hang");
            System.out.println("2. Sua so luong");
            System.out.println("3. Sua don gia");
            System.out.println("4. Thoat");
            System.out.print("Chon: ");
            chon = Integer.parseInt(sc.nextLine());

            switch (chon) {
                case 1:
                    System.out.print("Nhap ten hang moi: ");
                    ct.setTenHang(sc.nextLine());
                    System.out.println(" Da cap nha ten hang.");
                    break;
                case 2:
                    System.out.print("Nhap so luong moi: ");
                    ct.setSoLuong(Integer.parseInt(sc.nextLine()));
                    System.out.println(" Da cap nhat so luong.");
                    break;
                case 3:
                    System.out.print("Nhap don gia moi: ");
                    ct.setDonGia(Double.parseDouble(sc.nextLine()));
                    System.out.println(" Da cap nhat don gia.");
                    break;
                case 4:
                    System.out.println("Thoat sua chi tiet.");
                    break;
                default:
                    System.out.println("LLua chon khong hop le!");
            }
        } while (chon != 4);
    }
    public void suaMaNhaCungCap(String maNCCMoi) {
        if (nhaCungCap != null) {
            nhaCungCap.setMaNCC(maNCCMoi);
        }else{
            System.out.println("Nha cung cap la null, khong the sua.");
        }
    }
}
