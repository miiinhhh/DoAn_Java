package com.example;
import java.util.Scanner;
public class QuanLyThuVien{
    private DanhSachSach dss=new DanhSachSach();
    private DanhSachTacGia dstg=new DanhSachTacGia();
    private DanhSachTheLoai dstl=new DanhSachTheLoai();
    private DanhSachPhieuMuon dsphieumuon=new DanhSachPhieuMuon();
    private DanhSachChiTietPhieuMuon dschitietphieumuon=new DanhSachChiTietPhieuMuon();
    private DanhSachDocGia dsDG =new DanhSachDocGia();
    private DanhSachNhanVien dsNV =new DanhSachNhanVien();
    private DanhSachPhieuPhat dsphieuphat=new DanhSachPhieuPhat();
    private DanhSachQuyDinhPhat dsquydinhphat=new DanhSachQuyDinhPhat();
    private DanhSachNhaXuatBan dsnxb=new DanhSachNhaXuatBan();
    private static QuanLyNhaCungCap qlNCC = new QuanLyNhaCungCap();
    private static QuanLyPhieuNhap qlPN = new QuanLyPhieuNhap(qlNCC);

    private static String FILE_NCC = "src//main/java/com/example/nhacungcap.txt";
    private static String FILE_PN = "src//main/java/com/example/phieunhap.txt";
    private static String FILE_CT = "src//main/java/com/example/chitietphieunhap.txt";

    private static Scanner sc=new Scanner(System.in);

    public QuanLyThuVien() {}
    public QuanLyThuVien(DanhSachSach dss, DanhSachTacGia dstg, DanhSachTheLoai dstl, DanhSachPhieuMuon dsphieumuon, DanhSachChiTietPhieuMuon dschitietphieumuon, DanhSachDocGia dsdg, DanhSachNhanVien dsnv, DanhSachPhieuPhat dsphieuphat, DanhSachQuyDinhPhat dsquydinhphat, DanhSachNhaXuatBan dsnxb, QuanLyNhaCungCap qlNCC, QuanLyPhieuNhap qlPN) {
        this.dss = dss;
        this.dstg = dstg;
        this.dstl = dstl;
        this.dsphieumuon = dsphieumuon;
        this.dschitietphieumuon = dschitietphieumuon;
        this.dsDG = dsdg;
        this.dsNV = dsnv;
        this.dsphieuphat = dsphieuphat;
        this.dsquydinhphat = dsquydinhphat;
        this.dsnxb = dsnxb;
        this.qlNCC = qlNCC;
        this.qlPN = qlPN;
    }
    public QuanLyThuVien(QuanLyThuVien qltv) {
        this.dss = qltv.dss;
        this.dstg = qltv.dstg;
        this.dstl = qltv.dstl;
        this.dsphieumuon = qltv.dsphieumuon;
        this.dschitietphieumuon = qltv.dschitietphieumuon;
        this.dsDG = qltv.dsDG;
        this.dsNV = qltv.dsNV;
        this.dsphieuphat = qltv.dsphieuphat;
        this.dsquydinhphat = qltv.dsquydinhphat;
        this.dsnxb = qltv.dsnxb;
        this.qlNCC = qltv.qlNCC;
        this.qlPN = qltv.qlPN;
    }
    public DanhSachSach getDss() {return dss;}
    public DanhSachTacGia getDstg() {return dstg;}
    public DanhSachTheLoai getDstl() {return dstl;}
    public DanhSachPhieuMuon getDsphieumuon() {return dsphieumuon;}
    public DanhSachChiTietPhieuMuon getDschitietphieumuon() {return dschitietphieumuon;}
    public DanhSachDocGia getDsdg() {return dsDG;}
    public DanhSachNhanVien getDsnv() {return dsNV;}
    public DanhSachPhieuPhat getDsphieuphat() {return dsphieuphat;}
    public DanhSachQuyDinhPhat getDsquydinhphat() {return dsquydinhphat;}
    public DanhSachNhaXuatBan getDsnxb() {return dsnxb;}
    public QuanLyNhaCungCap getQlNCC() { return qlNCC; }
    public QuanLyPhieuNhap getQlPN() { return qlPN; }

