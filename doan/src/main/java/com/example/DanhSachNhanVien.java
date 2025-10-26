package com.example;
import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Scanner;

public class DanhSachNhanVien {
    private int n;
    NhanVien[] ds = new NhanVien[0];
    Scanner sc = new Scanner(System.in);

    public DanhSachNhanVien() {}

    public void nhap() {
        System.out.print("Nhap so luong nhan vien: ");
        n = sc.nextInt();
        sc.nextLine();
        ds = new NhanVien[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Nhap thong tin nhan vien thu " + (i + 1) + ":");
            NhanVien nv = new NhanVien();
            nv.nhap();
            ds[i] = nv;
        }
    }

    public void xuat() {
        if (n == 0) {
            System.out.println("Danh sach rong!");
            return;
        }
        xuatd();
        for (NhanVien nv : ds)
            nv.xuat();
        xuatc();
    }

    public void xuatd() {
        System.out.printf("+------------+------------+-----------------+-----------+--------------+-----------------+\n");
        System.out.printf("| %-10s | %-10s | %-15s | %-9s | %-12s | %-15s |\n", "Ma Nv", "Ho", "Ten", "Gioi tinh", "Ngay sinh", "SDT");
        System.out.printf("+------------+------------+-----------------+-----------+--------------+-----------------+\n");
    }

    public void xuatc() {
        System.out.printf("+------------+------------+-----------------+-----------+--------------+-----------------+\n");
    }

