package com.example;

import java.util.*;
import java.io.*;
public class DanhSachTheLoai {
    private ArrayList<TheLoai> dstl = new ArrayList<>();
    private DanhSachSach dss;

    public void setDanhSachSach(DanhSachSach dss) {
        this.dss = dss;
    }

    public DanhSachTheLoai(){
        
    }
    public DanhSachTheLoai(ArrayList<TheLoai> dstl){
        this.dstl=dstl;
    }
    public void DocFileTheLoai(String ten_file){
        try{
            File f = new File(ten_file);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while(line!=null){
                String[] parts = line.split(", ");
                if(parts.length>=3){
                    TheLoai tl = new TheLoai(parts[0], parts[1], parts[2]);
                    dstl.add(tl);
                }
                line = br.readLine();
            }
            br.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void GhiFileTheLoai(String ten_file){
        try {
            File f = new File(ten_file);
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            for(TheLoai tl : dstl){
                bw.write(tl.toString()+"\n");
            }
            bw.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public TheLoai TimTheLoaiTheoMa(String ma){
        for(TheLoai tl : dstl){
            if(tl.getMa_the_loai().equals(ma)){
                return tl;
            }
        }
        return null;
    }

    public void ThemTheLoaiKhiSuaSachKhacMa(String ma_tl){
        Scanner sc = new Scanner(System.in);
        TheLoai tl_moi = NhapThongTinTheLoaiCoThamSo(ma_tl);
        dstl.add(tl_moi);
        GhiFileTheLoai("TheLoai.txt");
        System.out.println("Da them the loai moi co ma: " + tl_moi.getMa_the_loai());
    }
    public void XemTheLoai(){
        for(TheLoai tl : dstl){
            System.out.println(tl);
        }
    }
    TheLoai NhapThongTinTheLoai(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap thong tin the loai: ");
        String ma;
        boolean ma_hop_le  = false;
        do {
            System.out.print("Nhap ma the loai (TLxx): ");
            ma = sc.nextLine().trim();
            if (!ma.matches("TL\\d+")) {
                System.out.println("Ma khong dung dinh dang. Vui long nhap lai!");
                continue;
            }
            ma_hop_le = true;
            for (TheLoai tl : dstl) {
                if (tl.getMa_the_loai().equals(ma)) {
                    System.out.println("Ma da ton tai. Vui long nhap lai!");
                    ma_hop_le = false;
                    break;
                }
            }
        }while(!ma_hop_le);
        System.out.print("Nhap ten the loai: ");
        String ten = sc.nextLine();
        System.out.print("Nhap mo ta the loai: ");
        String mt = sc.nextLine();
        TheLoai the_loai_moi = new TheLoai(ma,ten,mt);
        return the_loai_moi;
    }

    TheLoai NhapThongTinTheLoaiCoThamSo(String ma_tl_cu){ 
        Scanner sc = new Scanner(System.in);
        String ma = ma_tl_cu != null ? ma_tl_cu : ""; // giữ mã thể loại cũ nếu sửa
        if(ma_tl_cu != null){
            System.out.println("Ma the loai: " + ma); // hiển thị cho người dùng
        } else {
            System.out.print("Nhap ma the loai: ");
            ma = sc.nextLine();
        }

        System.out.print("Nhap ten the loai: ");
        String ten = sc.nextLine();
        System.out.print("Nhap mo ta the loai: ");
        String mt = sc.nextLine();

        TheLoai tl_moi = null;
        tl_moi = new TheLoai(ma,ten,mt);
        return tl_moi;
    }

    public void SuaTheLoai(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ma the loai muon sua: ");
        String ma = sc.nextLine();

        boolean found = false;
        for(int i = 0;i<dstl.size();i++){
            if(dstl.get(i).getMa_the_loai().equals(ma)){
                TheLoai tl_cu = dstl.get(i);
                System.out.println("Nhap thong tin moi cho the loai:");
                TheLoai tl_moi = NhapThongTinTheLoaiCoThamSo(ma);

                if(tl_moi == null){
                    System.out.println("Huy sua the loai.");
                    return;
                }
                dstl.set(i,tl_moi); //thay the the loai cu thanh the loai moi
                GhiFileTheLoai("TheLoai.txt");
                System.out.println("Da sua the loai co ma "+ma);
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("Khong tim thay the loai co ma "+ma);
        }
    }

    public void ThemTheLoai(){
        Scanner sc = new Scanner(System.in);
        TheLoai the_loai_moi = NhapThongTinTheLoai();
        dstl.add(the_loai_moi);
        GhiFileTheLoai("TheLoai.txt");
        System.out.println("Da them the loai moi co ma: " + the_loai_moi.getMa_the_loai());
        System.out.println("Nhap it nhat 1 quyen sach cho the loai nay: ");
        while(true){
            Sach sach_moi = dss.NhapThongTinSach();
            sach_moi.setMa_tac_gia(the_loai_moi.getMa_the_loai());
            dss.ThemSachTheoMa(sach_moi);
            dss.GhiFileSach("Sach.txt");
            System.out.println("Ban co muon them sach khac cho the loai nay khong? (c/k): ");
            String chon = sc.nextLine();
            if(!chon.equals("c")){
                break;
            }
        }   
    }
    public void ThemTheLoaiKhiSachKhacMa(String ma_tl){
        Scanner sc = new Scanner(System.in);
        TheLoai the_loai_moi = NhapThongTinTheLoaiCoThamSo(ma_tl);
        dstl.add(the_loai_moi);
        GhiFileTheLoai("TheLoai.txt");
        System.out.println("Da them the loai moi co ma: " + the_loai_moi.getMa_the_loai());
    }
    public void TimTheLoai(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ma the loai muon tim: ");
        String ma = sc.nextLine();
        boolean found = false;
        for(TheLoai tl : dstl){
            if(tl.getMa_the_loai().equals(ma)){
                System.out.println("Da tim thay the loai co ma "+ma);
                System.out.println(tl);
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("Khong tim thay the loai co ma "+ma);
        }
    }
    public void XoaTheLoai(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ma the loai muon xoa: ");
        String ma = sc.nextLine();
        boolean found = false;
        for(int i = 0;i < dstl.size();i++){   //ta dung vong lap chi so vi neu lam for-each ma remove(s) thi khong an toan
            if(dstl.get(i).getMa_the_loai().equals(ma)){
                TheLoai tl = dstl.get(i);
                // bắt buộc xóa tất cả sách của the loai này
                for(Sach s : new ArrayList<>(dss.getDSSachCuaTheLoai(ma))){
                    dss.XoaSachTheoMa(s.getMa_sach());
                }
                dstl.remove(i);
                found = true;
                System.out.println("Da xoa thanh cong the loai co ma "+ma);
                break;
            }
        }
        if(!found){
            System.out.println("Khong tim thay ma "+ma+ " de xoa");
        }else{
            GhiFileTheLoai("TheLoai.txt");
        }
    }
    
}

