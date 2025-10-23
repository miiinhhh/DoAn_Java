import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class DanhSachQuyDinhPhat{
    private QuyDinhPhat[] ds = new QuyDinhPhat[0];
    public DanhSachQuyDinhPhat(){}
    public DanhSachQuyDinhPhat(QuyDinhPhat[] ds){ this.ds = ds;}
    public DanhSachQuyDinhPhat(DanhSachQuyDinhPhat ds){ this.ds = ds.ds;}
    Scanner sc = new Scanner(System.in);
    public void nhap(){
        System.out.println("Nhap so luong quy dinh can nhap: ");
        int bd = ds.length;
        int sl;
        while(true){
            String s = sc.nextLine().trim();
            try{
                sl = Integer.parseInt(s);
                if(sl < 0) {
                    System.out.println("So luong phai >=0. Vui long nhap lai: "); continue;
                }
                break;
            }catch(NumberFormatException e){
                System.out.println("Vui long nhap so nguyen. Nhap lai: ");
            }
        }
        ds = Arrays.copyOf(ds,ds.length + sl);
        for(int i = bd; i < ds.length; i++){
            ds[i] = new QuyDinhPhat();
            ds[i].nhap();
        }
    }
    public void xuat(){
        System.out.printf("+------------+----------------------------------------------------+------------+%n");
        System.out.printf("| %-10s | %-50s | %-10s |%n","Ma phat","Noi dung","Tien phat");
        System.out.printf("|------------|----------------------------------------------------|------------|%n");
        for(QuyDinhPhat qd:ds)
            qd.xuat();
        System.out.printf("+------------+----------------------------------------------------+------------+%n");    
    }
    public void them(QuyDinhPhat qd){
        if( qd == null ) return;
        if(timkiemma(qd.getMaPhat()) != -1){
            System.out.println("Ma phieu phat da ton tai !!");
            return;
        }
        ds= Arrays.copyOf(ds,ds.length +1);
        ds[ds.length-1] = new QuyDinhPhat(qd);
        System.out.println("Them thanh cong");
    }
    public void them(){
        ds = Arrays.copyOf(ds,ds.length+1);
        ds[ds.length-1] = new QuyDinhPhat();
        System.out.println("Nhap thong tin quy dinh phat can them: ");
        ds[ds.length-1].nhap();
        System.out.println("Them thanh cong");
    }
    public int timkiemma(String ma){
        if(ma == null) return -1;
        ma = ma.trim();
        for(int i =0; i < ds.length; i++){
            if(ma.equals(ds[i].getMaPhat())) return i;
        }
        return -1;
    }
    private void inmenusua(){
        System.out.println("\nBan muon sua thong tin gi ?");
        System.out.println("1. Sua noi dung");
        System.out.println("2. Sua so tien phat");
        System.out.println("0. Quay lai");
        System.out.print("Lua chon cua ban : ");
    }
    public void sua(){
        if(ds.length == 0){
            System.out.println("Danh sach quy dinh phat dang rong !!");
            return;
        }
        while(true){
            System.out.print("Nhap ma phat (Nhan Enter de thoat): ");
            String ma = sc.nextLine().trim();
            if(ma.isEmpty()) return;
            int idx = timkiemma(ma);
            if(idx == -1){
                System.out.println("Khong tim thay phieu phat: "+ma);
                continue;
            }
            QuyDinhPhat qd = ds[idx];
            while(true){
                inmenusua();
                String choice = sc.nextLine().trim();
                switch(choice ){
                    case "1":{
                        System.out.print("Nhap noi dung moi: ");
                        String newNd = sc.nextLine().trim();
                        qd.setNoiDung(newNd);
                            System.out.println("Cap nhat thanh cong");
                            break;
                    }
                    case "2":{
                        while(true){
                            System.out.print("Nhap so tien phat moi: ");
                            String s = sc.nextLine().trim();
                            try{
                                int tp=Integer.parseInt(s);
                                if(tp < 0){ System.out.println("So tien phat phai >= 0. Vui long nhap lai !! "); continue;}
                                qd.setTienPhat(tp);
                                System.out.println("Cap nhat thanh cong");
                                break;
                            }catch(NumberFormatException e){
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
                }if("0".equals(choice)) break;

            }
        }
    }
   public void xoa(){
            System.out.println("Nhap ma phat cua quy dinh phat muon xoa: ");
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
        File file = new File("Quydinhphat.txt");
        if(!file.exists()){
            System.out.println("File không tồn tại !!");
            return;
        }
        ds = new QuyDinhPhat[0];
        try{
            Scanner f = new Scanner(file,"UTF-8");
            while(f.hasNextLine()){
                String line = f.nextLine().trim();
                if(line.isEmpty()) continue;
                String[] parts = line.split(",",-1);
                if(parts.length >= 3){
                    String mp = parts[0].trim();
                    String nd = parts[1].trim();
                    String stp = parts[2].trim();
                    try{
                        int tp = stp.isEmpty() ? 0 : Integer.parseInt(stp);
                        ds = Arrays.copyOf(ds,ds.length+1);
                        ds[ds.length-1] = new QuyDinhPhat(mp,nd,tp);
                    } catch(NumberFormatException e){
                        System.out.println("Dong loi (so tien): "+ line);
                    }
                }
            }
            System.out.println("Doc file thanh cong");
        }catch(Exception e){
            System.out.println("Loi doc file: " + e.getMessage());
        }    
    }
    public void ghiFile(){
        try(PrintWriter w = new PrintWriter("Quydinhphat.txt","UTF-8")){
            for(QuyDinhPhat qdp : ds){
                w.println(qdp.toFile());
            }
            System.out.println("Ghi file thanh cong.");
        }catch(Exception e){
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }
    public QuyDinhPhat layQuyDinhPhatTuMa(String ma){
        for(int i =0; i < ds.length; i++){
            if(ma.equals(ds[i].getMaPhat())){
                return ds[i];
            }
        }
        return null;
    }
    public void timKiem(String keyword) {
        if (keyword == null) keyword = "";
        boolean found = false;
        for (QuyDinhPhat q : ds) {
            if (q == null) continue;
            String ma = q.getMaPhat();
            String nd = q.getNoiDung();
            if ((ma != null && ma.contains(keyword)) || (nd != null && nd.contains(keyword))) {
                System.out.printf("%s, %s, %d%n", ma, nd, q.getTienPhat());
                found = true;
            }
        }
        if (!found) System.out.println("Khong tim thay.");
    }

    public void hienThiTatCa() {
        if (ds == null || ds.length == 0) {
            System.out.println("Danh sach quy dinh phat trong!");
            return;
        }
        System.out.printf("+------------+----------------------------------------------------+------------+%n");
        System.out.printf("| %-10s | %-50s | %-10s |%n","Ma phat","Noi dung","Tien phat");
        System.out.printf("+------------+----------------------------------------------------+------------+%n");
        for (QuyDinhPhat q : ds) {
            if (q == null) continue;
            System.out.printf("| %-10s | %-50s | %10d |%n",
                    q.getMaPhat(), q.getNoiDung(), q.getTienPhat());
        }
        System.out.printf("+------------+----------------------------------------------------+------------+%n");
    }

    public int getSoLuong(){
        return ds.length;
    }
}