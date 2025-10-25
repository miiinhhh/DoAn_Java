package com.example;
import java.util.Scanner;

public class DocGia {
    private String maDocGia;
    private String ho;
    private String ten;
    private String gioiTinh;
    private String ngaySinh;
    private String sdt;
    public DocGia() {}
    public DocGia(String maDocGia, String ho, String ten, String gioiTinh, String ngaySinh, String sdt) {
        this.maDocGia = maDocGia;
        this.ho = ho;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.sdt = sdt;
    }
    public DocGia(DocGia dg) {
        this.maDocGia = dg.maDocGia;
        this.ho = dg.ho;
        this.ten = dg.ten;
        this.gioiTinh = dg.gioiTinh;
        this.ngaySinh = dg.ngaySinh;
        this.sdt = dg.sdt;
    }
    public String getMaDocGia() { return maDocGia; }
    public String getHo() { return ho; }
    public String getTen() { return ten; }
    public String getGioiTinh() { return gioiTinh; }
    public String getNgaySinh() { return ngaySinh; }
    public String getSdt() { return sdt; }
    public void setMaDocGia(String maDocGia) { this.maDocGia = maDocGia; }
    public void setHo(String ho) { this.ho = ho; }
    public void setTen(String ten) { this.ten = ten; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }
    public void setNgaySinh(String ngaySinh) { this.ngaySinh = ngaySinh; }
    public void setSdt(String sdt) { this.sdt = sdt; }
    Scanner sc = new Scanner(System.in);
    public void nhap() {
        System.out.print("Nhap ma doc gia: ");
        maDocGia = sc.nextLine();
        System.out.print("Nhap ho: ");
        ho = sc.nextLine();
        System.out.print("Nhap ten: ");
        ten = sc.nextLine();
        System.out.print("Nhap gioi tinh: ");
        gioiTinh = sc.nextLine();
        System.out.print("Nhap ngay sinh: ");
        ngaySinh = sc.nextLine();
        System.out.print("Nhap so dien thoai: ");
        sdt = sc.nextLine();
    }
    public void xuat() {
        System.out.printf("| %-10s | %-10s | %-15s | %-9s | %-12s | %-15s |\n",
                maDocGia, ho, ten, gioiTinh, ngaySinh, sdt);
    }
    public String toString() {
        return maDocGia + "," + ho + "," + ten + "," + gioiTinh + "," + ngaySinh + "," + sdt;
    }
    public static DocGia parse(String line) {
        String[] parts = line.split(",");
        if (parts.length < 6) return null;
        return new DocGia(parts[0].trim(), parts[1].trim(), parts[2].trim(),
                          parts[3].trim(), parts[4].trim(), parts[5].trim());
    }
}

