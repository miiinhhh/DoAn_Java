package com.example;

import java.util.*;
import java.io.*;

public class DanhSachDocGia {
    private int n;
    DocGia[] ds = new DocGia[0];
    Scanner sc = new Scanner(System.in);

    public DanhSachDocGia() {}

    public void nhap() {
        System.out.print("Nhap so luong doc gia: ");
        n = sc.nextInt();
        sc.nextLine();
        ds = new DocGia[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Nhap thong tin doc gia thu " + (i + 1) + ":");
            DocGia dg = new DocGia();
            dg.nhap();
            ds[i] = dg;
        }
    }

    public void xuat() {
        if (n == 0) {
            System.out.println("Danh sach rong!");
            return;
        }
        xuatd();
        for (DocGia dg : ds)
            dg.xuat();
        xuatc();
    }

    public void xuatd() {
        System.out.printf("+------------+------------+-----------------+-----------+--------------+-----------------+\n");
        System.out.printf("| %-10s | %-10s | %-15s | %-9s | %-12s | %-15s |\n", "Ma DG", "Ho", "Ten", "Gioi tinh", "Ngay sinh", "SDT");
        System.out.printf("+------------+------------+-----------------+-----------+--------------+-----------------+\n");
    }

    public void xuatc() {
        System.out.printf("+------------+------------+-----------------+-----------+--------------+-----------------+\n");
    }

    public void sua() {
        System.out.print("Nhap ma doc gia can sua: ");
        String ma = sc.nextLine();
        int index = -1;
        for (int i = 0; i < n; i++) {
            if (ds[i].getMaDocGia().equalsIgnoreCase(ma)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Khong tim thay doc gia co ma " + ma);
            return;
        }
        while (true) {
            System.out.println("1. Sua ho");
            System.out.println("2. Sua ten");
            System.out.println("3. Sua gioi tinh");
            System.out.println("4. Sua ngay sinh");
            System.out.println("5. Sua sdt");
            System.out.println("6. Thoat");
            System.out.print("Chon: ");
            int chon = sc.nextInt();
            sc.nextLine();
            if (chon == 6) break;
            switch (chon) {
                case 1 -> {
                    System.out.print("Nhap ho moi: ");
                    ds[index].setHo(sc.nextLine());
                }
                case 2 -> {
                    System.out.print("Nhap ten moi: ");
                    ds[index].setTen(sc.nextLine());
                }
                case 3 -> {
                    System.out.print("Nhap gioi tinh moi: ");
                    ds[index].setGioiTinh(sc.nextLine());
                }
                case 4 -> {
                    System.out.print("Nhap ngay sinh moi: ");
                    ds[index].setNgaySinh(sc.nextLine());
                }
                case 5 -> {
                    System.out.print("Nhap sdt moi: ");
                    ds[index].setSdt(sc.nextLine());
                }
                default -> System.out.println("Lua chon khong hop le!");
            }
        }
    }

    public void them() {
        while (true) {
            DocGia dg = new DocGia();
            System.out.println("Nhap thong tin doc gia moi:");
            dg.nhap();
            ds = Arrays.copyOf(ds, n + 1);
            ds[n] = dg;
            n++;
            System.out.print("Ban co muon them tiep khong (y/n): ");
            String tiep = sc.nextLine();
            if (!tiep.equalsIgnoreCase("y")) break;
        }
    }

    public void xoa() {
        System.out.print("Nhap ma doc gia can xoa: ");
        String ma = sc.nextLine();
        int index = -1;
        for (int i = 0; i < n; i++) {
            if (ds[i].getMaDocGia().equalsIgnoreCase(ma)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Khong tim thay doc gia co ma " + ma);
            return;
        }
        for (int i = index; i < n - 1; i++) {
            ds[i] = ds[i + 1];
        }
        n--;
        ds = Arrays.copyOf(ds, n);
        System.out.println("Da xoa doc gia co ma " + ma);
    }

    public void ghifile() {
        try (PrintWriter pw = new PrintWriter(new File("DocGia.txt"))) {
            for (DocGia dg : ds)
                pw.println(dg.toString());
            System.out.println("Da ghi vao file DocGia.txt");
        } catch (Exception e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    public void docfile() {
        File file = new File("DocGia.txt");
        if (!file.exists()) {
            System.out.println("File khong ton tai!");
            return;
        }
        try (Scanner f = new Scanner(file)) {
            n = 0;
            ds = new DocGia[0];
            while (f.hasNextLine()) {
                String[] p = f.nextLine().split(",");
                if (p.length == 6) {
                    DocGia dg = new DocGia(p[0], p[1], p[2], p[3], p[4], p[5]);
                    ds = Arrays.copyOf(ds, n + 1);
                    ds[n] = dg;
                    n++;
                }
            }
            System.out.println("Da doc file DocGia.txt");
        } catch (Exception e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }
    }

    public void indanhsach() { xuat(); }
    public void tim() {
        System.out.print("Nhap ma doc gia can tim: ");
        String ma = sc.nextLine();
        for (DocGia dg : ds) {
            if (dg.getMaDocGia().equalsIgnoreCase(ma)) {
                xuatd();
                dg.xuat();
                xuatc();
                return;
            }
        }
        System.out.println("Khong tim thay doc gia co ma: " + ma);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DanhSachDocGia ds = new DanhSachDocGia();
        int chon;
        do {
            System.out.println("\n=== MENU QUAN LY DOC GIA ===");
            System.out.println("1. Nhap danh sach doc gia");
            System.out.println("2. Xuat danh sach doc gia");
            System.out.println("3. Them doc gia");
            System.out.println("4. Sua thong tin doc gia");
            System.out.println("5. Tim doc gia theo ma");
            System.out.println("6. Xoa doc gia");
            System.out.println("7. Ghi file");
            System.out.println("8. Doc file");
            System.out.println("0. Thoat");
            System.out.print("Chon chuc nang: ");
            chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1 -> ds.nhap();
                case 2 -> ds.indanhsach();
                case 3 -> ds.them();
                case 4 -> ds.sua();
                case 5 -> ds.tim();
                case 6 -> ds.xoa();
                case 7 -> ds.ghifile();
                case 8 -> ds.docfile();
                case 0 -> System.out.println("Thoat chuong trinh!");
                default -> System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
        sc.close();
    }
}
