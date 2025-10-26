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
        String ma;
        boolean ma_hop_le  = false;
        do {
            System.out.print("Nhap ma tac gia (TGxx): ");
            ma = sc.nextLine().trim();
            if (!ma.matches("TG\\d+")) {
                System.out.println("Ma khong dung dinh dang. Vui long nhap lai!");
                continue;
            }
            ma_hop_le = true;
            for (TacGia tg : dstg) {
                if (tg.getMa_tac_gia().equals(ma)) {
                    System.out.println("Ma da ton tai. Vui long nhap lai!");
                    ma_hop_le = false;
                    break;
                }
            }
        }while(!ma_hop_le);
        System.out.print("Nhap ho ten tac gia: ");
        String ten = sc.nextLine();
        System.out.print("Nhap gioi tinh tac gia: ");
        String gt = sc.nextLine();
        System.out.print("Nhap ngay sinh tac gia: ");
        int d,m,y;
        boolean hopLe;
        do {
            d = sc.nextInt();
            m = sc.nextInt();
            y = sc.nextInt();
            hopLe = dss.kiemTraHopLe(d, m, y);
            if (!hopLe) {
                System.out.println("Ngay thang nam khong hop le, vui long nhap lai!\n");
            }
        } while (!hopLe);
        Ngay date = new Ngay(d,m,y);
        sc.nextLine();
        TacGia tac_gia_moi = new TacGia(ma,ten,gt,date);
        return tac_gia_moi;
    }
    TacGia NhapThongTinTacGiaCoThamSo(String ma_tg_cu){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap thong tin tac gia: ");
        String ma = ma_tg_cu != null ? ma_tg_cu : ""; // giữ mã sách cũ nếu sửa
        if(ma_tg_cu != null){
            System.out.println("Ma tac gia: " + ma); // hiển thị cho người dùng
        } else {
            System.out.print("Nhap ma tac gia: ");
            ma = sc.nextLine();
        }
        System.out.print("Nhap ho ten tac gia: ");
        String ten = sc.nextLine();
        System.out.print("Nhap gioi tinh tac gia: ");
        String gt = sc.nextLine();
        System.out.print("Nhap ngay sinh tac gia: ");
        int d,m,y;
        boolean hopLe;
        do {
            d = sc.nextInt();
            m = sc.nextInt();
            y = sc.nextInt();
            hopLe = dss.kiemTraHopLe(d, m, y);
            if (!hopLe) {
                System.out.println("Ngay thang nam khong hop le, vui long nhap lai!\n");
            }
        } while (!hopLe);
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
            dss.ThemSachTheoMa(sach_moi);
            dss.GhiFileSach("Sach.txt");
            System.out.println("Ban co muon them sach khac cho tac gia nay khong? (c/k): ");
            String chon = sc.nextLine();
            if(!chon.equals("c")){
                break;
            }
        }   
    }
    public void ThemTacGiaKhiSuaSachKhacMa(String ma_tg){
        Scanner sc = new Scanner(System.in);
        TacGia tac_gia_moi = NhapThongTinTacGiaCoThamSo(ma_tg);
        dstg.add(tac_gia_moi);
        GhiFileTacGia("TacGia.txt");
        System.out.println("Da them tac gia moi co ma: " + tac_gia_moi.getMa_tac_gia());
    }

    public void ThemTacGiaKhiSachKhacMa(String ma_tg){
        Scanner sc = new Scanner(System.in);
        TacGia tac_gia_moi = NhapThongTinTacGiaCoThamSo(ma_tg);
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
        for(int i = 0;i<dstg.size();i++){
            if(dstg.get(i).getMa_tac_gia().equals(ma)){
                TacGia tl_cu = dstg.get(i);
                System.out.println("Nhap thong tin moi cho the loai:");
                TacGia tg_moi = NhapThongTinTacGiaCoThamSo(ma);
                if(tg_moi == null){
                    System.out.println("Huy sua tac gia.");
                    return;
                }
                dstg.set(i,tg_moi); //thay the the loai cu thanh the loai moi
                GhiFileTacGia("TacGia.txt");
                System.out.println("Da sua tac gia co ma "+ma);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay tac gia co ma " + ma);
        }
    }
}

