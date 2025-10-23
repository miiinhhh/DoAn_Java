import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class PhieuMuon{
     private String maphieumuon;
     private String madocgia;
     private String manhanvien;
     private String ngaylapphieu;
     private String ngaytradukien;
     private String ngaytrathucte;

     public PhieuMuon(){
        maphieumuon="";
        madocgia="";
        manhanvien= "";
        ngaylapphieu="";
        ngaytradukien="";
        ngaytrathucte="";
     }
     public PhieuMuon(String maphieumuon, String madocgia, String manhanvien, String ngaylapphieu, String ngaytradukien,String ngaytrathucte){
        this.maphieumuon = maphieumuon;
        this.madocgia = madocgia;
        this.manhanvien = manhanvien;
        this.ngaylapphieu = ngaylapphieu;
        this.ngaytradukien = ngaytradukien;
        this.ngaytrathucte = ngaytrathucte;  
    }
    public PhieuMuon(PhieuMuon pm){
        this.maphieumuon= pm.maphieumuon;
        this.madocgia = pm.madocgia;
        this.manhanvien = pm.manhanvien;
        this.ngaylapphieu = pm.ngaylapphieu;
        this.ngaytradukien = pm.ngaytradukien;
        this.ngaytrathucte = pm.ngaytrathucte;
    }
    
    public String getMaPhieuMuon(){ return maphieumuon; }
    public String getMaDocGia(){ return madocgia; }
    public String getMaNhanVien(){ return manhanvien; }
    public String getNgayLapPhieu(){ return ngaylapphieu; }
    public String getNgayTraDuKien(){ return ngaytradukien; }
    public String getNgayTraThucTe(){ return ngaytrathucte; }

    public void setMaPhieuMuon(String maphieumuon){ this.maphieumuon = maphieumuon; }
    public void setMaDocGia(String madocgia){ this.madocgia = madocgia; }
    public void setMaNhanVien(String manhanvien){ this.manhanvien = manhanvien; }
    public void setNgayLapPhieu(String ngaylapphieu){ this.ngaylapphieu = ngaylapphieu; }
    public void setNgayTraDuKien(String ngaytradukien){ this.ngaytradukien = ngaytradukien; }
    public void setNgayTraThucTe(String ngaytrathucte){ this.ngaytrathucte = ngaytrathucte; }
    
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public String getTinhTrang() {
        if (ngaytrathucte == null || ngaytrathucte.trim().isEmpty()) {
        return "Chua tra";
    }
    if (ngaytradukien == null || ngaytradukien.trim().isEmpty()) {
        return "Khong co ngay du kien";
    }

    try {
        LocalDate duKien = LocalDate.parse(ngaytradukien.trim(), FMT);
        LocalDate thucTe = LocalDate.parse(ngaytrathucte.trim(), FMT);

        if (thucTe.isAfter(duKien)) {
            return "Tra tre han";
        } else {
            return "Tra dung han";
        }
    } catch (DateTimeParseException e) {
        return "Ngay khong hop le";
    }
}
    public void nhap(){
       Scanner sc = new Scanner(System.in);
       System.out.print("Nhap ma phieu muon: ");
       maphieumuon = sc.nextLine().trim();
       System.out.print("Nhap ma doc gia: ");
       madocgia = sc.nextLine().trim();
       System.out.print("Nhap ma nhan vien: ");
       manhanvien = sc.nextLine().trim();

       while(true){
          System.out.print("Nhap ngay lap phieu (dd/MM/yyyy): ");
          String s = sc.nextLine().trim();
          if (s.isEmpty()){
              System.out.println("Khong duoc de trong ngay lap phieu.");
              continue;
          }
          try{
            LocalDate.parse(s, FMT);
            ngaylapphieu = s;
            break;
          } catch(DateTimeParseException ex){
              System.out.println("Sai dinh dang ngay/gia tri ngay. Vui long nhap lai ngay (dd/MM/yyyy) !!");
          }
       }
       while(true){
          System.out.print("Nhap ngay tra du kien (dd/MM/yyyy): ");
          String s = sc.nextLine().trim();
          try{
            LocalDate.parse(s, FMT);
            ngaytradukien = s;
            break;
          } catch(DateTimeParseException ex){
              System.out.println("Sai dinh dang ngay/gia tri ngay. Vui long nhap lai ngay (dd/MM/yyyy) !!");
          }
       }

       while(true){
          System.out.print("Nhap ngay tra thuc te (dd/MM/yyyy) (de trong neu chua tra): ");
          String s = sc.nextLine().trim();
          if(s.isEmpty()){
            ngaytrathucte = "";
            break;
          }
          try{
            LocalDate.parse(s, FMT);
            ngaytrathucte = s;
            break;
          } catch(DateTimeParseException ex){
              System.out.println("Sai dinh dang ngay/gia tri ngay. Vui long nhap lai ngay (dd/MM/yyyy) !!");
          }
       }
    }
    public void xuat(){
        String hienthingaytra = ( ngaytrathucte == null || ngaytrathucte.trim().isEmpty()) ? "Chua tra" : ngaytrathucte;
        System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-15s | %-16s |%n", maphieumuon,madocgia,manhanvien,ngaylapphieu,ngaytradukien,hienthingaytra);
    }

    public String toFile(){
        String a = maphieumuon == null ? "" : maphieumuon.trim();
        String b = madocgia == null ? "" : madocgia.trim();
        String c = manhanvien == null ? "" : manhanvien.trim();
        String d = ngaylapphieu == null ? "" : ngaylapphieu.trim();
        String e = ngaytradukien == null ? "" : ngaytradukien.trim();
        String f = (ngaytrathucte == null || ngaytrathucte.trim().isEmpty()) ? "" : ngaytrathucte.trim();
        return String.join(",", a, b, c, d, e, f);
    }

}