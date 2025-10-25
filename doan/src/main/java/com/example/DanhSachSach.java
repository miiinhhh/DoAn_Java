package com.example;

import java.util.*;
import java.io.*;
public class DanhSachSach {
    private ArrayList<Sach> dss = new ArrayList<>();
    private DanhSachTacGia dstg;
    private DanhSachTheLoai dstl;
    private DanhSachNhaXuatBan dsnxb;
    public DanhSachSach(){
        
    }
    public DanhSachSach(ArrayList<Sach> dss, DanhSachTacGia dstg, DanhSachTheLoai dstl, DanhSachNhaXuatBan dsnxb){
        this.dss = dss;
        this.dstg = dstg;
        this.dstl = dstl;
        this.dsnxb = dsnxb;
    }
    public void DocFileSach(String ten_file){
        try{
            File f = new File(ten_file);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            
            while(line!=null){
                // Sử dụng regex để tách bằng dấu phẩy và loại bỏ bất kỳ khoảng trắng nào xung quanh
                // Regex: \s*,\s* nghĩa là (0 hoặc nhiều khoảng trắng) + (dấu phẩy) + (0 hoặc nhiều khoảng trắng)
                String[] parts = line.split("\\s*,\\s*"); // SỬA LẠI DÒNG NÀY
                
                if(parts.length >= 7){
                    
                    // Hầu hết các trường đã được trim() nhờ regex split ở trên, nhưng ta vẫn dùng trim() để đảm bảo
                    Ngay day = Ngay.parseNgay(parts[6].trim());
                    String loaiSach = parts[0].trim();
                    Sach s = null;
                    
                    switch(loaiSach){
                        case "GiaoKhoa":
                            // KHI DÙNG line.split("\\s*,\\s*"), MON VÀ LOP ĐÃ LÀ parts[7] và parts[8]
                            s = new SachGiaoKhoa(
                                parts[1].trim(), parts[2].trim(), parts[3].trim(), 
                                parts[4].trim(), parts[5].trim(), day,
                                parts[7].trim(), parts[8].trim() // SỬA Ở ĐÂY
                            );
                            break;
                            
                        case "ThamKhao":
                            s = new SachThamKhao(
                                parts[1].trim(), parts[2].trim(), parts[3].trim(), 
                                parts[4].trim(), parts[5].trim(), day, 
                                parts[7].trim(), parts[8].trim() // SỬA Ở ĐÂY
                            );
                            break;
                            
                        default: // Sach Thuong (chỉ có 8 fields)
                            s = new Sach(
                                parts[1].trim(), parts[2].trim(), parts[3].trim(), 
                                parts[4].trim(), parts[5].trim(), day
                            );
                    }
                    
                    dss.add(s);
                }
                line = br.readLine();
            }
            br.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void GhiFileSach(String ten_file){
        try {
            File f = new File(ten_file);
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            for(Sach s : dss){
                bw.write(s.toFileString());
                bw.newLine();
            }
            bw.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void XemSach() {
        for (Sach s : dss) {
            System.out.println(s);
        }
    }
    public static boolean namNhuan(int y) {
        return (y % 400 == 0) || (y % 4 == 0 && y % 100 != 0);
    }
    public static boolean kiemTraHopLe(int d, int m, int y) {
        if (y <= 0 || m < 1 || m > 12 || d < 1) return false;

        int maxDay;
        switch (m) {
            case 4: case 6: case 9: case 11:
                maxDay = 30;
                break;
            case 2:
                maxDay = namNhuan(y) ? 29 : 28;
                break;
            default:
                maxDay = 31;
        }

        return d <= maxDay;
    }
    Sach NhapThongTinSach(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap lua chon sach (1: Sach giao khoa, 2: Sach tham khoa)");
        int c = sc.nextInt();
        sc.nextLine();
        System.out.println("Nhap thong tin sach muon them: ");
        System.out.print("Nhap ma sach: ");
        String ma = sc.nextLine();
        System.out.print("Nhap ten sach: ");
        String ten = sc.nextLine();
        System.out.print("Nhap ma the loai: ");
        String ma_tl = sc.nextLine();
        System.out.print("Nhap ma tac gia: ");
        String ma_tg = sc.nextLine();
        System.out.print("Nhap ma nxb: ");
        String ma_nxb = sc.nextLine();
        System.out.print("Nhap ngay xuat ban (ngay thang nam): ");
        int d,m,y;
        boolean hopLe;
        do {
            d = sc.nextInt();
            m = sc.nextInt();
            y = sc.nextInt();
            hopLe = kiemTraHopLe(d, m, y);
            if (!hopLe) {
                System.out.println("Ngay thang nam khong hop le, vui long nhap lai!\n");
            }
        } while (!hopLe);
        Ngay date = new Ngay(d,m,y);
        sc.nextLine();
        Sach sach_moi =null;
        if(c == 1){
            System.out.print("Nhap mon: ");
            String mon = sc.nextLine();
            System.out.print("Nhap lop: ");
            String lop = sc.nextLine();
            sach_moi = new SachGiaoKhoa(ma, ten, ma_tl, ma_tg, ma_nxb, date, mon, lop);
            }else if(c==2){
                System.out.print("Nhap linh vuc: ");
                String lv = sc.nextLine();
                System.out.print("Nhap loai doc gia: ");
                String ldg = sc.nextLine();
                sach_moi = new SachThamKhao(ma, ten, ma_tl, ma_tg, ma_nxb, date, lv,ldg);
            }else{
                System.out.println("Lua chon khong hop le.");
                return null;
            }
        return sach_moi;
    }
    Sach NhapThongTinSachCoThamSo(String ma_sach_cu){ // truyền mã sách cũ khi sửa
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap lua chon sach (1: Sach giao khoa, 2: Sach tham khoa)");
        int c = sc.nextInt();
        sc.nextLine();

        System.out.println("Nhap thong tin sach:");
        String ma = ma_sach_cu != null ? ma_sach_cu : ""; // giữ mã sách cũ nếu sửa
        if(ma_sach_cu != null){
            System.out.println("Ma sach: " + ma); // hiển thị cho người dùng
        } else {
            System.out.print("Nhap ma sach: ");
            ma = sc.nextLine();
        }

        System.out.print("Nhap ten sach: ");
        String ten = sc.nextLine();
        System.out.print("Nhap ma the loai: ");
        String ma_tl = sc.nextLine();
        System.out.print("Nhap ma tac gia: ");
        String ma_tg = sc.nextLine();
        System.out.print("Nhap ma nxb: ");
        String ma_nxb = sc.nextLine();

        System.out.print("Nhap ngay xuat ban (ngay thang nam): ");
        int d = sc.nextInt(), m = sc.nextInt(), y = sc.nextInt();
        Ngay date = new Ngay(d, m, y);
        sc.nextLine();

        Sach sach_moi = null;
        if(c == 1){
            System.out.print("Nhap mon: ");
            String mon = sc.nextLine();
            System.out.print("Nhap lop: ");
            String lop = sc.nextLine();
            sach_moi = new SachGiaoKhoa(ma, ten, ma_tl, ma_tg, ma_nxb, date, mon, lop);
        } else if(c == 2){
            System.out.print("Nhap linh vuc: ");
            String lv = sc.nextLine();
            System.out.print("Nhap loai doc gia: ");
            String ldg = sc.nextLine();
            sach_moi = new SachThamKhao(ma, ten, ma_tl, ma_tg, ma_nxb, date, lv, ldg);
        } else {
            System.out.println("Lua chon khong hop le.");
            return null;
        }
        return sach_moi;
    }

    public void SuaSach(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ma sach muon sua: ");
        String ma = sc.nextLine();

        boolean found = false;
        for(int i = 0;i<dss.size();i++){
            if(dss.get(i).getMa_sach().equals(ma)){
                Sach sach_cu = dss.get(i);
                System.out.println("Nhap thong tin moi cho sach:");
                Sach sach_moi = NhapThongTinSachCoThamSo(ma);

                if(sach_moi == null){
                    System.out.println("Huy sua sach.");
                    return;
                }

                String ma_tg_cu = sach_cu.getMa_tac_gia();
                String ma_tg_moi = sach_moi.getMa_tac_gia();

                if(!ma_tg_cu.equals(ma_tg_moi)){
                    TacGia tg_cu = dstg.TimTacGiaTheoMa(ma_tg_cu);
                    if(tg_cu!=null) tg_cu.xoaSachTheoMa(ma);

                    TacGia tg_moi = dstg.TimTacGiaTheoMa(ma_tg_moi);
                    if(tg_moi==null){
                        System.out.println("Tac gia moi chua ton tai. Vui long nhap thong tin tac gia moi:");
                        dstg.ThemTacGia();
                        tg_moi = dstg.TimTacGiaTheoMa(ma_tg_moi);
                    }
                    tg_moi.themSach(sach_moi);
                }
                dss.set(i,sach_moi); //thay the sach cu thanh sach moi
                GhiFileSach("Sach.txt");
                System.out.println("Da sua sach co ma "+ma);
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("Khong tim thay sach co ma "+ma);
        }
    }
    public void ThemSach(){
        Sach sach_moi = NhapThongTinSach();
        //xu ly tac gia, the loai, nxb
        String ma_tg = sach_moi.getMa_tac_gia();
        String ma_tl = sach_moi.getMa_the_loai();
        String ma_nxb = sach_moi.getMa_nxb();
        //sach moi co tac gia k?
        if(ma_tg!=null){
            //kt co sach moi thi tac gia da co trong danh sach chua?
            TacGia tg_hien_co = dstg.TimTacGiaTheoMa(ma_tg);
            if(tg_hien_co==null){
                //neu tac gia k ton tai->them moi
                System.out.println("Tac gia chua ton tai. Vui long nhap");
                dstg.ThemTacGiaKhiSachKhacMa();
                //tim trong dstg, xem co ai co ma do chua
                tg_hien_co = dstg.TimTacGiaTheoMa(ma_tg);
            }
            if(tg_hien_co!=null){
                tg_hien_co.themSach(sach_moi);
            }
        }
        if(ma_tl!=null){
            //kt co sach moi thi the loai da co trong danh sach chua?
            TheLoai tl_hien_co = dstl.TimTheLoaiTheoMa(ma_tl);
            if(tl_hien_co==null){
                //neu the loai k ton tai->them moi
                System.out.println("The loai chua ton tai. Vui long nhap");
                dstl.ThemTheLoaiKhiSachCoMa();
                //tim trong dstl, xem co sach co ma do chua
                tl_hien_co = dstl.TimTheLoaiTheoMa(ma_tl);
            }
            if(tl_hien_co!=null){
                tl_hien_co.themSach(sach_moi);
            }
        }

        if(ma_nxb!=null){
            //kt co sach moi thi nxb da co trong danh sach chua?
            NhaXuatBan nxb_hien_co = dsnxb.TimNhaXuatBanTheoMa(ma_nxb);
            if(nxb_hien_co==null){
                //neu nxb k ton tai->them moi
                System.out.println("Nha Xuat Ban chua ton tai. Vui long nhap");
                dsnxb.ThemNhaXuatBanKhiSachKhacMa();
                //tim trong dsnxb, xem co sach co ma do chua
                nxb_hien_co = dsnxb.TimNhaXuatBanTheoMa(ma_nxb);
            }
            if(nxb_hien_co!=null){
                nxb_hien_co.themSach(sach_moi);
            }
        }
        dss.add(sach_moi);
        GhiFileSach("Sach.txt");
    }
    public void ThemSachTheoMa(Sach s) {
        dss.add(s);
        GhiFileSach("Sach.txt");
    }

    //xoa theo ma nhap tu ban phim
    public void XoaSach(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ma sach muon xoa: ");
        String ma = sc.nextLine();
        boolean found = false;
        for(int i = 0;i < dss.size();i++){   //ta dung vong lap chi so vi neu lam for-each ma remove(s) thi khong an toan
            if(dss.get(i).getMa_sach().equals(ma)){  
                Sach s = dss.get(i); //danh dau sach bi xoa
                String ma_tg = s.getMa_tac_gia();
                dss.remove(i);
                found = true;
                System.out.println("Da xoa thanh cong sach co ma "+ma);
                // Kiểm tra tác giả còn sách hay không
                int soSachConLai = 0;
                for(Sach x : dss){
                    if(x.getMa_tac_gia().equals(ma_tg)){
                        soSachConLai++;
                    }
                }
                if(soSachConLai == 0){
                    // System.out.println("Tac gia co ma " + ma_tg + " khong con sach nao.");
                    dstg.XoaTacGiaTheoMa(ma_tg);
                }
                break;
            }
        }
        if(!found){
            System.out.println("Khong tim thay ma "+ma);
        }else{
            GhiFileSach("Sach.txt");
        }
    }
    //xoa theo ma sach tu dong
    public void XoaSachTheoMa(String ma){
        boolean found = false;
        for(int i = 0; i < dss.size(); i++){
            if(dss.get(i).getMa_sach().equals(ma)){
                dss.remove(i);
                found = true;
                break;
            }
        }
        if(found){
            GhiFileSach("Sach.txt");
        }
    }

    //lay tat ca sach cua 1 tac gia
    public ArrayList<Sach> getDSSachCuaTacGia(String ma_tg){
        ArrayList<Sach> result = new ArrayList<>();
        for(Sach s : dss){
            if(s.getMa_tac_gia().equals(ma_tg)){
                result.add(s);
            }
        }
        return result;
    }

    public void TimSach(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ma sach muon tim: ");
        String ma = sc.nextLine();
        boolean found = false;
        for(Sach s : dss){
            if(s.getMa_sach().equals(ma)){
                System.out.println("Da tim thay sach co ma "+ma);
                System.out.println(s);
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("Khong tim thay sach co ma "+ma);
        }
    }
}

