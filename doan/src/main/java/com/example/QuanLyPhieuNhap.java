package com.example;

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

    // dùng bởi IOFile
    public PhieuNhap[] getArray() { return ds; }
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int n) { this.soLuong = n; }
    public QuanLyNhaCungCap getQlNCC() { return qlNCC; }
}
