package com.example;

import java.util.Scanner;

public class QuanLyPhieuNhap {
    private PhieuNhap[] ds = new PhieuNhap[500];
    private int soLuong = 0;
    private QuanLyNhaCungCap qlNCC; // để liên kết với QL nhà cung cấp

    public QuanLyPhieuNhap(QuanLyNhaCungCap qlNCC) {
        this.qlNCC = qlNCC;
    }

    public boolean them(PhieuNhap pn) {
        if (soLuong < ds.length) {
            ds[soLuong++] = pn;
            return true;
        }
        return false;
    }

    public void hienThi() {
        if (soLuong == 0) {
            System.out.println("Chua co phieu nhap.");
            return;
        }
        System.out.println("+--------------------+-----------------+-----------------+-----------------+-----------------+");
        System.out.printf("%-10s %-12s %-10s %12s\n", "Mã PN", "Ngày", "MãNCC", "Tổng tiền");
        System.out.println("+--------------------+-----------------+-----------------+-----------------+-----------------+");
        for (int i = 0; i < soLuong; i++) {
            System.out.println(ds[i]);
            ds[i].hienThiChiTiet();
        }
    }

    public int timTheoMa(String ma) {
        for (int i = 0; i < soLuong; i++) {
            if (ds[i].getMaPhieu().equalsIgnoreCase(ma)) return i;
        }
        return -1;
    }

    public PhieuNhap layTheoMa(String ma) {
        int idx = timTheoMa(ma);
        if (idx != -1) return ds[idx];
        return null;
    }

    public boolean xoa(String ma) {
        int idx = timTheoMa(ma);
        if (idx != -1) {
            for (int i = idx; i < soLuong - 1; i++) ds[i] = ds[i + 1];
            ds[--soLuong] = null;
            return true;
        }
        return false;
    }

    public void menuSua(Scanner sc) {
        System.out.print("Nhap ma phieu can sua: ");
        String ma = sc.nextLine();
        int idx = timTheoMa(ma);

        if (idx == -1) {
            System.out.println("Khong tim thay phieu nhap co ma " + ma);
            return;
        }

        PhieuNhap pn = ds[idx];
        int chon;
        do {
            System.out.println("\n--- MENU SUA PHIEU NHAP ---");
            System.out.println("1. Sua ngay nhap");
            System.out.println("2. Sua ma nha cung cap");
            System.out.println("3. Sua chi tiet phieu nhap");
            System.out.println("4. Thoat sua");
            System.out.print("Chon: ");
            chon = Integer.parseInt(sc.nextLine());

            switch (chon) {
                case 1:
                    System.out.print("Nhap ngay moi: ");
                    String ngay = sc.nextLine();
                    pn.setNgayNhap(ngay);
                    System.out.println(" Da cap nhat ngay nhap.");
                    break;
                case 2:
                    System.out.print("Nhap ma NCC moi: ");
                    String maNCC = sc.nextLine();
                    pn.suaMaNhaCungCap(maNCC);
                    System.out.println(" Da cap nhat ma nha cung cap.");
                    break;
                case 3:
                    System.out.println(" GGoi den chuc nang sua chi tiet trong phieu nhap...");
                    pn.suaChiTiet(sc); // ban can co ham nay trong class PhieuNhap
                    break;
                case 4:
                    System.out.println("Thoat menu sua.");
                    break;
                default:
                    System.out.println("LLua chon khong hop le!");
            }
        } while (chon != 4);
    }
    // dùng bởi IOFile
    public PhieuNhap[] getArray() { return ds; }
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int n) { this.soLuong = n; }
    public QuanLyNhaCungCap getQlNCC() { return qlNCC; }
}
