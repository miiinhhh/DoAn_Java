    package com.example;
    import java.io.File;
    import java.io.PrintWriter;
    import java.util.Arrays;
    import java.util.Scanner;

    public class DanhSachPhieuPhat{
        private PhieuPhat[] ds = new PhieuPhat[0];
        private Scanner sc = new Scanner(System.in);
        
        public DanhSachPhieuPhat(){}
        public DanhSachPhieuPhat(PhieuPhat[] ds){ this.ds =ds; }
        public DanhSachPhieuPhat(DanhSachPhieuPhat other) {
            this.ds = Arrays.copyOf(other.ds,other.ds.length);
        }
        public void nhap(DanhSachQuyDinhPhat dsqdp){
            System.out.print("Nhap so luong phieu phat can nhap: ");
            int sl;
            while(true){
                String s = sc.nextLine().trim();
                try{
                    sl = Integer.parseInt(s);
                    if(sl < 0) { System.out.print("So luong phai >= 0. Vui long nhap lai: "); continue;}
                    break;
                } catch(NumberFormatException e){
                    System.out.print("Vui long nhap so nguyen. Nhap lai: ");
                }
            }
            int bd = ds.length;
            ds = Arrays.copyOf(ds,ds.length+sl);
            for(int i = bd; i < ds.length; i++){
                ds[i] = new PhieuPhat();
                ds[i].nhap(dsqdp);
            } 
        }
        public void xuat() {
            System.out.printf("+-----------------+-----------------+-----------------+-----------------+------------+%n");
            System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-10s |%n", "MaPhieuPhat", "MaDocGia", "MaPhieuMuon", "MaPhat", "TienPhat");
            System.out.printf("+-----------------+-----------------+-----------------+-----------------+------------+%n");
            for (PhieuPhat p : ds) {
                if (p != null)
                    System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-10d |%n",
                            p.getMaPhieuPhat(), p.getMaDocGia(), p.getMaPhieuMuon(), p.getMaPhat(), p.getTienPhat());
            }
            System.out.printf("+-----------------+-----------------+-----------------+-----------------+------------+%n");
        }
        public void them(PhieuPhat p){
            if(p == null) return;
            if(timkiemma(p.getMaPhieuPhat()) != -1){
                System.out.println("Ma phieu phat da ton tai !!");
                return;
            }
            ds = Arrays.copyOf(ds,ds.length+1);
            ds[ds.length-1] = new PhieuPhat(p);
            System.out.println("Them thanh cong");
        }
        public void them(DanhSachQuyDinhPhat dsqdp){
            ds = Arrays.copyOf(ds,ds.length+1);
            ds[ds.length-1] = new PhieuPhat();
            System.out.println("Nhap thong tin phieu phat can them: ");
            ds[ds.length-1].nhap(dsqdp);
            System.out.println("Them thanh cong");
        }
        public int timkiemma(String ma){
            if(ma == null) return -1;
            ma = ma.trim();
            for(int i=0; i < ds.length; i++){
                if(ma.equals(ds[i].getMaPhieuPhat())) return i;
            }
            return -1;
        }
        public void inmenusua(){
            System.out.println("\nBan muon sua thong tin gi ?");
            System.out.println("1. Sua ma doc gia");
            System.out.println("2. Sua ma phieu muon");
            System.out.println("3. Sua ma phat");
            System.out.println("4. Sua so tien phat");
            System.out.print("Lua chon cua ban: ");
        }
        public void sua(DanhSachQuyDinhPhat dsqdp){
            if(ds.length == 0){
                System.out.println("Danh sach phieu phat dang rong !!");
                return;
            }
            while(true){
                System.out.print("Nhap ma phieu phat (Nhan Enter de thoat): ");
                String ma = sc.nextLine().trim();
                if(ma.isEmpty()) return;
                int idx = timkiemma(ma);
                if(idx == -1){
                    System.out.println("Khong tim thay phieu phat "+ ma);
                    continue;
                }
                PhieuPhat p = ds[idx];
                while(true){
                    inmenusua();
                    String choice = sc.nextLine().trim();
                    switch(choice){
                        case "1":{
                            System.out.print("Nhap ma doc gia moi (Nhan Enter de giu nguyen): ");
                            String mdg = sc.nextLine().trim();
                            if(!mdg.isEmpty()) p.setMaDocGia(mdg);
                            System.out.println("Cap nhat thanh cong");
                            break;
                        }
                        case "2":{
                            System.out.print("Nhap ma phieu muon moi (Nhan Enter de giu nguyen): ");
                            String mpm = sc.nextLine().trim();
                            if(!mpm.isEmpty()) p.setMaPhieuMuon(mpm);
                            System.out.println("Cap nhat thanh cong");
                            break;
                        }
                        case "3":{
                            System.out.print("Nhap ma phat moi (Nhan Enter de giu nguyen): ");
                            String mp = sc.nextLine().trim();
                            if(!mp.isEmpty()) p.setMaPhat(mp,dsqdp);
                            System.out.println("Cap nhat thanh cong");
                            break;
                        }
                        case "4":{
                            System.out.print("Nhap so tien phat moi (Nhan Enter de giu nguyen): ");
                            String tp = sc.nextLine().trim();
                            if(!tp.isEmpty()){ 
                                try{
                                    p.setTienPhat(Integer.parseInt(tp));
                                } catch(NumberFormatException e){
                                    System.out.println("Nhap sai, giu nguyen tien phat !!");
                                }
                            }
                            System.out.println("Cap nhat thanh cong");
                            break;
                        }
                        case "0":{
                            System.out.println("Quay lai");
                            break;
                        }
                        default:{
                                System.out.println("Lua chon khong hop le !!");
                                continue;
                        }
                    } if("0".equals(choice)) break;
                }
            }
        }
        public void xoa(){
            System.out.println("Nhap ma phieu phat cua phieu phat muon xoa: ");
            System.out.println("Ma phieu phat: ");
            String ma = sc.nextLine().trim();
            xoa(ma);    
        }
        public boolean xoa(String ma) {
            int idx = timkiemma(ma);
            if (idx == -1) {
                System.out.println("Khong tim thay ma: " + ma);
                return false;
            }
            for (int j = idx; j < ds.length - 1; j++) ds[j] = ds[j + 1];
            ds = Arrays.copyOf(ds, ds.length - 1);
            System.out.println("Xoa thanh cong: " + ma);
            return true;
        }
        public void timKiem(String keyword) {
            if (keyword == null) keyword = "";
            boolean found = false;
            for (PhieuPhat p : ds) {
                if (p.getMaPhieuPhat().contains(keyword) || p.getMaDocGia().contains(keyword)) {
                    System.out.printf("%s, %s, %s, %s, %d%n",
                            p.getMaPhieuPhat(), p.getMaDocGia(), p.getMaPhieuMuon(), p.getMaPhat(), p.getTienPhat());
                    found = true;
                }
            }
            if (!found) System.out.println("Khong tim thay.");
        }
        public void hienThiTatCa() {
            if (ds == null || ds.length == 0) {
                System.out.println("Danh sach phieu phat trong!");
                return;
            }
            System.out.printf("+-----------------+-----------------+-----------------+-----------------+------------+%n");
            System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-10s |%n", "MaPhieuPhat", "MaDocGia", "MaPhieuMuon", "MaPhat", "TienPhat");
            System.out.printf("+-----------------+-----------------+-----------------+-----------------+------------+%n");
            for (PhieuPhat p : ds) {
                if (p == null) continue;
                System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-10d |%n",
                        p.getMaPhieuPhat(), p.getMaDocGia(), p.getMaPhieuMuon(), p.getMaPhat(), p.getTienPhat());
            }
            System.out.printf("+-----------------+-----------------+-----------------+-----------------+------------+%n");
        }
        public void docFile(DanhSachQuyDinhPhat dsqdp){
            File file = new File("Phieuphat.txt");
            if(!file.exists()){ System.out.println("File khong ton tai !!"); return;}
            ds = new PhieuPhat[0];
            try (Scanner f = new Scanner(file,"UTF-8")){
                while(f.hasNextLine()){
                    String line = f.nextLine().trim();
                    if(line.isEmpty()) continue;
                    String[] parts = line.split(",",-1);
                    if(parts.length >= 5){
                        String mpp = parts[0].trim();
                        String mdg = parts[1].trim();
                        String mpm = parts[2].trim();
                        String mp = parts[3].trim();
                        String st = parts[4].trim();
                        int tp = 0;
                        try{
                            tp = st.isEmpty()? 0 : Integer.parseInt(st);
                        }catch(NumberFormatException e){
                            System.out.println("Dong loi (so tien): "+line); continue;
                        }
                        ds = Arrays.copyOf(ds,ds.length+1);
                        PhieuPhat pp = new PhieuPhat(mpp, mdg, mpm, mp, dsqdp);
                        pp.setTienPhat(tp);
                        ds[ds.length-1] = pp;
                    }
                }
                System.out.println("Doc file thanh cong");
            }catch(Exception e){
                System.out.println("Loi doc file: "+ e.getMessage());
            }
        }
        public void ghiFile() {
            try (PrintWriter w = new PrintWriter("Phieuphat.txt", "UTF-8")) {
                for (PhieuPhat p : ds) {
                    w.println(p.toFile());
                }
                System.out.println("Ghi file thanh cong.");
            } catch (Exception e) {
                System.out.println("Loi ghi file: " + e.getMessage());
            }
        }
        public int getSoLuong(){
            return ds.length;
        }

    }