    public void setDss(DanhSachSach dss) {this.dss = dss;}
    public void setDstg(DanhSachTacGia dstg) {this.dstg = dstg;}
    public void setDstl(DanhSachTheLoai dstl) {this.dstl = dstl;}
    public void setDsphieumuon(DanhSachPhieuMuon dsphieumuon) {this.dsphieumuon = dsphieumuon;}
    public void setDschitietphieumuon(DanhSachChiTietPhieuMuon dschitietphieumuon) {this.dschitietphieumuon = dschitietphieumuon;}
    public void setDsdg(DanhSachDocGia dsdg) {this.dsDG = dsdg;}
    public void setDsnv(DanhSachNhanVien dsnv) {this.dsNV = dsnv;}
    public void setDsphieuphat(DanhSachPhieuPhat dsphieuphat) {this.dsphieuphat = dsphieuphat;}
    public void setDsquydinhphat(DanhSachQuyDinhPhat dsquydinhphat) {this.dsquydinhphat = dsquydinhphat;}
    public void setDsnxb(DanhSachNhaXuatBan dsnxb) {this.dsnxb = dsnxb;}
    public void setQlNCC(QuanLyNhaCungCap qlNCC) { this.qlNCC = qlNCC; }
    public void setQlPN(QuanLyPhieuNhap qlPN) { this.qlPN = qlPN; }

