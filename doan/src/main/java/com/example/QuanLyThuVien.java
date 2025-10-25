package com.example;
import java.util.Arrays;
import java.util.Scanner;
public class QuanLyThuVien{
    private DanhSachSach dss=new DanhSachSach();
    private DanhSachTacGia dstg=new DanhSachTacGia();
    private DanhSachTheLoai dstl=new DanhSachTheLoai();
    private DanhSachPhieuMuon dsphieumuon=new DanhSachPhieuMuon();
    private DanhSachChiTietPhieuMuon dschitietphieumuon=new DanhSachChiTietPhieuMuon();
    private DanhSachDocGia dsdg=new DanhSachDocGia();
    private DanhSachNhanVien dsnv=new DanhSachNhanVien();
    private DanhSachPhieuPhat dsphieuphat=new DanhSachPhieuPhat();
    private DanhSachQuyDinhPhat dsquydinhphat=new DanhSachQuyDinhPhat();
    private DanhSachNhaXuatBan dsnxb=new DanhSachNhaXuatBan();
    Scanner sc=new Scanner(System.in);
    public QuanLyThuVien() {}
    public QuanLyThuVien(DanhSachSach dss, DanhSachTacGia dstg, DanhSachTheLoai dstl, DanhSachPhieuMuon dsphieumuon, DanhSachChiTietPhieuMuon dschitietphieumuon, DanhSachDocGia dsdg, DanhSachNhanVien dsnv, DanhSachPhieuPhat dsphieuphat, DanhSachQuyDinhPhat dsquydinhphat, DanhSachNhaXuatBan dsnxb) {
        this.dss = dss;
        this.dstg = dstg;
        this.dstl = dstl;
        this.dsphieumuon = dsphieumuon;
        this.dschitietphieumuon = dschitietphieumuon;
        this.dsdg = dsdg;
        this.dsnv = dsnv;
        this.dsphieuphat = dsphieuphat;
        this.dsquydinhphat = dsquydinhphat;
        this.dsnxb = dsnxb;
    }
    public QuanLyThuVien(QuanLyThuVien qltv) {
        this.dss = qltv.dss;
        this.dstg = qltv.dstg;
        this.dstl = qltv.dstl;
        this.dsphieumuon = qltv.dsphieumuon;
        this.dschitietphieumuon = qltv.dschitietphieumuon;
        this.dsdg = qltv.dsdg;
        this.dsnv = qltv.dsnv;
        this.dsphieuphat = qltv.dsphieuphat;
        this.dsquydinhphat = qltv.dsquydinhphat;
        this.dsnxb = qltv.dsnxb;
    }
    public DanhSachSach getDss() {return dss;}
    public DanhSachTacGia getDstg() {return dstg;}
    public DanhSachTheLoai getDstl() {return dstl;}
    public DanhSachPhieuMuon getDsphieumuon() {return dsphieumuon;}
    public DanhSachChiTietPhieuMuon getDschitietphieumuon() {return dschitietphieumuon;}
    public DanhSachDocGia getDsdg() {return dsdg;}
    public DanhSachNhanVien getDsnv() {return dsnv;}
    public DanhSachPhieuPhat getDsphieuphat() {return dsphieuphat;}
    public DanhSachQuyDinhPhat getDsquydinhphat() {return dsquydinhphat;}
    public DanhSachNhaXuatBan getDsnxb() {return dsnxb;}

    public void setDss(DanhSachSach dss) {this.dss = dss;}
    public void setDstg(DanhSachTacGia dstg) {this.dstg = dstg;}
    public void setDstl(DanhSachTheLoai dstl) {this.dstl = dstl;}
    public void setDsphieumuon(DanhSachPhieuMuon dsphieumuon) {this.dsphieumuon = dsphieumuon;}
    public void setDschitietphieumuon(DanhSachChiTietPhieuMuon dschitietphieumuon) {this.dschitietphieumuon = dschitietphieumuon;}
    public void setDsdg(DanhSachDocGia dsdg) {this.dsdg = dsdg;}
    public void setDsnv(DanhSachNhanVien dsnv) {this.dsnv = dsnv;}
    public void setDsphieuphat(DanhSachPhieuPhat dsphieuphat) {this.dsphieuphat = dsphieuphat;}
    public void setDsquydinhphat(DanhSachQuyDinhPhat dsquydinhphat) {this.dsquydinhphat = dsquydinhphat;}
    public void setDsnxb(DanhSachNhaXuatBan dsnxb) {this.dsnxb = dsnxb;}
 
