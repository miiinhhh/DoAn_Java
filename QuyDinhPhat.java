import java.util.Scanner;

public class QuyDinhPhat{
    private String maphat;
    private String noidung;
    private int tienphat;

    public QuyDinhPhat(){
        maphat="";
        noidung="";
        tienphat=0;
    }
    public QuyDinhPhat(String maphat, String noidung, int tienphat){
        this.maphat = maphat;
        this.noidung = noidung;
        this.tienphat=tienphat;
    }
    public QuyDinhPhat(QuyDinhPhat qd){
        this.maphat = qd.maphat;
        this.noidung = qd.noidung;
        this.tienphat = qd.tienphat;
    }
    public String getMaPhat(){
        return maphat;
    }
    public String getNoiDung(){
        return noidung;
    }
    public int getTienPhat(){
        return tienphat;
    }
    public void setMaPhat(String maphat){
        this.maphat =maphat;
    }
    public void setNoiDung(String noidung){
        this.noidung = noidung;
    }
    public void setTienPhat(int tienphat){
        this.tienphat = tienphat;
    }
    Scanner sc = new Scanner(System.in);
    public void nhap(){
        System.out.print("Nhap ma phat: ");
        maphat = sc.nextLine().trim();
        System.out.print("Nhap noi dung: ");
        noidung = sc.nextLine().trim();
        while(true){
            System.out.print("Nhap so tien phat: ");
            String s = sc.nextLine().trim();
            try{
                tienphat = Integer.parseInt(s);
                if(tienphat < 0) {System.out.print("So tien phat khong am. Vui long nhap lai !!"); continue; }
                break;
              } catch (NumberFormatException e){
                System.out.println("Vui long nhap so nguyen. Nhap lai: ");
              }
        }
    }
    @Override public String toString(){
        return maphat+","+noidung+","+tienphat;
    }
    public String toFile(){
        return String.join(",", maphat == null ? "" : maphat, noidung == null ? "" : noidung, String.valueOf(tienphat));
    }
    public void  xuat(){
        System.out.printf("| %-10s | %-50s | %-10d |%n",maphat,noidung,tienphat);
    }
}