package com.example;

import java.util.*;
import java.io.*;
public class DanhSachTacGia {
    private ArrayList<TacGia> dstg = new ArrayList<>();
    private DanhSachSach dss; // thêm vào class
    public void setDanhSachSach(DanhSachSach dss) {
        this.dss = dss;
    }

    public DanhSachTacGia(){
        
    }
    public DanhSachTacGia(ArrayList<TacGia> dstg){
        this.dstg = dstg;
    }

    public void DocFileTacGia(String ten_file){
        try{
            File f = new File(ten_file);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while(line!=null){
                String[] parts = line.split(", ");
                if(parts.length>=4){
                    Ngay day = Ngay.parseNgay(parts[3]);
                    TacGia tg = new TacGia(parts[0], parts[1], parts[2], day);
                    dstg.add(tg);
                }
                line = br.readLine();
            }
            br.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void GhiFileTacGia(String ten_file){
        try {
            File f = new File(ten_file);
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            for(TacGia tg : dstg){
                bw.write(tg.toString()+"\n");
            }
            bw.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public TacGia TimTacGiaTheoMa(String ma){
        for(TacGia tg : dstg){
            if(tg.getMa_tac_gia().equals(ma)){
                return tg;
            }
        }
        return null;
    }
    public void XemTacGia(){
        for(TacGia tg : dstg){
            System.out.println(tg);
        }
    }
    TacGia NhapThongTinTacGia(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap thong tin tac gia: ");
        System.out.print("Nhap ma tac gia: ");
        String ma = sc.nextLine();
        System.out.print("Nhap ho ten tac gia: ");
        String ten = sc.nextLine();
        System.out.print("Nhap gioi tinh tac gia: ");
        String gt = sc.nextLine();
        System.out.print("Nhap ngay sinh tac gia: ");
        int d = sc.nextInt(), m = sc.nextInt(), y = sc.nextInt();
        Ngay date = new Ngay(d,m,y);
        sc.nextLine();
        TacGia tac_gia_moi = new TacGia(ma,ten,gt,date);
        return tac_gia_moi;
    }
    public void ThemTacGia(){
        Scanner sc = new Scanner(System.in);
        TacGia tac_gia_moi = NhapThongTinTacGia();
        dstg.add(tac_gia_moi);
        GhiFileTacGia("TacGia.txt");
        System.out.println("Da them tac gia moi co ma: " + tac_gia_moi.getMa_tac_gia());
        System.out.println("Nhap it nhat 1 quyen sach cho tac gia nay: ");
        while(true){
            Sach sach_moi = dss.NhapThongTinSach();
            sach_moi.setMa_tac_gia(tac_gia_moi.getMa_tac_gia());
            tac_gia_moi.themSach(sach_moi);
            dss.ThemSachTheoMa(sach_moi);
            dss.GhiFileSach("Sach.txt");
            System.out.println("Ban co muon them sach khac cho tac gia nay khong? (c/k): ");
            String chon = sc.nextLine();
            if(!chon.equals("c")){
                break;
            }
        }   
    }
    public void ThemTacGiaKhiSachKhacMa(){
        Scanner sc = new Scanner(System.in);
        TacGia tac_gia_moi = NhapThongTinTacGia();
        dstg.add(tac_gia_moi);
        GhiFileTacGia("TacGia.txt");
        System.out.println("Da them tac gia moi co ma: " + tac_gia_moi.getMa_tac_gia());
    }
    
    public void XoaTacGia(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ma tac gia muon xoa: ");
        String ma = sc.nextLine();
        boolean found = false;
        for(int i = 0;i < dstg.size();i++){   //ta dung vong lap chi so vi neu lam for-each ma remove(s) thi khong an toan
            if(dstg.get(i).getMa_tac_gia().equals(ma)){
                TacGia tg = dstg.get(i);
                // bắt buộc xóa tất cả sách của tác giả này
                for(Sach s : new ArrayList<>(dss.getDSSachCuaTacGia(ma))){
                    dss.XoaSachTheoMa(s.getMa_sach());
                }
                dstg.remove(i);
                found = true;
                System.out.println("Da xoa thanh cong tac gia co ma "+ma);
                break;
            }
        }
        if(!found){
            System.out.println("Khong tim thay ma "+ma+ " de xoa");
        }else{
            GhiFileTacGia("TacGia.txt");
        }
    }
    public void XoaTacGiaTheoMa(String ma){
        for(int i = 0; i < dstg.size(); i++){
            if(dstg.get(i).getMa_tac_gia().equals(ma)){
                dstg.remove(i);
                GhiFileTacGia("TacGia.txt");
                System.out.println("Da xoa tac gia co ma " + ma + " vi khong con sach nao.");
                break;
            }
        }
    }

    public void TimTacGia(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ma tac gia muon tim: ");
        String ma = sc.nextLine();
        boolean found = false;
        for(TacGia tg : dstg){
            if(tg.getMa_tac_gia().equals(ma)){
                System.out.println("Da tim thay tac gia co ma "+ma);
                System.out.println(tg);
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("Khong tim thay tac gia co ma "+ma);
        }
    }
   
    public void SuaTacGia() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma tac gia muon sua: ");
        String ma = sc.nextLine();

        boolean found = false;
        for (int i = 0; i < dstg.size(); i++) {
            if (dstg.get(i).getMa_tac_gia().equals(ma)) {
                String ma_cu = dstg.get(i).getMa_tac_gia();
                System.out.println("Nhap thong tin moi cho tac gia:");
                TacGia tac_gia_moi = NhapThongTinTacGia();
                dstg.set(i, tac_gia_moi);

                // Liên kết sang sách (dss là biến thành viên của lớp)
                ArrayList<Sach> sachCuaTG = dss.getDSSachCuaTacGia(ma_cu);
                for (Sach s : sachCuaTG) {
                    s.setMa_tac_gia(tac_gia_moi.getMa_tac_gia());
                }

                // Ghi lại dữ liệu
                dss.GhiFileSach("Sach.txt");
                GhiFileTacGia("TacGia.txt");

                System.out.println("Da sua tac gia co ma " + ma);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Khong tim thay tac gia co ma " + ma);
        }
    }

    public void XemSachCuaTacGia(String ma) {
        TacGia tg = TimTacGiaTheoMa(ma);
        if (tg != null) {
            System.out.println("Cac sach cua tac gia " + tg.getHo_ten() + ":");
            for (Sach s : tg.getDsSach()) {
                System.out.println("  - " + s.getTen_sach());
            }
        } else {
            System.out.println("Khong tim thay tac gia co ma " + ma);
        }
    }

}