    public void docTatCaFile(){
        dsphieumuon.docFile();
        dschitietphieumuon.docFile();
        dsDG.docfile();
        dsNV.docfile();
        dsphieuphat.docFile(dsquydinhphat);
        dsquydinhphat.docFile();
        dstg.DocFileTacGia("src/main/java/com/example/TacGia.txt");
        dss.DocFileSach("src/main/java/com/example/Sach.txt");
        dstl.DocFileTheLoai("src/main/java/com/example/TheLoai.txt");
        dsnxb.DocFileNhaXuatBan("src/main/java/com/example/NhaXuatBan.txt");
        int soLuongDoc =IOFile.docNhaCungCap(qlNCC.getArray(), FILE_NCC);
        qlNCC.setSoLuong(soLuongDoc);
        int soPhieu = IOFile.docPhieuNhap(qlPN.getArray(), 500, qlNCC, FILE_PN, FILE_CT);
        qlPN.setSoLuong(soPhieu);

    }
    public void menu(){
        int choice;
        String input;
        do{ 
            System.out.println("\n+=================================================+");
            System.out.println("|           HE THONG QUAN LY THU VIEN             |");
            System.out.println("+=================================================+");
            System.out.println("| [1] Quan ly SACH                                |");
            System.out.println("| [2] Quan ly TAC GIA                             |");
            System.out.println("| [3] Quan ly THE LOAI                            |");
            System.out.println("| [4] Quan ly NHA XUAT BAN                        |");
            System.out.println("+-------------------------------------------------+");
            System.out.println("| [5] Quan ly DOC GIA                             |");
            System.out.println("| [6] Quan ly NHAN VIEN                           |");
            System.out.println("+-------------------------------------------------+");
            System.out.println("| [7] Quan ly PHIEU MUON                          |");
            System.out.println("| [8] Quan ly CHI TIET PHIEU MUON                 |");
            System.out.println("| [9] Quan ly PHIEU PHAT                          |");
            System.out.println("| [10] Quan ly QUY DINH PHAT                      |");
            System.out.println("+-------------------------------------------------+");
            System.out.println("| [11] Quan ly PHIEU NHAP SACH                    |");
            System.out.println("| [12] Quan ly NHA CUNG CAP                       |");
            System.out.println("+-------------------------------------------------+");
            System.out.println("| [13] THOAT CHUONG TRINH                         |");
            System.out.println("+=================================================+");
            System.out.print(">>> Nhap lua chon cua ban (1-14): ");
            try {
                input = sc.nextLine().trim();
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                choice = -1; 
            }
            switch(choice){
                case 1:
                   menuSach(dss);
                   break;
                case 2:
                   menuTacGia(dstg);
                   break;
                case 3:
                   menuTheLoai(dstl);
                   break;
                case 4:
                   menuNhaXuatBan(dsnxb);
                   break;
                case 5:
                   menuDocGia(dsDG, sc);
                   break;
                case 6:
                   menuNhanVien(dsNV, sc);
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
                    menuPN();
                    break;
                case 12:
                    menuNCC();
                    break;
                case 13:
                    System.out.println("Cam on ban da su dung chuong trinh. Hen gap lai ");
                    break;
                default:
                    System.out.println("!!!Lua chon KHONG HOP LE. Vui long nhap lai (1-13)!");
            }
            }while(choice != 13);
        }
        public void menuPhieuMuon(){
    int choice = -1;
    String input; 
    do {
        System.out.println("\n+-------------------------------------------------+");
        System.out.println("|           QUAN LY PHIEU MUON                    |");
        System.out.println("+-------------------------------------------------+");
        System.out.println("| [1] Them phieu muon moi                         |");
        System.out.println("| [2] Hien thi tat ca phieu muon                  |");
        System.out.println("| [3] Sua thong tin phieu muon                    |");
        System.out.println("| [4] Xoa phieu muon                              |");
        System.out.println("| [5] Tim kiem phieu muon (Ma PM/Ma DG)           |");
        System.out.println("+-------------------------------------------------+");
        System.out.println("| [6] Ghi du lieu ra file Phieumuon.txt           |");
        System.out.println("| [7] Doc du lieu tu file Phieumuon.txt           |");
        System.out.println("+-------------------------------------------------+");
        System.out.println("| [0] Quay lai menu chinh                         |");
        System.out.println("+-------------------------------------------------+");
        System.out.print(">>> Nhap lua chon cua ban (0-7): ");
        try {
            input = sc.nextLine().trim();
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            choice = -1; 
        }
        switch (choice) {
            case 1:
                dsphieumuon.them();
                break;
            case 2:
                dsphieumuon.hienThiTatCa();
                break;
            case 3:
                dsphieumuon.sua();
                break;
            case 4:
                dsphieumuon.xoa();
                break;
            case 5:
                System.out.print("Nhap Ma phieu muon hoac Ma doc gia de tim kiem: ");
                String keyword = sc.nextLine().trim();
                dsphieumuon.timKiem(keyword);
                break;
            case 6:
                dsphieumuon.ghiFile();
                break;
            case 7:
                dsphieumuon.docFile();
                break;
            case 0:
                System.out.println("Quay lai menu chinh...");
                break;
            default:
                System.out.println("!!! Lua chon KHONG HOP LE. Vui long chon lai tu 0 den 7 !!!");
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
        System.out.println("\n+-------------------------------------------------+");
        System.out.println("|       QUAN LY CHI TIET PHIEU MUON               |");
        System.out.println("+-------------------------------------------------+");
        System.out.println("| [1] Them chi tiet phieu muon                    |");
        System.out.println("| [2] Hien thi tat ca chi tiet phieu muon         |");
        System.out.println("| [3] Sua thong tin chi tiet phieu muon           |");
        System.out.println("| [4] Xoa chi tiet phieu muon                     |");
        System.out.println("| [5] Tim kiem chi tiet (theo Ma PM/Ma Sach)      |");
        System.out.println("+-------------------------------------------------+");
        System.out.println("| [6] Ghi du lieu ra file                         |");
        System.out.println("| [7] Doc du lieu tu file                         |");
        System.out.println("+-------------------------------------------------+");
        System.out.println("| [0] Quay lai menu chinh                         |");
        System.out.println("+-------------------------------------------------+");
        System.out.print(">>> Vui long nhap lua chon (0-7): ");
        try {
            input = sc.nextLine().trim();
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            choice = -1; 
        }
        switch (choice) {
            case 1:
                dsctpm.them(dsphieumuon,dss);
                break;
            case 2:
                dsctpm.hienThiTatCa();
                break;
            case 3:
                dsctpm.sua(dss); 
                break;
            case 4:
                dsctpm.xoa(); 
                break;
            case 5:
                System.out.print("Nhap Ma phieu muon hoac Ma sach de tim kiem: ");
                String keyword = sc.nextLine().trim();
                dsctpm.timKiem(keyword);
                break;
            case 6:
                dsctpm.ghiFile();
                break;
            case 7:
                dsctpm.docFile();
                break;
            case 0:
                System.out.println("Quay lai menu chinh...");
                break;
            default:
                System.out.println("!!! Lua chon KHONG HOP LE. Vui long chon lai tu 0 den 7 !!!");
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
        System.out.println("\n+-------------------------------------------------+");
        System.out.println("|           QUAN LY PHIEU PHAT                    |");
        System.out.println("+-------------------------------------------------+");
        System.out.println("| [1] Them phieu phat                             |");
        System.out.println("| [2] Hien thi tat ca phieu phat                  |");
        System.out.println("| [3] Sua thong tin phieu phat                    |");
        System.out.println("| [4] Xoa phieu phat                              |");
        System.out.println("| [5] Tim kiem phieu phat (theo Ma PP/Ma DG)      |");
        System.out.println("+-------------------------------------------------+");
        System.out.println("| [6] Ghi du lieu ra file                         |");
        System.out.println("| [7] Doc du lieu tu file                         |");
        System.out.println("+-------------------------------------------------+");
        System.out.println("| [0] Quay lai menu chinh                         |");
        System.out.println("+-------------------------------------------------+");
        System.out.print(">>> Vui long nhap lua chon (0-7): ");

        try {
            input = sc.nextLine().trim();
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            choice = -1; 
        }
        switch (choice) {
            case 1:
                dspp.them(dsqdp);
                break;
            case 2:
                dspp.hienThiTatCa();
                break;
            case 3:
                dspp.sua(dsqdp);
                break;
            case 4:
                dspp.xoa(); 
                break;
            case 5:
                System.out.print("Nhap Ma phieu phat hoac Ma doc gia de tim kiem: ");
                String keyword = sc.nextLine().trim();
                dspp.timKiem(keyword);
                break;
            case 6:
                dspp.ghiFile();
                break;
            case 7:
                dspp.docFile(dsqdp);
                break;
            case 0:
                System.out.println("Quay lai menu chinh...");
                break;
            default:
                System.out.println("!!! Lua chon KHONG HOP LE. Vui long chon lai tu 0 den 7 !!!");
                break;
            }
        } while (choice != 0);
    }
    public void menuQuyDinhPhat(){
        int choice = -1;
        String input; 
        DanhSachQuyDinhPhat dsqdp = this.dsquydinhphat;
        do {
            System.out.println("\n+-------------------------------------------------+");
            System.out.println("|             QUAN LY QUY DINH PHAT               |");
            System.out.println("+-------------------------------------------------+");
            System.out.println("| [1] Them quy dinh                               |");
            System.out.println("| [2] Hien thi tat ca quy dinh                    |");
            System.out.println("| [3] Sua thong tin quy dinh                      |");
            System.out.println("| [4] Xoa quy dinh                                |");
            System.out.println("| [5] Tim kiem quy dinh (theo Ma Phat/Noi dung)   |");
            System.out.println("+-------------------------------------------------+");
            System.out.println("| [6] Ghi du lieu ra file                         |");
            System.out.println("| [7] Doc du lieu tu file                         |");
            System.out.println("+-------------------------------------------------+");
            System.out.println("| [0] Quay lai menu chinh                         |");
            System.out.println("+-------------------------------------------------+");
            System.out.print(">>> Vui long nhap lua chon (0-7): ");
        try {
            input = sc.nextLine().trim();
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            choice = -1; 
        }
        switch (choice) {
            case 1:
                dsqdp.them();
                break;
            case 2:
                dsqdp.hienThiTatCa();
                break;
            case 3:
                dsqdp.sua();
                break;
            case 4:
                dsqdp.xoa(); 
                break;
            case 5:
                System.out.print("Nhap Ma phat hoac Noi dung de tim kiem: ");
                String keyword = sc.nextLine().trim();
                dsqdp.timKiem(keyword);
                break;
            case 6:
                dsqdp.ghiFile();
                break;
            case 7:
                dsqdp.docFile();
                break;
            case 0:
                System.out.println("Quay lai menu chinh...");
                break;
            default:
                System.out.println("!!! Lua chon KHONG HOP LE. Vui long chon lai tu 0 den 7 !!!");
                break;
            }
        } while (choice != 0);
    }
    public static void menuSach(DanhSachSach dss) {
        Scanner sc = new Scanner(System.in);
        int c;
        do {
            System.out.println("\n+-------------------------------------------------+");
            System.out.println("|                 QUAN LY SACH                    |");
            System.out.println("+-------------------------------------------------+");
            System.out.println("| [1] Xem danh sach sach                          |");
            System.out.println("| [2] Them sach moi                               |");
            System.out.println("| [3] Sua thong tin sach                          |");
            System.out.println("| [4] Xoa sach                                    |");
            System.out.println("| [5] Tim sach                                    |");
            System.out.println("+-------------------------------------------------+");
            System.out.println("| [6] Doc du lieu tu file                         |");
            System.out.println("| [7] Ghi du lieu ra file                         |");
            System.out.println("+-------------------------------------------------+");
            System.out.println("| [0] Quay lai menu chinh                         |");
            System.out.println("+-------------------------------------------------+");
            System.out.print(">>> Nhap lua chon (0-7): ");
            c = sc.nextInt();
            sc.nextLine();

            switch(c) {
                case 1: dss.XemSach(); break;
                case 2: dss.ThemSach(); break;
                case 3: dss.SuaSach(); break;
                case 4: dss.XoaSach(); break;
                case 5: dss.TimSach(); break;
                case 6: dss.DocFileSach("src/main/java/com/example/Sach.txt"); break;
                case 7: dss.GhiFileSach("src/main/java/com/example/Sach.txt"); break;
                case 0: System.out.println("Quay lai menu chinh..."); break;
                default: System.out.println("Lua chon khong hop le!");
            }
        } while(c != 0);
    }
    public static void menuTheLoai(DanhSachTheLoai dstl) {
        Scanner sc = new Scanner(System.in);
        int c;
        do {
            System.out.println("\n+-------------------------------------------------+");
            System.out.println("|               QUAN LY THE LOAI                  |");
            System.out.println("+-------------------------------------------------+");
            System.out.println("| [1] Xem danh sach the loai                      |");
            System.out.println("| [2] Them the loai moi                           |");
            System.out.println("| [3] Sua thong tin the loai                      |");
            System.out.println("| [4] Xoa the loai                                |");
            System.out.println("| [5] Tim the loai                                |");
            System.out.println("+-------------------------------------------------+");
            System.out.println("| [6] Doc du lieu tu file                         |");
            System.out.println("| [7] Ghi du lieu ra file                         |");
            System.out.println("+-------------------------------------------------+");
            System.out.println("| [0] Quay lai menu chinh                         |");
            System.out.println("+-------------------------------------------------+");
            System.out.print(">>> Nhap lua chon (0-7): ");
            c = sc.nextInt();
            sc.nextLine();

            switch(c) {
                case 1: dstl.XemTheLoai(); break;
                case 2: dstl.ThemTheLoai(); break;
                case 3: dstl.SuaTheLoai(); break;
                case 4: dstl.XoaTheLoai(); break;
                case 5: dstl.TimTheLoai(); break;
                case 6: dstl.DocFileTheLoai("src/main/java/com/example/TheLoai.txt");
                case 7: dstl.GhiFileTheLoai("src/main/java/com/example/TheLoai.txt"); break;
                case 0: break;
                default: System.out.println("Lua chon khong hop le!");
            }
        } while(c != 0);
    }
    public static void menuNhaXuatBan(DanhSachNhaXuatBan dsnxb) {
        Scanner sc = new Scanner(System.in);
        int c;
        do {
            System.out.println("\n+-------------------------------------------------+");
            System.out.println("|             QUAN LY NHA XUAT BAN                |");
            System.out.println("+-------------------------------------------------+");
            System.out.println("| [1] Xem danh sach nha xuat ban                  |");
            System.out.println("| [2] Them nha xuat ban moi                       |");
            System.out.println("| [3] Sua thong tin nha xuat ban                  |");
            System.out.println("| [4] Xoa nha xuat ban                            |");
            System.out.println("| [5] Tim nha xuat ban                            |");
            System.out.println("+-------------------------------------------------+");
            System.out.println("| [6] Doc du lieu tu file                         |");
            System.out.println("| [7] Ghi du lieu ra file                         |");
            System.out.println("+-------------------------------------------------+");
            System.out.println("| [0] Quay lai menu chinh                         |");
            System.out.println("+-------------------------------------------------+");
            System.out.print(">>> Nhap lua chon (0-7): ");
            c = sc.nextInt();
            sc.nextLine();

            switch(c) {
                case 1: dsnxb.XemNXB(); break;
                case 2: dsnxb.ThemNhaXuatBan(); break;
                case 3: dsnxb.SuaNhaXuatBan(); break;
                case 4: dsnxb.XoaNhaXuatBan(); break;
                case 5: dsnxb.TimNhaXuatBan(); break;
                case 6: dsnxb.DocFileNhaXuatBan("src/main/java/com/example/NhaXuatBan.txt"); break;
                case 7: dsnxb.GhiFileNhaXuatBan("src/main/java/com/example/NhaXuatBan.txt"); break;
                case 0: break;
                default: System.out.println("Lua chon khong hop le!");
            }
        } while(c != 0);
    }
    public static void menuTacGia(DanhSachTacGia dstg) {
        Scanner sc = new Scanner(System.in);
        int c;
        do {
            System.out.println("\n+-------------------------------------------------+");
            System.out.println("|             QUAN LY TAC GIA                     |");
            System.out.println("+-------------------------------------------------+");
            System.out.println("| [1] Xem danh sach tac gia                       |");
            System.out.println("| [2] Them tac gia moi                            |");
            System.out.println("| [3] Sua thong tin tac gia                       |");
            System.out.println("| [4] Xoa tac gia                                 |");
            System.out.println("| [5] Tim tac gia                                 |");
            System.out.println("+-------------------------------------------------+");
            System.out.println("| [6] Doc du lieu tu file                         |");
            System.out.println("| [7] Ghi du lieu ra file                         |");
            System.out.println("+-------------------------------------------------+");
            System.out.println("| [0] Quay lai menu chinh                         |");
            System.out.println("+-------------------------------------------------+");
            System.out.print(">>> Nhap lua chon (0-7): ");
            c = sc.nextInt();
            sc.nextLine();

            switch(c) {
                case 1: dstg.XemTacGia(); break;
                case 2: dstg.ThemTacGia(); break;
                case 3: dstg.SuaTacGia(); break;
                case 4: dstg.XoaTacGia(); break;
                case 5: dstg.TimTacGia(); break;
                case 6: dstg.DocFileTacGia("src/main/java/com/example/TacGia.txt"); break;
                case 7: dstg.GhiFileTacGia("src/main/java/com/example/TacGia.txt"); break;
                case 0: break;
                default: System.out.println("Lua chon khong hop le!");
            }
        } while(c != 0);
    }
    private static void menuNCC() {
        int chon;
        do {
            System.out.println("\n+-------------------------------------------------+");
            System.out.println("|           QUAN LY NHA CUNG CAP                  |");
            System.out.println("+-------------------------------------------------+");
            System.out.println("| [1] Them nha cung cap                           |");
            System.out.println("| [2] Xem danh sach                               |");
            System.out.println("| [3] Tim theo ma                                 |");
            System.out.println("| [4] Sua nha cung cap                            |");
            System.out.println("| [5] Xoa nha cung cap                            |");
            System.out.println("+-------------------------------------------------+");
            System.out.println("| [6] Ghi du lieu ra file                         |");
            System.out.println("| [7] Doc du lieu tu file                         |");
            System.out.println("+-------------------------------------------------+");
            System.out.println("| [0] Quay lai menu chinh                         |");
            System.out.println("+-------------------------------------------------+");
            System.out.print(">>> Chon chuc nang (0-7): ");
            chon = Integer.parseInt(sc.nextLine());

            switch (chon) {
                case 1:
                    System.out.print("Nhap ma NCC: ");
                    String maNCC = sc.nextLine();
                    System.out.print("Nhap ten NCC: ");
                    String tenNCC = sc.nextLine();
                    System.out.print("Nhap dia chi: ");
                    String diaChi = sc.nextLine();
                    System.out.print("Nhap so dien thoai: ");
                    String sdt = sc.nextLine();

                    NhaCungCap ncc = new NhaCungCap(maNCC, tenNCC, diaChi, sdt);
                    if (qlNCC.them(ncc))
                        System.out.println("Da them nha cung cap!");
                    else
                        System.out.println("Danh sach day!");
                    break;
                case 2:
                    qlNCC.hienThi();
                    break;
                case 3:
                    System.out.print("Nhap ma NCC: ");
                    String ma = sc.nextLine();
                    int idx = qlNCC.timTheoMa(ma);
                    if (idx != -1)
                        System.out.println(qlNCC.getArray()[idx]);
                    else
                        System.out.println("Khong tim thay!");
                    break;
                case 4:
                    System.out.print("Nhap ma NCC can sua: ");
                    String maSua = sc.nextLine();
                    System.out.print("Nhap ten moi: ");
                    String tenMoi = sc.nextLine();
                    System.out.print("Nhap dia chi moi: ");
                    String diaChiMoi = sc.nextLine();
                    System.out.print("Nhap so dien thoai moi: ");
                    String sdtMoi = sc.nextLine();

                    if (qlNCC.sua(maSua, tenMoi, diaChiMoi, sdtMoi))
                        System.out.println("Da cap nhat nha cung cap!");
                    else
                        System.out.println("Khong tim thay ma NCC!");
                    break;
                case 5:
                    System.out.print("Nhap ma NCC can xoa: ");
                    if (qlNCC.xoa(sc.nextLine()))
                        System.out.println("Da xoa!");
                    else
                        System.out.println("Khong tim thay!");
                    break;
                case 6:
                    IOFile.ghiNhaCungCap(qlNCC.getArray(), qlNCC.getSoLuong(), FILE_NCC);
                    break;
                case 7:
                    int soLuongDoc =IOFile.docNhaCungCap(qlNCC.getArray(), FILE_NCC);
                    qlNCC.setSoLuong(soLuongDoc);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
    }
    // ===================== MENU PHIEU NHAP =====================
    private static void menuPN() {
        int chon;
        do {
            System.out.println("\n+-------------------------------------------------+");
             System.out.println("|           QUAN LY PHIEU NHAP                    |");
             System.out.println("+-------------------------------------------------+");
             System.out.println("| [1] Them phieu nhap                             |");
             System.out.println("| [2] Xem danh sach                               |");
             System.out.println("| [3] Tim theo ma phieu                           |");
             System.out.println("| [4] Sua phieu nhap                              |");
             System.out.println("| [5] Xoa phieu nhap                              |");
             System.out.println("+-------------------------------------------------+");
             System.out.println("| [6] Ghi du lieu ra file                         |");
             System.out.println("| [7] Doc du lieu tu file                         |");
             System.out.println("+-------------------------------------------------+");
             System.out.println("| [0] Quay lai menu chinh                         |");
             System.out.println("+-------------------------------------------------+");
             System.out.print(">>> Chon chuc nang (0-7): ");
            chon = Integer.parseInt(sc.nextLine());

            switch (chon) {
                case 1:
                    themPhieuNhap();
                    break;
                case 2:
                    qlPN.hienThi();
                    break;
                case 3:
                    System.out.print("Nhap ma phieu: ");
                    PhieuNhap pn = qlPN.layTheoMa(sc.nextLine());
                    if (pn != null) {
                        System.out.println(pn);
                        pn.hienThiChiTiet();
                    } else {
                        System.out.println("Khong tim thay!");
                    }
                    break;
                case 4:
                    System.out.print("Nhap ma phieu can sua: ");
                    suaPhieuNhap(sc.nextLine());
                    break;
                case 5:
                    System.out.print("Nhap ma phieu can xoa: ");
                    if (qlPN.xoa(sc.nextLine()))
                        System.out.println("Da xoa!");
                    else
                        System.out.println("Khong tim thay!");
                    break;
                case 6:
                    IOFile.ghiPhieuNhap(qlPN.getArray(), qlPN.getSoLuong(), FILE_PN, FILE_CT);
                    System.out.println("Da ghi file phieu nhap!");
                    break;
                case 7:
                    int soPhieu = IOFile.docPhieuNhap(qlPN.getArray(), 500, qlNCC, FILE_PN, FILE_CT);
                    System.out.println("Da doc file phieu nhap!");
                    qlPN.setSoLuong(soPhieu);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
    }

    // ===================== THEM PHIEU NHAP =====================
    private static void themPhieuNhap() {
        System.out.print("Nhap ma phieu: ");
        String ma = sc.nextLine();
        System.out.print("Nhap ngay (yyyy-MM-dd): ");
        String ngay = sc.nextLine();
        System.out.print("Nhap ma nha cung cap: ");
        String maNCC = sc.nextLine();
        NhaCungCap ncc = qlNCC.layTheoMa(maNCC);
        if (ncc == null) {
            System.out.println("Ma NCC khong ton tai!");
            return;
        }
        PhieuNhap pn = new PhieuNhap(ma, ngay, ncc);

        System.out.print("Ban co muon nhap chi tiet khong? (y/n): ");
        String ch = sc.nextLine();
        if (ch.equalsIgnoreCase("y")) {
            while (true) {
                System.out.print("Nhap ma hang: ");
                String maHang = sc.nextLine();
                System.out.print("Nhap ten hang: ");
                String ten = sc.nextLine();
                System.out.print("Nhap so luong: ");
                int sl = Integer.parseInt(sc.nextLine());
                System.out.print("Nhap don gia: ");
                double dg = Double.parseDouble(sc.nextLine());
                pn.themChiTiet(new ChiTietPhieuNhap(ma, maHang, sl, dg));

                System.out.print("Nhap tiep? (y/n): ");
                if (!sc.nextLine().equalsIgnoreCase("y")) break;
            }
        }

        if (qlPN.them(pn))
            System.out.println("Them thanh cong!");
        else
            System.out.println("Danh sach day!");
    }

    // ===================== SUA PHIEU NHAP =====================
    private static void suaPhieuNhap(String ma) {
        PhieuNhap pn = qlPN.layTheoMa(ma);
        if (pn == null) {
            System.out.println("Khong tim thay!");
            return;
        }
        System.out.print("Nhap ngay moi (bo trong neu giu nguyen): ");
        String ngay = sc.nextLine();
        if (!ngay.isEmpty()) pn.setNgayNhap(ngay);

        System.out.print("Nhap ma NCC moi (bo trong neu giu nguyen): ");
        String maNCC = sc.nextLine();
        if (!maNCC.isEmpty()) {
            NhaCungCap ncc = qlNCC.layTheoMa(maNCC);
            if (ncc != null) pn.setNhaCungCap(ncc);
            else System.out.println("Ma NCC khong ton tai!");
        }

        System.out.println("Ban co muon sua chi tiet khong? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            pn.hienThiChiTiet();
            System.out.println("Nhap lai toan bo chi tiet:");
            pn = new PhieuNhap(pn.getMaPhieu(), pn.getNgayNhap(), pn.getNhaCungCap());
            while (true) {
                System.out.print("Nhap ma hang: ");
                String maHang = sc.nextLine();
                System.out.print("Nhap ten hang: ");
                String ten = sc.nextLine();
                System.out.print("Nhap so luong: ");
                int sl = Integer.parseInt(sc.nextLine());
                System.out.print("Nhap don gia: ");
                double dg = Double.parseDouble(sc.nextLine());
                pn.themChiTiet(new ChiTietPhieuNhap(ma, maHang, sl, dg));

                System.out.print("Nhap tiep? (y/n): ");
                if (!sc.nextLine().equalsIgnoreCase("y")) break;
            }
        }
        System.out.println("Da cap nhat phieu nhap!");
    }
    // ===================== MENU DOC GIA =====================
    public static void menuDocGia(DanhSachDocGia dsDG, Scanner sc) {
        int chon;
        do {
            System.out.println("\n+-------------------------------------------------+");
            System.out.println("|           QUAN LY DOC GIA                       |");
            System.out.println("+-------------------------------------------------+");
            System.out.println("| [1] Nhap danh sach doc gia                      |");
            System.out.println("| [2] Xuat danh sach doc gia                      |");
            System.out.println("| [3] Them doc gia                                |");
            System.out.println("| [4] Sua thong tin doc gia                       |");
            System.out.println("| [5] Tim doc gia theo ma                         |");
            System.out.println("| [6] Xoa doc gia theo ma                         |");
            System.out.println("+-------------------------------------------------+");
            System.out.println("| [7] Ghi danh sach vao file                      |");
            System.out.println("| [8] Doc danh sach tu file                       |");
            System.out.println("+-------------------------------------------------+");
            System.out.println("| [0] Quay lai menu chinh                         |");
            System.out.println("+-------------------------------------------------+");
            System.out.print(">>> Chon chuc nang (0-8): ");
            chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1: dsDG.nhap(); break;
                case 2: dsDG.indanhsach(); break;
                case 3: dsDG.them(); break;
                case 4: dsDG.sua(); break;
                case 5: dsDG.tim(); break;
                case 6: dsDG.xoa(); break;
                case 7: dsDG.ghifile(); break;
                case 8: dsDG.docfile(); break;
                case 0: System.out.println("Quay lai menu chinh..."); break;
                default: System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
    }
    // ===================== MENU NHAN VIEN =====================
    public static void menuNhanVien(DanhSachNhanVien dsNV, Scanner sc) {
        int chon;
        do {
            System.out.println("\n+-------------------------------------------------+");
            System.out.println("|           QUAN LY NHAN VIEN                     |");
            System.out.println("+-------------------------------------------------+");
            System.out.println("| [1] Nhap danh sach nhan vien                    |");
            System.out.println("| [2] Xuat danh sach nhan vien                    |");
            System.out.println("| [3] Them nhan vien                              |");
            System.out.println("| [4] Sua thong tin nhan vien                     |");
            System.out.println("| [5] Tim nhan vien theo ma                       |");
            System.out.println("| [6] Xoa nhan vien theo ma                       |");
            System.out.println("+-------------------------------------------------+");
            System.out.println("| [7] Ghi danh sach vao file                      |");
            System.out.println("| [8] Doc danh sach tu file                       |");
            System.out.println("+-------------------------------------------------+");
            System.out.println("| [0] Quay lai menu chinh                         |");
            System.out.println("+-------------------------------------------------+");
            System.out.print(">>> Chon chuc nang (0-8): ");
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
                case 0: System.out.println("Quay lai menu chinh..."); break;
                default: System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
    }
    public static void main(String[] args) {
    QuanLyThuVien ql = new QuanLyThuVien();
    ql.docTatCaFile();
    ql.menu();
    }
}
