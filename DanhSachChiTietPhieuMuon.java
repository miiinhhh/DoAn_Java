import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class DanhSachChiTietPhieuMuon {
    ChiTietPhieuMuon[] ds = new ChiTietPhieuMuon[0];
    Scanner sc = new Scanner(System.in);
    public DanhSachChiTietPhieuMuon(){}
    public DanhSachChiTietPhieuMuon(ChiTietPhieuMuon[] ds1){
        ds = Arrays.copyOf(ds1,ds1.length);
    }
    public DanhSachChiTietPhieuMuon(DanhSachChiTietPhieuMuon other){
        ds = Arrays.copyOf(other.ds, other.ds.length);
    }
    public void nhap(){
        System.out.print("Nhap so luong chi tiet phieu muon: ");
        int bd = ds.length;
        int k ;
        while (true) {
            String s = sc.nextLine().trim();
            try {
                k = Integer.parseInt(s);
                if (k < 0) { System.out.println("So luong phai >= 0. Vui long nhap lai: "); continue; }
                break;
            } catch (NumberFormatException e) {
                System.out.print("Vui long nhap so nguyen. Nhap lai: ");
            }
        }
        ds = Arrays.copyOf(ds,ds.length + k);
        for(int i = bd; i < ds.length; i++){
            ds[i] = new ChiTietPhieuMuon();
            ds[i].nhap();
        }
    }
    public void xuatt(){
        System.out.printf("+-----------------+-----------------+------------+\n");
        System.out.printf("| %-15s | %-15s | %-10s |%n","Ma phieu muon","Ma sach","So luong");
        System.out.printf("|-----------------|-----------------|------------|\n");
    }
    public void xuatd(){
        System.out.printf("+-----------------+-----------------+------------+\n");
    }
    public void xuat(){
        xuatt();
        for(ChiTietPhieuMuon ctpm : ds){
            if( ctpm == null) continue;
            ctpm.xuat();
        }
        xuatd();
    }
    public void them(ChiTietPhieuMuon ctpm){
        ds = Arrays.copyOf(ds,ds.length + 1);
        ds[ds.length -1] = new ChiTietPhieuMuon(ctpm);
        System.out.println("Them thanh cong");
    }
    public void them(){
        ds = Arrays.copyOf(ds,ds.length+1);
        ds[ds.length-1] = new ChiTietPhieuMuon();
        System.out.println("Nhap thong tin chi tiet phieu muon them: ");
        ds[ds.length-1].nhap();
        System.out.println("Them thanh cong");
    }
    public int timkiemma(String mapm) {
        if (mapm == null) return -1;
        mapm = mapm.trim();
        for (int i = 0; i < ds.length; i++) {
            ChiTietPhieuMuon ct = ds[i];
            if (ct == null) continue;
            if (mapm.equals(ct.getMaPhieuMuon())) return i;
        }
        return -1;
    }
    private void inmenusua(){
        System.out.println("\nBan muon sua thong tin gi ?");
        System.out.println("1. Sua ma sach");
        System.out.println("2. Sua so luong");
        System.out.println("0. Quay lai");
        System.out.print("Lua chon cua ban :");
    }
    public void sua(){
        if(ds.length == 0){
            System.out.println("Danh sach chi tiet phieu muon dang rong !!");
            return;
        }
        while(true){
            System.out.print("\nNhap ma phieu muon (Nhan Enter de thoat): ");
            String mapm = sc.nextLine().trim();
            if(mapm.isEmpty()) return;
            System.out.print("Nhap ma sach: ");
            String mas = sc.nextLine().trim();
            if(mas.isEmpty()){
                System.out.println("Ma sach khong duoc de trong !!");
                continue;
            }
            int idx = timkiemma(mapm);
            if(idx == -1){
                System.out.println("Khong tim thay phieu chi tiet: "+ mapm + "," + mas);
                continue;
            }
            ChiTietPhieuMuon ct = ds[idx];
            while (true){
                inmenusua();
                String choice = sc.nextLine().trim();
                switch(choice){
                    case "1":{
                        System.out.print("Nhap ma sach moi: ");
                        String newMa = sc.nextLine().trim();
                        if(newMa.isEmpty()){
                            System.out.println("Ma sach khong duoc de trong !!");
                        }
                        else {
                            ct.setMaSach(newMa);
                            System.out.println("Cap nhat thanh cong");
                        }
                        break;
                    }
                    case "2":{
                        while(true){
                            System.out.print("Nhap so luong moi (>= 0): ");
                            String s = sc.nextLine().trim();
                            try{
                                int s1 = Integer.parseInt(s);
                                if(s1 < 0){
                                    System.out.println(("So luong phai >= 0"));
                                    continue;
                                }
                                ct.setSoLuong(s1);
                                System.out.println("Cap nhat thanh cong");
                                break;
                            }catch (NumberFormatException e){
                                   System.out.println("Vui long nhap so nguyen !!");
                            }
                        }
                        break;
                    }
                    case "0":{
                        System.out.println("Quay lai");
                        break;
                    }
                    default: {
                        System.out.println("Lua chon khong hop le !!");
                        continue;
                    }
                }
                if("0".equals(choice)) break;
            }
        }
    }
    
    public void docFile(){
        File file = new File("Chitietphieumuon.txt");
        if(!file.exists()){
            System.out.println("File khong ton tai.");
            return;
        }
        ds = new ChiTietPhieuMuon[0];
        try(Scanner f = new Scanner(file, "UTF-8")){
            while(f.hasNextLine()){
                String line = f.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(",", -1);
                if(parts.length >= 3){
                    String pm = parts[0].trim();
                    String ms = parts[1].trim();
                    String sls = parts[2].trim();
                    try {
                        int sl = sls.isEmpty() ? 0 : Integer.parseInt(sls);
                        ds = Arrays.copyOf(ds, ds.length + 1);
                        ds[ds.length - 1] = new ChiTietPhieuMuon(pm, ms, sl);
                    } catch (NumberFormatException ex){
                        System.out.println("Dong loi (so luong): " + line);
                    }
                }
            }
            System.out.println("Doc file thanh cong.");
        }catch(Exception e){
            System.out.println("Loi doc file: " + e.getMessage());
        }
    }
    public void ghiFile(){
        try(PrintWriter w = new PrintWriter("Chitietphieumuon.txt","UTF-8")){
            for(ChiTietPhieuMuon ctpm : ds){
                w.println(ctpm.toFile());
            }
            System.out.println("Ghi file thanh cong.");
        }catch(Exception e){
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    
    public void xoa(){
            System.out.println("Nhap ma phieu muon can xoa: ");
            String ma = sc.nextLine().trim();
            xoa(ma);    
        }
        public boolean xoa(String mapm) {
            int idx = timkiemma(mapm);
            if (idx == -1) {
                System.out.println("Khong tim thay ma: " + mapm);
                return false;
            }
            for (int j = idx; j < ds.length - 1; j++) ds[j] = ds[j + 1];
            ds = Arrays.copyOf(ds, ds.length - 1);
            System.out.println("Xoa thanh cong: " + mapm);
            return true;
        }

    public void hienThiTatCa() {
        if (ds == null || ds.length == 0) {
            System.out.println("Danh sach chi tiet phieu muon trong!");
            return;
        }
        System.out.printf("+-----------------+-----------------+------------+%n");
        System.out.printf("| %-15s | %-15s | %-10s |%n", "MaPhieuMuon", "MaSach", "SoLuong");
        System.out.printf("+-----------------+-----------------+------------+%n");
        for (ChiTietPhieuMuon ctpm : ds) {
            if (ctpm == null) continue;
            System.out.printf("| %-15s | %-15s | %10d |%n",
                    ctpm.getMaPhieuMuon(), ctpm.getMaSach(), ctpm.getSoLuong());
        }
        System.out.printf("+-----------------+-----------------+------------+%n");
    }

    public void timKiem(String keyword) {
        if (keyword == null) keyword = "";
        keyword = keyword.trim();
        boolean found = false;
        for (ChiTietPhieuMuon ctpm : ds) {
            if (ctpm == null) continue;
            String a = ctpm.getMaPhieuMuon();
            String b = ctpm.getMaSach();
            if ((a != null && a.contains(keyword)) || (b != null && b.contains(keyword))) {
                System.out.printf("%s, %s, %d%n",
                        ctpm.getMaPhieuMuon(), ctpm.getMaSach(), ctpm.getSoLuong());
                found = true;
            }
        }
        if (!found) System.out.println("Khong tim thay.");
    }

    public int getSoLuong(){
        return ds.length;
    }

}