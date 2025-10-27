    package com.example;
    import java.io.File;
    import java.io.PrintWriter;
    import java.time.LocalDate;
    import java.time.format.DateTimeFormatter;
    import java.time.format.DateTimeParseException;
    import java.util.Arrays;
    import java.util.Scanner;

    public class DanhSachPhieuMuon{
        PhieuMuon[] ds = new PhieuMuon[0];
        Scanner sc = new Scanner(System.in);
        public DanhSachPhieuMuon(){}
        public DanhSachPhieuMuon(PhieuMuon[] ds1){
            this.ds = Arrays.copyOf(ds1,ds1.length);
        }
        public DanhSachPhieuMuon(DanhSachPhieuMuon other){
            this.ds = Arrays.copyOf(other.ds,other.ds.length);
        }
        private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
        public void nhap(DanhSachDocGia dsDG, DanhSachNhanVien dsNV){
            System.out.println("Nhap so luong phieu muon can nhap: ");
            int k;
            int count = 0;
            while(true){
                String s = sc.nextLine().trim();
                try{
                    k = Integer.parseInt(s);
                    if(k < 0){ System.out.print("So luong phai >= 0. Vui long nhap lai: "); continue; }
                    break;
                }catch(NumberFormatException e){
                    System.out.print("Vui long nhap so nguyen: ");
                }
            }
            for(int i = 0; i < k; i++){
                PhieuMuon newPhieu = new PhieuMuon();
                System.out.println("\n--- Nhap thong tin phieu muon thu " + (i + 1) + "---");
                newPhieu.nhap(dsDG,dsNV);
                if(timkiemma(newPhieu.getMaPhieuMuon()) != -1){
                    System.out.println("Loi: Ma phieu muon"+ newPhieu.getMaPhieuMuon()+"da ton tai. Vui long nhap lai !!");
                    i--;
                    continue;
                }
                ds = Arrays.copyOf(ds, ds.length + 1);
                ds[ds.length -1]= newPhieu;
                count++;
            }
            System.out.println("Them thanh cong " + count + " phieu muon.");
        }
        public void them(PhieuMuon ht){
            if(ht == null) return;
            if(timkiemma(ht.getMaPhieuMuon()) != -1 ){
                System.out.println("Ma phieu muon da ton tai !!");
                return;
            }
            ds = Arrays.copyOf(ds,ds.length+1);
            ds[ds.length-1] = new PhieuMuon(ht);
            System.out.println("Them thanh cong");
        }
        public void them(DanhSachDocGia dsDG,DanhSachNhanVien dsNV){
            PhieuMuon newPhieu = new PhieuMuon();
            System.out.println("Nhap thong tin phieu muon can them: ");
            newPhieu.nhap(dsDG,dsNV);
            if(timkiemma(newPhieu.getMaPhieuMuon()) != -1 ){ 
                System.out.println("Ma phieu muon da ton tai !! Khong them duoc.");
                return;
            }
            ds = Arrays.copyOf(ds, ds.length + 1);
            ds[ds.length - 1] = newPhieu;
            System.out.println("Them thanh cong");
        }
        public int timkiemma (String ma){
            if(ma == null) return -1;
            ma = ma.trim();
            for(int i = 0; i < ds.length; i++){
                if(ds[i].getMaPhieuMuon().equals(ma)) return i;
            }
            return -1;
        }
        private boolean ngayhople(String s){
            try{
                LocalDate.parse(s,FMT);
                return true;
            } catch(DateTimeParseException e){
                return false;
            }
        }
        private void inmenusua(){
            System.out.println("\nBan muon sua thong tin gi ?");
            System.out.println("1. Sua ma phieu muon");
            System.out.println("2. Sua ma doc gia");
            System.out.println("3. Sua ma nhan vien");
            System.out.println("4. Sua ngay lap phieu");
            System.out.println("5. Sua ngay tra du kien");
            System.out.println("6. Sua ngay tra thuc te");
            System.out.println("0. Quay lai");
            System.out.print("Lua chon cua ban la: ");
        }
        public void sua(DanhSachDocGia dsdg, DanhSachNhanVien dsnv){
            if(ds.length == 0){
                System.out.println("Danh sach dang bi rong !!");
                return;
            }
            while(true){
                System.out.print("\nNhap ma phieu muon can sua (Nhan Enter de thoat): ");
                String ma = sc.nextLine().trim();
                if(ma.isEmpty()) return;
                
                int idx = timkiemma(ma);
                if(idx == -1){
                    System.out.println("Khong tim thay ma phieu muon !!");
                    continue;
                }
                PhieuMuon ht = ds[idx];
                while (true){
                    inmenusua();
                    String choice = sc.nextLine().trim();
                    switch(choice){
                        case "1":{
                            String oldMa = ht.getMaPhieuMuon();
                            System.out.print("Nhap ma phieu muon moi: ");
                            String newMa = sc.nextLine().trim();
                            if (newMa.equals(oldMa)) {
                                System.out.println("Ma phieu muon khong thay doi. Bo qua cap nhat.");
                                break;
                            }
                            if (timkiemma(newMa) != -1) {
                                System.out.println("Loi: Ma phieu muon moi da ton tai trong danh sach !!");
                                break;
                            }
                            ht.setMaPhieuMuon(newMa); 
                            System.out.println("Da cap nhat thanh cong");
                            break;
                        }
                        case "2": {
                            System.out.print("Nhap ma doc gia moi: ");
                            String newMaDG = sc.nextLine().trim();
                            if (dsdg.timkiemma(newMaDG) == -1) { 
                                System.out.println("Loi: Ma doc gia moi khong ton tai!");
                                break;
                            }
                            ht.setMaDocGia(newMaDG);
                            System.out.println("Da cap nhat thanh cong");
                            break; 
                        }
                        case "3": {
                            System.out.print("Nhap ma nhan vien moi: ");
                            String newMaNV = sc.nextLine().trim();
                            if (dsnv.timkiemma(newMaNV) == -1) { 
                                System.out.println("Loi: Ma nhan vien moi khong ton tai!");
                                break;
                            }
                            ht.setMaNhanVien(newMaNV);
                            System.out.println("Da cap nhat thanh cong");
                            break;
                        }
                        case "4": {
                            System.out.print("Nhap ngay lap phieu moi (dd/MM/yyyy): ");
                            String s = sc.nextLine().trim();
                            if(ngayhople(s)){
                                ht.setNgayLapPhieu(s);
                                System.out.println("Da cap nhat thanh cong");
                            }
                            else {
                                System.out.println("Ngay khong hop le !!!");
                            }
                            break;
                        }
                        case "5": {
                            System.out.print("Nhap ngay tra du kien moi (dd/MM/yyyy): ");
                            String s = sc.nextLine().trim();
                            if(ngayhople(s)){
                                ht.setNgayTraDuKien(s);
                                System.out.println("Da cap nhat thanh cong");
                            }
                            else {
                                System.out.println("Ngay khong hop le !!!");
                            }
                            break;
                        }
                        case "6": {
                            System.out.print("Nhap ngay tra thuc te moi (dd/MM/yyyy): ");
                            String s = sc.nextLine().trim();
                            if(s.isEmpty()){
                                ht.setNgayTraThucTe("");
                                System.out.println("Da xoa ngay tra thuc te");
                            }
                            else if(ngayhople(s)){
                                ht.setNgayTraThucTe(s);
                                System.out.println("Da cap nhat thanh cong");
                            }
                            else {
                                System.out.println("Ngay khong hop le !!!");
                            }
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
                    }
                    if ("0".equals(choice)) break;
                }
            }
        }
        public void xoa(){
                System.out.println("Nhap ma phieu muon can xoa: ");
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
        public void docFile(){
            File file = new File("src/main/java/com/example/Phieumuon.txt");
            if(!file.exists()){
                System.out.println("File khong ton tai !!");
                return;
            }
            ds = new PhieuMuon[0];
            try(Scanner f =new Scanner(file,"UTF-8")){
                while (f.hasNextLine()){
                    String line = f.nextLine().trim();
                    if(line.isEmpty()) continue;
                    String[] parts = line.split(",",-1);
                    if(parts.length >= 6){
                        for(int i = 0; i < 6; i++) parts[i] = parts[i].trim();
                        ds = Arrays.copyOf(ds,ds.length+1);
                        ds[ds.length-1] = new PhieuMuon(parts[0],parts[1], parts[2],parts[3], parts[4], parts[5]);
                    }
                }
                System.out.println("Doc du lieu tu file Phieumuon.txt thanh cong");
            }catch (Exception e){
                System.out.println("Loi doc file: " + e);
            }
        }
        public void ghiFile(){
            try(PrintWriter pw = new PrintWriter("src/main/java/com/example/Phieumuon.txt","UTF-8")){
                for( PhieuMuon pm : ds){
                    pw.println(pm.toFile());
                }
                System.out.println("Ghi du lieu vao file Phieumuon.txt thanh cong");  
            }catch(Exception e){
                System.out.println("Loi ghi file: " +e); 
            }
        }
        public void hienThiTatCa() {
                if (ds == null || ds.length == 0) {
                    System.out.println("Danh sach phieu muon trong!");
                    return;
                }
                System.out.printf("+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+%n");
                System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-15s | %-16s |%n", "MaPhieuMuon", "MaDocGia", "MaNhanVien", "NgayLapPhieu", "NgayTraDukien","NgayTraThucte");
                System.out.printf("+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+%n");
                for (PhieuMuon pm : ds) {
                    if (pm == null) continue;
                    System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-15s | %-16s |%n",
                            pm.getMaPhieuMuon(), pm.getMaDocGia(), pm.getMaNhanVien(), pm.getNgayLapPhieu(), pm.getNgayTraDuKien(), pm.getNgayTraThucTe());
                }
                System.out.printf("+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+%n");
        }
        public void timKiem(String keyword) {
                if (keyword == null) keyword = "";
                keyword = keyword.trim();
                boolean found = false;
                for (PhieuMuon pm : ds) {
                    if (pm.getMaPhieuMuon().contains(keyword) || pm.getMaDocGia().contains(keyword)) {
                        System.out.printf("%s, %s, %s, %s, %s, %s%n",
                                pm.getMaPhieuMuon(), pm.getMaDocGia(), pm.getMaNhanVien(), pm.getNgayLapPhieu(), pm.getNgayTraDuKien(),pm.getNgayTraThucTe());
                        found = true;
                    }
                }
                if (!found) System.out.println("Khong tim thay.");
        }
        public int getSoLuong(){
                return ds.length;
        }

}
