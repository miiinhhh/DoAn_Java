package com.example;
import java.util.*;
import java.io.*;
public class Main1_2 {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    ArrayList<TacGia> dstgList = new ArrayList<>();
    DanhSachTacGia dstg = new DanhSachTacGia(dstgList);
    DanhSachTheLoai dstl = new DanhSachTheLoai();
    DanhSachNhaXuatBan dsnxb = new DanhSachNhaXuatBan();
    DanhSachSach dss = new DanhSachSach(new ArrayList<>(), dstg,dstl, dsnxb);
    dstg.setDanhSachSach(dss);  // gán ngược lại
    dstl.setDanhSachSach(dss);
    dsnxb.setDanhSachSach(dss);


        dstg.DocFileTacGia("src/main/java/com/example/TacGia.txt");
        dss.DocFileSach("src/main/java/com/example/Sach.txt");
        dstl.DocFileTheLoai("src/main/java/com/example/TheLoai.txt");
        dsnxb.DocFileNhaXuatBan("src/main/java/com/example/NhaXuatBan.txt");

        int choice;
        do {
            System.out.println("===== MENU QUAN LY THU VIEN =====");
            System.out.println("1. Quan ly sach");
            System.out.println("2. Quan ly tac gia");
            System.out.println("3. Quan ly the loai");
            System.out.println("4. Quan ly nha xuat ban");
            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon: ");
            choice = sc.nextInt();
            sc.nextLine(); // bỏ dòng thừa
            switch(choice) {
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
                case 0:
                    System.out.println("Thoat chuong trinh...");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 0);
    }

    private static void menuSach(DanhSachSach dss) {
        Scanner sc = new Scanner(System.in);
        int c;
        do {
            System.out.println("----- QUAN LY SACH -----");
            System.out.println("1. Xem danh sach sach");
            System.out.println("2. Them sach moi");
            System.out.println("3. Sua thong tin sach");
            System.out.println("4. Xoa sach");
            System.out.println("5. Tim sach");
            System.out.println("0. Quay lai");
            System.out.print("Nhap lua chon: ");
            c = sc.nextInt();
            sc.nextLine();

            switch(c) {
                case 1: dss.XemSach(); break;
                case 2: dss.ThemSach(); break;
                case 3: dss.SuaSach(); break;
                case 4: dss.XoaSach(); break;
                case 5: dss.TimSach(); break;
                case 0: break;
                default: System.out.println("Lua chon khong hop le!");
            }
        } while(c != 0);
    }

    private static void menuTacGia(DanhSachTacGia dstg) {
        Scanner sc = new Scanner(System.in);
        int c;
        do {
            System.out.println("----- QUAN LY TAC GIA -----");
            System.out.println("1. Xem danh sach tac gia");
            System.out.println("2. Them tac gia moi");
            System.out.println("3. Sua thong tin tac gia");
            System.out.println("4. Xoa tac gia");
            System.out.println("5. Tim tac gia");
            System.out.println("0. Quay lai");
            System.out.print("Nhap lua chon: ");
            c = sc.nextInt();
            sc.nextLine();

            switch(c) {
                case 1: dstg.XemTacGia(); break;
                case 2: dstg.ThemTacGia(); break;
                case 3: dstg.SuaTacGia(); break;
                case 4: dstg.XoaTacGia(); break;
                case 5: dstg.TimTacGia(); break;
                case 0: break;
                default: System.out.println("Lua chon khong hop le!");
            }
        } while(c != 0);
    }
    private static void menuTheLoai(DanhSachTheLoai dstl) {
        Scanner sc = new Scanner(System.in);
        int c;
        do {
            System.out.println("----- QUAN LY THE LOAI-----");
            System.out.println("1. Xem danh sach the loai");
            System.out.println("2. Them the loai moi");
            System.out.println("3. Sua thong tin the loai");
            System.out.println("4. Xoa the loai");
            System.out.println("5. Tim the loai");
            System.out.println("0. Quay lai");
            System.out.print("Nhap lua chon: ");
            c = sc.nextInt();
            sc.nextLine();

            switch(c) {
                case 1: dstl.XemTheLoai(); break;
                case 2: dstl.ThemTheLoai(); break;
                case 3: dstl.SuaTheLoai(); break;
                case 4: dstl.XoaTheLoai(); break;
                case 5: dstl.TimTheLoai(); break;
                case 0: break;
                default: System.out.println("Lua chon khong hop le!");
            }
        } while(c != 0);
    }
    private static void menuNhaXuatBan(DanhSachNhaXuatBan dsnxb) {
        Scanner sc = new Scanner(System.in);
        int c;
        do {
            System.out.println("----- QUAN LY NHA XUAT BAN-----");
            System.out.println("1. Xem danh sach nha xuat ban");
            System.out.println("2. Them nha xuat ban moi");
            System.out.println("3. Sua thong tin nha xuat ban");
            System.out.println("4. Xoa nha xuat ban");
            System.out.println("5. Tim nha xuat ban");
            System.out.println("0. Quay lai");
            System.out.print("Nhap lua chon: ");
            c = sc.nextInt();
            sc.nextLine();

            switch(c) {
                case 1: dsnxb.XemNXB(); break;
                case 2: dsnxb.ThemNhaXuatBan(); break;
                case 3: dsnxb.SuaNhaXuatBan(); break;
                case 4: dsnxb.XoaNhaXuatBan(); break;
                case 5: dsnxb.TimNhaXuatBan(); break;
                case 0: break;
                default: System.out.println("Lua chon khong hop le!");
            }
        } while(c != 0);
    }
}