    public void docTatCaFile(){
        dsphieumuon.docFile();
        dschitietphieumuon.docFile();
        dsdg.docfile();
        dsnv.docFile();
        dsphieuphat.docFile(dsquydinhphat);
        dsquydinhphat.docFile();
    }
    public void menu(){
        int choice;
        String input;
        do{
            System.out.println("1.Quan ly sach");
            System.out.println("2.Quan ly tac gia");
            System.out.println("3.Quan ly nhan vien");
            System.out.println("4.Quan ly doc gia");
            System.out.println("5.Quan ly the loai");
            System.out.println("6.Quan ly nha xuat ban");
            System.out.println("7.Quan ly phieu muon");
            System.out.println("8.Quan ly chi tiet phieu muon");
            System.out.println("9.Quan ly phieu phat");
            System.out.println("10.Quan ly quy dinh phat");
            System.out.println("11.Quan ly phieu nhap sach");
            System.out.println("12.Quan ly chi tiet phieu nhap sach");
            System.out.println("13.Quan ly nha cung cap");
            System.out.println("14.Thoat");
            System.out.print("Lua chon cua ban: ");
            try {
                input = sc.nextLine().trim();
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                choice = -1; 
            }
            switch(choice){
                case 1:
                   menuSach();
                   break;
                case 2:
                    menuTacGia();
                    break;
                case 3:
                    menuNhanVien();
                    break;
                case 4:
                    menuDocGia();
                    break;
                case 5:
                    menuTheLoai();
                    break;
                case 6:
                    menuNhaXuatBan();
                    break;
                case 7:
                    menuPhieuMuon();
                    break;
                case 8:
                    menuChiTietPhieuMuon();
                    break;
                case 9:
                    menuPhieuPhat();
                    break;
                case 10:
                    menuQuyDinhPhat();
                    break;
                case 11:
                    menuPhieuNhapSach();
                    break;
                case 12:
                    menuChiTietPhieuNhapSach();
                    break;
                case 13:
                    menuNhaCungCap();
                    break;
                case 14:
                    System.out.println("Cam on ban da su dung chuong trinh!");
                    break;
                default:
                    System.out.println("Nhap sai ! Vui long nhap lai !");
            }
            }while(choice != 14);
        }
        public void menuPhieuMuon(){
            int choice = -1;
            String input; 
            do {
                System.out.println("\n--- QUAN LY PHIEU MUON ---");
                System.out.println("1. Nhap thong tin phieu muon moi (nhap nhieu)");
                System.out.println("2. Them phieu muon (nhap 1)");
                System.out.println("3. Hien thi tat ca phieu muon");
                System.out.println("4. Sua thong tin phieu muon");
                System.out.println("5. Xoa phieu muon");
                System.out.println("6. Tim kiem phieu muon (theo Ma phieu muon/Ma doc gia)");
                System.out.println("7. Ghi du lieu ra file Phieumuon.txt");
                System.out.println("8. Doc du lieu tu file Phieumuon.txt");
                System.out.println("0. Quay lai menu chinh");
                System.out.print("Lua chon cua ban: ");
                try {
                    input = sc.nextLine().trim();
                    choice = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    choice = -1; 
                }
                System.out.println("---------------------------");
                switch (choice) {
                    case 1:
                       dsphieumuon.nhap(); 
                       break;
                    case 2:
                       dsphieumuon.them();
                       break;
                    case 3:
                       dsphieumuon.hienThiTatCa();
                       break;
                    case 4:
                       dsphieumuon.sua();
                       break;
                    case 5:
                       dsphieumuon.xoa();
                       break;
                    case 6:
                       System.out.print("Nhap Ma phieu muon hoac Ma doc gia de tim kiem: ");
                       String keyword = sc.nextLine().trim();
                       dsphieumuon.timKiem(keyword);
                       break;
                    case 7:
                       dsphieumuon.ghiFile();
                       System.out.println("Da ghi du lieu vao file Phieumuon.txt.");
                       break;
                    case 8:
                       dsphieumuon.docFile();
                       System.out.println("Da doc du lieu tu file Phieumuon.txt.");
                       break;
                    case 0:
                       System.out.println("Quay lai menu chinh...");
                       break;
                    default:
                       System.out.println("Lua chon khong hop le. Vui long chon lai tu 0 den 8 !!");
                       break;
                }
            } while (choice != 0);
        }
public void menuChiTietPhieuMuon(){
    int choice = -1;
    String input; 
    DanhSachChiTietPhieuMuon dsctpm = this.dschitietphieumuon;
    DanhSachSach dss = this.dss;               
    
    do {
        System.out.println("\n--- QUAN LY CHI TIET PHIEU MUON ---");
        System.out.println("1. Nhap thong tin chi tiet phieu muon (nhap nhieu)");
        System.out.println("2. Them chi tiet phieu muon (nhap 1)");
        System.out.println("3. Hien thi tat ca chi tiet phieu muon");
        System.out.println("4. Sua thong tin chi tiet phieu muon");
        System.out.println("5. Xoa chi tiet phieu muon");
        System.out.println("6. Tim kiem chi tiet phieu muon (theo Ma PM/Ma Sach)");
        System.out.println("7. Ghi du lieu ra file Chitietphieumuon.txt");
        System.out.println("8. Doc du lieu tu file Chitietphieumuon.txt");
        System.out.println("0. Quay lai menu chinh");
        System.out.print("Vui long nhap lua chon: ");

        try {
            input = sc.nextLine().trim();
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            choice = -1; 
        }

        System.out.println("---------------------------");

        switch (choice) {
            case 1:
                dsctpm.nhap(dsphieumuon,dss);
                break;
            case 2:
                dsctpm.them(dsphieumuon,dss);
                break;
            case 3:
                dsctpm.hienThiTatCa();
                break;
            case 4:
                dsctpm.sua(dss); 
                break;
            case 5:
                dsctpm.xoa(); 
                break;
            case 6:
                System.out.print("Nhap Ma phieu muon hoac Ma sach de tim kiem: ");
                String keyword = sc.nextLine().trim();
                dsctpm.timKiem(keyword);
                break;
            case 7:
                dsctpm.ghiFile();
                break;
            case 8:
                dsctpm.docFile();
                break;
            case 0:
                System.out.println("Quay lai menu chinh...");
                break;
            default:
                System.out.println("Lua chon khong hop le. Vui long chon lai!");
                break;
            }
        } while (choice != 0);
    }

public void menuPhieuPhat(){
    int choice = -1;
    String input; 
    DanhSachPhieuPhat dspp = this.dsphieuphat;
    DanhSachQuyDinhPhat dsqdp = this.dsquydinhphat;
    
    do {
        System.out.println("\n--- QUAN LY PHIEU PHAT ---");
        System.out.println("1. Nhap thong tin phieu phat moi (nhap nhieu)");
        System.out.println("2. Them phieu phat (nhap 1)");
        System.out.println("3. Hien thi tat ca phieu phat");
        System.out.println("4. Sua thong tin phieu phat");
        System.out.println("5. Xoa phieu phat");
        System.out.println("6. Tim kiem phieu phat (theo Ma Phieu Phat/Ma Doc Gia)");
        System.out.println("7. Ghi du lieu ra file Phieuphat.txt");
        System.out.println("8. Doc du lieu tu file Phieuphat.txt");
        System.out.println("0. Quay lai menu chinh");
        System.out.print("Vui long nhap lua chon: ");

        try {
            input = sc.nextLine().trim();
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            choice = -1; 
        }

        System.out.println("---------------------------");

        switch (choice) {
            case 1:
                dspp.nhap(dsqdp);
                break;
            case 2:
                dspp.them(dsqdp);
                break;
            case 3:
                dspp.hienThiTatCa();
                break;
            case 4:
                dspp.sua(dsqdp);
                break;
            case 5:
                dspp.xoa(); 
                break;
            case 6:
                System.out.print("Nhap Ma phieu phat hoac Ma doc gia de tim kiem: ");
                String keyword = sc.nextLine().trim();
                dspp.timKiem(keyword);
                break;
            case 7:
                dspp.ghiFile();
                break;
            case 8:
                dspp.docFile(dsqdp);
                break;
            case 0:
                System.out.println("Quay lai menu chinh...");
                break;
            default:
                System.out.println("Lua chon khong hop le. Vui long chon lai!");
                break;
            }
        } while (choice != 0);
    }
    public void menuQuyDinhPhat(){
        int choice = -1;
        String input; 
        DanhSachQuyDinhPhat dsqdp = this.dsquydinhphat;
        do {
            System.out.println("\n--- QUAN LY QUY DINH PHAT ---");
            System.out.println("1. Nhap thong tin quy dinh (nhap nhieu)");
            System.out.println("2. Them quy dinh (nhap 1)");
            System.out.println("3. Hien thi tat ca quy dinh");
            System.out.println("4. Sua thong tin quy dinh");
            System.out.println("5. Xoa quy dinh");
            System.out.println("6. Tim kiem quy dinh (theo Ma phat/Noi dung)");
            System.out.println("7. Ghi du lieu ra file Quydinhphat.txt");
            System.out.println("8. Doc du lieu tu file Quydinhphat.txt");
            System.out.println("0. Quay lai menu chinh");
            System.out.print("Vui long nhap lua chon: ");
        try {
            input = sc.nextLine().trim();
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            choice = -1; 
        }
        System.out.println("---------------------------");
        switch (choice) {
            case 1:
                dsqdp.nhap();
                break;
            case 2:
                dsqdp.them();
                break;
            case 3:
                dsqdp.hienThiTatCa();
                break;
            case 4:
                dsqdp.sua();
                break;
            case 5:
                dsqdp.xoa(); 
                break;
            case 6:
                System.out.print("Nhap Ma phat hoac Noi dung de tim kiem: ");
                String keyword = sc.nextLine().trim();
                dsqdp.timKiem(keyword);
                break;
            case 7:
                dsqdp.ghiFile();
                break;
            case 8:
                dsqdp.docFile();
                break;
            case 0:
                System.out.println("Quay lai menu chinh...");
                break;
            default:
                System.out.println("Lua chon khong hop le. Vui long chon lai!");
                break;
            }
        } while (choice != 0);
    }
    public static void main(String[] args) {
    QuanLyThuVien ql = new QuanLyThuVien();
    ql.docTatCaFile();
    ql.menu();
    }
}
