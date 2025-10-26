package com.example;

import java.util.*;
import java.io.*;
public class DanhSachNhaXuatBan {
    private ArrayList<NhaXuatBan> dsnxb = new ArrayList<>();
    private DanhSachSach dss;
    public void setDanhSachSach(DanhSachSach dss) {
        this.dss = dss;
    }
    public DanhSachNhaXuatBan(){
        
    }
    public DanhSachNhaXuatBan(ArrayList<NhaXuatBan> dsnxb){
        this.dsnxb=dsnxb;
    }
    public void DocFileNhaXuatBan(String ten_file){
        try{
            File f = new File(ten_file);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while(line!=null){
                String[] parts = line.split(", ");
                if(parts.length>=2){
                    NhaXuatBan nxb = new NhaXuatBan(parts[0], parts[1]);
                    dsnxb.add(nxb);
                }
                line = br.readLine();
            }
            br.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void GhiFileNhaXuatBan(String ten_file){
        try {
            File f = new File(ten_file);
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            for(NhaXuatBan nxb : dsnxb){
                bw.write(nxb.toString()+"\n");
            }
            bw.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public NhaXuatBan TimNhaXuatBanTheoMa(String ma){
        for(NhaXuatBan nxb : dsnxb){
            if(nxb.getMa_nxb().equals(ma)){
                return nxb;
            }
        }
        return null;
    }
    public void XemNXB(){
        for(NhaXuatBan nxb : dsnxb){
            System.out.println(nxb);
        }
    }
    NhaXuatBan NhapThongTinNhaXuatBan(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap thong tin nha xuat ban: ");
        String ma;
        boolean ma_hop_le  = false;
        do {
            System.out.print("Nhap ma nha xuat ban (NXBxx): ");
            ma = sc.nextLine().trim();
            if (!ma.matches("NXB\\d+")) {
                System.out.println("Ma khong dung dinh dang. Vui long nhap lai!");
                continue;
            }
            ma_hop_le = true;
            for (NhaXuatBan nxb : dsnxb) {
                if (nxb.getMa_nxb().equals(ma)) {
                    System.out.println("Ma da ton tai. Vui long nhap lai!");
                    ma_hop_le = false;
                    break;
                }
            }
        }while(!ma_hop_le);
        System.out.print("Nhap ten nha xuat ban: ");
        String ten = sc.nextLine();
        NhaXuatBan nxb_moi = new NhaXuatBan(ma,ten);
        return nxb_moi;
    }

    NhaXuatBan NhapThongTinNhaXuatBanCoThamSo(String ma_nxb_cu){ 
        Scanner sc = new Scanner(System.in);
        String ma = ma_nxb_cu != null ? ma_nxb_cu : ""; // giữ mã nxb cũ nếu sửa
        if(ma_nxb_cu != null){
            System.out.println("Ma nha xuat ban: " + ma); // hiển thị cho người dùng
        } else {
            System.out.print("Nhap nha xuat ban: ");
            ma = sc.nextLine();
        }

        System.out.print("Nhap ten nha xuat ban: ");
        String ten = sc.nextLine();;

        NhaXuatBan nxb_moi = null;
        nxb_moi = new NhaXuatBan(ma,ten);
        return nxb_moi;
    }

    public void SuaNhaXuatBan(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ma nha xuat ban muon sua: ");
        String ma = sc.nextLine();

        boolean found = false;
        for(int i = 0;i<dsnxb.size();i++){
            if(dsnxb.get(i).getMa_nxb().equals(ma)){
                NhaXuatBan nxb_cu = dsnxb.get(i);
                System.out.println("Nhap thong tin moi cho nha xuat ban:");
                NhaXuatBan nxb_moi = NhapThongTinNhaXuatBanCoThamSo(ma);

                if(nxb_moi == null){
                    System.out.println("Huy sua nha xuat ban.");
                    return;
                }
                dsnxb.set(i,nxb_moi); //thay the nxb cu thanh nxb moi
                GhiFileNhaXuatBan("NhaXuatBan.txt");
                System.out.println("Da sua nha xuat ban co ma "+ma);
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("Khong tim thay nha xuat ban co ma "+ma);
        }
    }
    public void ThemNhaXuatBanKhiSuaSachKhacMa(String ma_nxb){
        Scanner sc = new Scanner(System.in);
        NhaXuatBan nxb_moi = NhapThongTinNhaXuatBanCoThamSo(ma_nxb);
        dsnxb.add(nxb_moi);
        GhiFileNhaXuatBan("NhaXuatBan.txt");
        System.out.println("Da them nha xuat ban moi co ma: " + nxb_moi.getMa_nxb());
    }
    public void ThemNhaXuatBan(){
        Scanner sc = new Scanner(System.in);
        NhaXuatBan nxb_moi = NhapThongTinNhaXuatBan();
        dsnxb.add(nxb_moi);
        GhiFileNhaXuatBan("NhaXuatBan.txt");
        System.out.println("Da them nha xuat moi co ma: " + nxb_moi.getMa_nxb());
        System.out.println("Nhap it nhat 1 quyen sach cho nha xuat ban nay: ");
        while(true){
            Sach sach_moi = dss.NhapThongTinSach();
            sach_moi.setMa_nxb(nxb_moi.getMa_nxb());
            dss.ThemSachTheoMa(sach_moi);
            dss.GhiFileSach("Sach.txt");
            System.out.println("Ban co muon them sach khac cho nha xuat ban nay khong? (c/k): ");
            String chon = sc.nextLine();
            if(!chon.equals("c")){
                break;
            }
        }   
    }
    public void ThemNhaXuatBanKhiSachKhacMa(String ma_nxb){
        Scanner sc = new Scanner(System.in);
        NhaXuatBan nxb_moi = NhapThongTinNhaXuatBanCoThamSo(ma_nxb);
        dsnxb.add(nxb_moi);
        GhiFileNhaXuatBan("NhaXuatBan.txt");
        System.out.println("Da them nha xuat moi co ma: " + nxb_moi.getMa_nxb());
    }

    public void XoaNhaXuatBan(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ma nha xuat ban muon xoa: ");
        String ma = sc.nextLine();
        boolean found = false;
        for(int i = 0;i < dsnxb.size();i++){   //ta dung vong lap chi so vi neu lam for-each ma remove(s) thi khong an toan
            if(dsnxb.get(i).getMa_nxb().equals(ma)){
                NhaXuatBan nxb = dsnxb.get(i);
                // bắt buộc xóa tất cả sách của nha xuat ban này
                for(Sach s : new ArrayList<>(dss.getDSSachCuaNhaXuatBan(ma))){
                    dss.XoaSachTheoMa(s.getMa_sach());
                }
                dsnxb.remove(i);
                found = true;
                System.out.println("Da xoa thanh cong nha xuat ban co ma "+ma);
                break;
            }
        }
        if(!found){
            System.out.println("Khong tim thay ma "+ma+ " de xoa");
        }else{
            GhiFileNhaXuatBan("NhaXuatBan.txt");
        }
    }
    public void TimNhaXuatBan(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ma nha xuat ban muon tim: ");
        String ma = sc.nextLine();
        boolean found = false;
        for(NhaXuatBan nxb : dsnxb){
            if(nxb.getMa_nxb().equals(ma)){
                System.out.println("Da tim thay nha xuat ban co ma "+ma);
                System.out.println(nxb);
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("Khong tim thay nha xuat ban co ma "+ma);
        }
    }

}

