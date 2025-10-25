package com.example;

public class QuanLyNhaCungCap {
    private NhaCungCap[] ds = new NhaCungCap[200];
    private int soLuong = 0;

    public boolean them(NhaCungCap ncc) {
        if (soLuong < ds.length) {
            ds[soLuong++] = ncc;
            return true;
        } 
        return false;
    }

    public void hienThi() {
        if (soLuong == 0) {
            System.out.println("Chua co nha cung cap.");
            return;
        }
        System.out.println("+--------------------+-----------------+-----------------+-----------------+-----------------+");
        System.out.printf("%-10s %-20s %-25s %-15s\n", "Mã NCC", "Tên NCC", "Địa chỉ", "SĐT");
        System.out.println("+--------------------+-----------------+-----------------+-----------------+-----------------+");
        for (int i = 0; i < soLuong; i++) {
            System.out.println(ds[i]);
        }
    }

    public int timTheoMa(String ma) {
        for (int i = 0; i < soLuong; i++) {
            if (ds[i].getMaNCC().equalsIgnoreCase(ma)) return i;
        }
        return -1;
    }

    public NhaCungCap layTheoMa(String ma) {
        int idx = timTheoMa(ma);
        if (idx != -1) return ds[idx];
        return null;
    }

    public boolean sua(String ma, String tenMoi, String diaChiMoi, String sdtMoi) {
        int idx = timTheoMa(ma);
        if (idx != -1) {
            ds[idx].setTenNCC(tenMoi);
            ds[idx].setDiaChi(diaChiMoi);
            ds[idx].setSoDienThoai(sdtMoi);
            return true;
        }
        return false;
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
    public NhaCungCap[] getArray() { return ds; }
    public void setSoLuong(int n) { this.soLuong = n; }
    public int getSoLuong() { return soLuong; }
}