    public void sua() {
        System.out.print("Nhap ma nhan vien can sua: ");
        String ma = sc.nextLine();
        int index = -1;
        for (int i = 0; i < n; i++) {
            if (ds[i].getMnv().equalsIgnoreCase(ma)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Khong tim thay nhan vien co ma " + ma);
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
                case 1:
                    System.out.print("Nhap ho moi: ");
                    ds[index].setHo(sc.nextLine());
                    break;
                case 2:
                    System.out.print("Nhap ten moi: ");
                    ds[index].setTen(sc.nextLine());
                    break;
                case 3:
                    System.out.print("Nhap gioi tinh moi: ");
                    ds[index].setGioitinh(sc.nextLine());
                    break;
                case 4:
                    System.out.print("Nhap ngay sinh moi (YYYY-MM-DD): ");
                    ds[index].setNgaysinh(sc.nextLine());
                    break;
                case 5:
                    System.out.print("Nhap sdt moi: ");
                    ds[index].setSdt(sc.nextLine());
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }

    public void timKiemTheoMaNhanVien() {
        System.out.print("Nhap ma nhan vien can tim: ");
        String ma = sc.nextLine();
        for (NhanVien nv : ds) {
            if (nv.getMnv().equalsIgnoreCase(ma)) {
                xuatd();
                nv.xuat();
                xuatc();
                return;
            }
        }
        System.out.println("Khong tim thay nhan vien co ma: " + ma);
    }

    public void timKiemTheoTen() {
        System.out.print("Nhap ten nhan vien can tim: ");
        String ten = sc.nextLine();
        boolean kt = false;
        for (NhanVien nv : ds) {
            if (nv.getTen().equalsIgnoreCase(ten)) {
                if (!kt) xuatd();
                nv.xuat();
                kt = true;
            }
        }
        if (kt) xuatc();
        else System.out.println("Khong tim thay nhan vien co ten: " + ten);
    }

    private int tinhTuoi(NhanVien nv) {
        LocalDate ns = LocalDate.parse(nv.getNgaySinh());
        LocalDate now = LocalDate.now();
        return Period.between(ns, now).getYears();
    }

    public void thongKeTheoGioiTinh() {
        int nam = 0, nu = 0;
        for (NhanVien nv : ds) {
            if (nv.getGioiTinh().equalsIgnoreCase("Nam"))
                nam++;
            else
                nu++;
        }
        System.out.println("So nhan vien nam: " + nam);
        System.out.println("So nhan vien nu: " + nu);
    }

    public void thongKeTheoTuoi() {
        int duoi30 = 0, bang30 = 0, tren30 = 0;
        for (NhanVien nv : ds) {
            int tuoi = tinhTuoi(nv);
            if (tuoi < 30) duoi30++;
            else if (tuoi == 30) bang30++;
            else tren30++;
        }
        System.out.println("So nhan vien duoi 30 tuoi: " + duoi30);
        System.out.println("So nhan vien 30 tuoi: " + bang30);
        System.out.println("So nhan vien tren 30 tuoi: " + tren30);
    }

    public void them() {
        while (true) {
            NhanVien nv = new NhanVien();
            System.out.println("Nhap thong tin nhan vien moi:");
            nv.nhap();
            ds = Arrays.copyOf(ds, n + 1);
            ds[n] = nv;
            n++;
            System.out.print("Ban co muon them tiep khong (y/n): ");
            String tiep = sc.nextLine();
            if (!tiep.equalsIgnoreCase("y")) break;
        }
    }

    public void xoa() {
        System.out.print("Nhap ma nhan vien can xoa: ");
        String ma = sc.nextLine();
        int index = -1;
        for (int i = 0; i < n; i++) {
            if (ds[i].getMnv().equalsIgnoreCase(ma)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Khong tim thay nhan vien co ma " + ma);
            return;
        }
        for (int i = index; i < n - 1; i++) {
            ds[i] = ds[i + 1];
        }
        n--;
        ds = Arrays.copyOf(ds, n);
        System.out.println("Da xoa nhan vien co ma " + ma);
    }

    public void ghiFile() {
        try (PrintWriter pw = new PrintWriter(new File("src/main/java/com/example/Nhanvien.txt"))) {
            for (NhanVien nv : ds)
                pw.println(nv.toString());
            System.out.println("Da ghi vao file Nhanvien.txt");
        } catch (Exception e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    public void docFile() {
        File file = new File("src/main/java/com/example/Nhanvien.txt");
        if (!file.exists()) {
            System.out.println("File khong ton tai!");
            return;
        }
        try (Scanner f = new Scanner(file)) {
            n = 0;
            ds = new NhanVien[0];
            while (f.hasNextLine()) {
                String[] p = f.nextLine().split(",");
                if (p.length == 6) {
                    NhanVien nv = new NhanVien(p[0], p[1], p[2], p[3], p[4], p[5]);
                    ds = Arrays.copyOf(ds, n + 1);
                    ds[n] = nv;
                    n++;
                }
            }
            System.out.println("Da doc file Nhanvien.txt");
        } catch (Exception e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }
    }
    public void indanhsach() { xuat(); }
    public void ghifile() { ghiFile(); }
    public void docfile() { docFile(); }
    public void tim() { timKiemTheoMaNhanVien(); }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DanhSachNhanVien dsNV = new DanhSachNhanVien();
        int chon;
        do {
            System.out.println("\n=== MENU QUAN LY NHAN SU ===");
            System.out.println("1. Nhap danh sach nhan vien");
            System.out.println("2. Xuat danh sach nhan vien");
            System.out.println("3. Them nhan vien");
            System.out.println("4. Sua thong tin nhan vien");
            System.out.println("5. Tim nhan vien theo ma");
            System.out.println("6. Xoa nhan vien");
            System.out.println("7. Ghi file");
            System.out.println("8. Doc file");
            System.out.println("0. Thoat");
            System.out.print("Chon chuc nang: ");
            chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1: dsNV.nhap(); break;
                case 2: dsNV.indanhsach(); break;
                case 3: dsNV.them(); break;
                case 4: dsNV.sua(); break;
                case 5: dsNV.tim(); break;
                case 6: dsNV.xoa(); break;
                case 7: dsNV.ghifile(); break;
                case 8: dsNV.docfile(); break;
                case 0: System.out.println("Thoat chuong trinh!"); break;
                default: System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
        sc.close();
    }
    public int timkiemma(String ma) {
        if (ma == null || ma.trim().isEmpty()) return -1;
        String cleanMa = ma.trim();
        for (int i = 0; i < n; i++) {
            if (ds[i].getMnv().trim().equalsIgnoreCase(cleanMa)) {
                return i;
            }
        }
        return -1;
    }
}
