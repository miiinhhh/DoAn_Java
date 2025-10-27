package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
public class DanhSachSach {
    private ArrayList<Sach> dss;
    private DanhSachTacGia dstg;
    private DanhSachTheLoai dstl;
    private DanhSachNhaXuatBan dsnxb;
    public DanhSachSach(){
        dss = new ArrayList<>();
        dstg = new DanhSachTacGia();
        dstl = new DanhSachTheLoai();
        dsnxb = new DanhSachNhaXuatBan();
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
                
                if(parts.length >= 8){
                    int sl = Integer.parseInt(parts[6]);
                    // Hầu hết các trường đã được trim() nhờ regex split ở trên, nhưng ta vẫn dùng trim() để đảm bảo
                    Ngay day = Ngay.parseNgay(parts[7].trim());
                    String loaiSach = parts[0].trim();
                    Sach s = null;
                    
                    switch(loaiSach){
                        case "GiaoKhoa":
                            // KHI DÙNG line.split("\\s*,\\s*"), MON VÀ LOP ĐÃ LÀ parts[8] và parts[9]
                            s = new SachGiaoKhoa(
                                parts[1].trim(), parts[2].trim(), parts[3].trim(), 
                                parts[4].trim(), parts[5].trim(), sl, day,
                                parts[8].trim(), parts[9].trim() // SỬA Ở ĐÂY
                            );
                            break;
                            
                        case "ThamKhao":
                            s = new SachThamKhao(
                                parts[1].trim(), parts[2].trim(), parts[3].trim(), 
                                parts[4].trim(), parts[5].trim(), sl, day, 
                                parts[8].trim(), parts[9].trim() // SỬA Ở ĐÂY
                            );
                            break;
                            
                        default: // Sach Thuong 
                            s = new Sach(
                                parts[1].trim(), parts[2].trim(), parts[3].trim(), 
                                parts[4].trim(), parts[5].trim(), sl, day
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
        String ma;
        boolean ma_hop_le  = false;
        do {
            System.out.print("Nhap ma sach (GKxxx hoac TKxxx): ");
            ma = sc.nextLine().trim();
            if(c == 1){
                if (!ma.matches("GK\\d+")) {
                    System.out.println("Ma khong dung dinh dang. Vui long nhap lai!");
                    continue;
                }
            }
            if(c == 2){
                if (!ma.matches("TK\\d+")) {
                    System.out.println("Ma khong dung dinh dang. Vui long nhap lai!");
                    continue;
                }
            }
            ma_hop_le = true;
            for (Sach s : dss) {
                if (s.getMa_sach().equals(ma)) {
                    System.out.println("Ma da ton tai. Vui long nhap lai!");
                    ma_hop_le = false;
                    break;
                }
            }
        }while(!ma_hop_le);
        System.out.print("Nhap ten sach: ");
        String ten = sc.nextLine();
        String ma_tl;
        boolean ma_tl_hl = false;
        do{
            System.out.print("Nhap ma the loai (TLxx): ");
            ma_tl = sc.nextLine().trim();
            if (!ma_tl.matches("TL\\d+")) {
                System.out.println("Ma khong dung dinh dang. Vui long nhap lai!");
                continue;
            }
            ma_tl_hl = true;
        }while(!ma_tl_hl);
        String ma_tg;
        boolean ma_tg_hl = false;
        do{
            System.out.print("Nhap ma tac gia (TGxx): ");
            ma_tg = sc.nextLine().trim();
            if (!ma_tg.matches("TG\\d+")) {
                System.out.println("Ma khong dung dinh dang. Vui long nhap lai!");
                continue;
            }
            ma_tg_hl = true;
        }while(!ma_tg_hl);
        String ma_nxb;
        boolean ma_nxb_hl = false;
        do{
            System.out.print("Nhap ma nha xuat ban (NXBxx): ");
            ma_nxb = sc.nextLine().trim();
            if (!ma_nxb.matches("NXB\\d+")) {
                System.out.println("Ma khong dung dinh dang. Vui long nhap lai!");
                continue;
            }
            ma_nxb_hl = true;
        }while(!ma_nxb_hl);
        System.out.print("Nhap so luong: ");
        int sl = sc.nextInt();
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
            sach_moi = new SachGiaoKhoa(ma, ten, ma_tl, ma_tg, ma_nxb, sl, date, mon, lop);
            }else if(c==2){
                System.out.print("Nhap linh vuc: ");
                String lv = sc.nextLine();
                System.out.print("Nhap loai doc gia: ");
                String ldg = sc.nextLine();
                sach_moi = new SachThamKhao(ma, ten, ma_tl, ma_tg, ma_nxb, sl, date, lv,ldg);
            }else{
                System.out.println("Lua chon khong hop le.");
                return null;
            }
        return sach_moi;
    }
    Sach NhapThongTinSachKhiThemTacGia(String ma_tg){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap lua chon sach (1: Sach giao khoa, 2: Sach tham khoa)");
        int c = sc.nextInt();
        sc.nextLine();
        System.out.println("Nhap thong tin sach muon them: ");
        String ma;
        boolean ma_hop_le  = false;
        do {
            System.out.print("Nhap ma sach (GKxxx hoac TKxxx): ");
            ma = sc.nextLine().trim();
            if(c == 1){
                if (!ma.matches("GK\\d+")) {
                    System.out.println("Ma khong dung dinh dang. Vui long nhap lai!");
                    continue;
                }
            }
            if(c == 2){
                if (!ma.matches("TK\\d+")) {
                    System.out.println("Ma khong dung dinh dang. Vui long nhap lai!");
                    continue;
                }
            }
            ma_hop_le = true;
            for (Sach s : dss) {
                if (s.getMa_sach().equals(ma)) {
                    System.out.println("Ma da ton tai. Vui long nhap lai!");
                    ma_hop_le = false;
                    break;
                }
            }
        }while(!ma_hop_le);
        System.out.print("Nhap ten sach: ");
        String ten = sc.nextLine();
        String ma_tl;
        boolean ma_tl_hl = false;
        do{
            System.out.print("Nhap ma the loai (TLxx): ");
            ma_tl = sc.nextLine().trim();
            if (!ma_tl.matches("TL\\d+")) {
                System.out.println("Ma khong dung dinh dang. Vui long nhap lai!");
                continue;
            }
            ma_tl_hl = true;
            boolean tonTai_tl = false;
            for (TheLoai tl : dstl.getDSTheLoai()) {
                if (tl.getMa_the_loai().equals(ma_tl)) {
                    tonTai_tl = true;
                    break;
                }
            }
            if (!tonTai_tl) {
                System.out.println("Ma chua ton tai. Vui long nhap!");
                dstl.ThemTheLoaiCoMa(ma_tl);
            }
        }while(!ma_tl_hl);

        System.out.println("Nhap ma tac gia: "+ma_tg);

        String ma_nxb;
        boolean ma_nxb_hl = false;
        do{
            System.out.print("Nhap ma nha xuat ban (NXBxx): ");
            ma_nxb = sc.nextLine().trim();
            if (!ma_nxb.matches("NXB\\d+")) {
                System.out.println("Ma khong dung dinh dang. Vui long nhap lai!");
                continue;
            }
            ma_nxb_hl = true;
            boolean tonTai_nxb = false;
            for (NhaXuatBan nxb : dsnxb.getDSNhaXuatBan()) {
                if (nxb.getMa_nxb().equals(ma_nxb)) {
                    tonTai_nxb = true;
                    break;
                }
            }
            if (!tonTai_nxb) {
                System.out.println("Ma chua ton tai. Vui long nhap!");
                dsnxb.ThemNhaXuatBanCoMa(ma_nxb);
            }
        }while(!ma_nxb_hl);
        System.out.print("Nhap so luong: ");
        int sl = sc.nextInt();
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
            sach_moi = new SachGiaoKhoa(ma, ten, ma_tl, ma_tg, ma_nxb, sl, date, mon, lop);
            }else if(c==2){
                System.out.print("Nhap linh vuc: ");
                String lv = sc.nextLine();
                System.out.print("Nhap loai doc gia: ");
                String ldg = sc.nextLine();
                sach_moi = new SachThamKhao(ma, ten, ma_tl, ma_tg, ma_nxb, sl, date, lv,ldg);
            }else{
                System.out.println("Lua chon khong hop le.");
                return null;
            }
        return sach_moi;
    }

    Sach NhapThongTinSachKhiThemTheLoai(String ma_tl){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap lua chon sach (1: Sach giao khoa, 2: Sach tham khoa)");
        int c = sc.nextInt();
        sc.nextLine();
        System.out.println("Nhap thong tin sach muon them: ");
        String ma;
        boolean ma_hop_le  = false;
        do {
            System.out.print("Nhap ma sach (GKxxx hoac TKxxx): ");
            ma = sc.nextLine().trim();
            if(c == 1){
                if (!ma.matches("GK\\d+")) {
                    System.out.println("Ma khong dung dinh dang. Vui long nhap lai!");
                    continue;
                }
            }
            if(c == 2){
                if (!ma.matches("TK\\d+")) {
                    System.out.println("Ma khong dung dinh dang. Vui long nhap lai!");
                    continue;
                }
            }
            ma_hop_le = true;
            for (Sach s : dss) {
                if (s.getMa_sach().equals(ma)) {
                    System.out.println("Ma da ton tai. Vui long nhap lai!");
                    ma_hop_le = false;
                    break;
                }
            }
        }while(!ma_hop_le);
        System.out.print("Nhap ten sach: ");
        String ten = sc.nextLine();

        System.out.println("Nhap ma the loai: "+ma_tl);

        String ma_tg;
        boolean ma_tg_hl = false;
        do{
            System.out.print("Nhap ma tac gia (TGxx): ");
            ma_tg = sc.nextLine().trim();
            if (!ma_tg.matches("TG\\d+")) {
                System.out.println("Ma khong dung dinh dang. Vui long nhap lai!");
                continue;
            }
            ma_tg_hl = true;
            boolean tonTai_tg = false;
            for (TacGia tg : dstg.getDSTacGia()) {
                if (tg.getMa_tac_gia().equals(ma_tg)) {
                    tonTai_tg = true;
                    break;
                }
            }
            if (!tonTai_tg) {
                System.out.println("Ma chua ton tai. Vui long nhap!");
                dstg.ThemTacGiaCoMa(ma_tg);
            }
        }while(!ma_tg_hl);

        String ma_nxb;
        boolean ma_nxb_hl = false;
        do{
            System.out.print("Nhap ma nha xuat ban (NXBxx): ");
            ma_nxb = sc.nextLine().trim();
            if (!ma_nxb.matches("NXB\\d+")) {
                System.out.println("Ma khong dung dinh dang. Vui long nhap lai!");
                continue;
            }
            ma_nxb_hl = true;
            boolean tonTai_nxb = false;
            for (NhaXuatBan nxb : dsnxb.getDSNhaXuatBan()) {
                if (nxb.getMa_nxb().equals(ma_nxb)) {
                    tonTai_nxb = true;
                    break;
                }
            }
            if (!tonTai_nxb) {
                System.out.println("Ma chua ton tai. Vui long nhap!");
                dsnxb.ThemNhaXuatBanCoMa(ma_nxb);
            }
        }while(!ma_nxb_hl);
        System.out.print("Nhap so luong: ");
        int sl = sc.nextInt();
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
            sach_moi = new SachGiaoKhoa(ma, ten, ma_tl, ma_tg, ma_nxb, sl, date, mon, lop);
            }else if(c==2){
                System.out.print("Nhap linh vuc: ");
                String lv = sc.nextLine();
                System.out.print("Nhap loai doc gia: ");
                String ldg = sc.nextLine();
                sach_moi = new SachThamKhao(ma, ten, ma_tl, ma_tg, ma_nxb, sl, date, lv,ldg);
            }else{
                System.out.println("Lua chon khong hop le.");
                return null;
            }
        return sach_moi;
    }
    Sach NhapThongTinSachKhiThemNXB(String ma_nxb){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap lua chon sach (1: Sach giao khoa, 2: Sach tham khoa)");
        int c = sc.nextInt();
        sc.nextLine();
        System.out.println("Nhap thong tin sach muon them: ");
        String ma;
        boolean ma_hop_le  = false;
        do {
            System.out.print("Nhap ma sach (GKxxx hoac TKxxx): ");
            ma = sc.nextLine().trim();
            if(c == 1){
                if (!ma.matches("GK\\d+")) {
                    System.out.println("Ma khong dung dinh dang. Vui long nhap lai!");
                    continue;
                }
            }
            if(c == 2){
                if (!ma.matches("TK\\d+")) {
                    System.out.println("Ma khong dung dinh dang. Vui long nhap lai!");
                    continue;
                }
            }
            ma_hop_le = true;
            for (Sach s : dss) {
                if (s.getMa_sach().equals(ma)) {
                    System.out.println("Ma da ton tai. Vui long nhap lai!");
                    ma_hop_le = false;
                    break;
                }
            }
        }while(!ma_hop_le);
        System.out.print("Nhap ten sach: ");
        String ten = sc.nextLine();

        String ma_tl;
        boolean ma_tl_hl = false;
        do{
            System.out.print("Nhap ma the loai (TLxx): ");
            ma_tl = sc.nextLine().trim();
            if (!ma_tl.matches("TL\\d+")) {
                System.out.println("Ma khong dung dinh dang. Vui long nhap lai!");
                continue;
            }
            ma_tl_hl = true;
            boolean tonTai_tl = false;
            for (TheLoai tl : dstl.getDSTheLoai()) {
                if (tl.getMa_the_loai().equals(ma_tl)) {
                    tonTai_tl = true;
                    break;
                }
            }
            if (!tonTai_tl) {
                System.out.println("Ma chua ton tai. Vui long nhap!");
                dstl.ThemTheLoaiCoMa(ma_tl);
            }
        }while(!ma_tl_hl);

        String ma_tg;
        boolean ma_tg_hl = false;
        do{
            System.out.print("Nhap ma tac gia (TGxx): ");
            ma_tg = sc.nextLine().trim();
            if (!ma_tg.matches("TG\\d+")) {
                System.out.println("Ma khong dung dinh dang. Vui long nhap lai!");
                continue;
            }
            ma_tg_hl = true;
            boolean tonTai_tg = false;
            for (TacGia tg : dstg.getDSTacGia()) {
                if (tg.getMa_tac_gia().equals(ma_tg)) {
                    tonTai_tg = true;
                    break;
                }
            }
            if (!tonTai_tg) {
                System.out.println("Ma chua ton tai. Vui long nhap!");
                dstg.ThemTacGiaCoMa(ma_tg);
            }
        }while(!ma_tg_hl);

        System.out.println("Nhap ma nha xuat ban: "+ma_nxb);

        System.out.print("Nhap so luong: ");
        int sl = sc.nextInt();
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
            sach_moi = new SachGiaoKhoa(ma, ten, ma_tl, ma_tg, ma_nxb, sl, date, mon, lop);
            }else if(c==2){
                System.out.print("Nhap linh vuc: ");
                String lv = sc.nextLine();
                System.out.print("Nhap loai doc gia: ");
                String ldg = sc.nextLine();
                sach_moi = new SachThamKhao(ma, ten, ma_tl, ma_tg, ma_nxb, sl, date, lv,ldg);
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
        System.out.print("Nhap so luong: ");
        int sl = sc.nextInt();
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
        Ngay date = new Ngay(d, m, y);
        sc.nextLine();

        Sach sach_moi = null;
        if(c == 1){
            System.out.print("Nhap mon: ");
            String mon = sc.nextLine();
            System.out.print("Nhap lop: ");
            String lop = sc.nextLine();
            sach_moi = new SachGiaoKhoa(ma, ten, ma_tl, ma_tg, ma_nxb, sl, date, mon, lop);
        } else if(c == 2){
            System.out.print("Nhap linh vuc: ");
            String lv = sc.nextLine();
            System.out.print("Nhap loai doc gia: ");
            String ldg = sc.nextLine();
            sach_moi = new SachThamKhao(ma, ten, ma_tl, ma_tg, ma_nxb, sl, date, lv, ldg);
        } else {
            System.out.println("Lua chon khong hop le.");
            return null;
        }
        return sach_moi;
    }
    Sach NhapThongTinSachCoThamSoDeSua(String ma_sach_cu){ // truyền mã sách cũ khi sửa
        Scanner sc = new Scanner(System.in);
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
        System.out.print("Nhap so luong: ");
        int sl = sc.nextInt();
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
        Ngay date = new Ngay(d, m, y);
        sc.nextLine();

        Sach sach_moi = null;
        if(ma_sach_cu.startsWith("GK")){
            System.out.print("Nhap mon: ");
            String mon = sc.nextLine();
            System.out.print("Nhap lop: ");
            String lop = sc.nextLine();
            sach_moi = new SachGiaoKhoa(ma, ten, ma_tl, ma_tg, ma_nxb, sl, date, mon, lop);
        } else if(ma_sach_cu.startsWith("TK")){
            System.out.print("Nhap linh vuc: ");
            String lv = sc.nextLine();
            System.out.print("Nhap loai doc gia: ");
            String ldg = sc.nextLine();
            sach_moi = new SachThamKhao(ma, ten, ma_tl, ma_tg, ma_nxb, sl, date, lv, ldg);
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
                Sach sach_moi = NhapThongTinSachCoThamSoDeSua(ma);

                if(sach_moi == null){
                    System.out.println("Huy sua sach.");
                    return;
                }

                String ma_tg_cu = sach_cu.getMa_tac_gia();
                String ma_tg_moi = sach_moi.getMa_tac_gia();

                if(!ma_tg_cu.equals(ma_tg_moi)){
                    boolean tonTai = false;
                    for (TacGia tg : dstg.getDSTacGia()) {
                        if (tg.getMa_tac_gia().equals(ma_tg_moi)) {
                            tonTai = true;
                            break;
                        }
                    }

                    if (!tonTai) {
                        System.out.println("Tac gia moi chua ton tai. Vui long nhap thong tin tac gia moi:");
                        dstg.ThemTacGiaKhiSuaSachKhacMa(ma_tg_moi);
                    }
                }

                String ma_tl_cu = sach_cu.getMa_the_loai();
                String ma_tl_moi = sach_moi.getMa_the_loai();

                if(!ma_tl_cu.equals(ma_tl_moi)){
                    boolean tonTaiTL = false;
                    for (TheLoai tl : dstl.getDSTheLoai()) {
                        if (tl.getMa_the_loai().equals(ma_tl_moi)) {
                            tonTaiTL = true;
                            break;
                        }
                    }

                    if (!tonTaiTL) {
                        System.out.println("The loai moi chua ton tai. Vui long nhap thong tin the loai moi:");
                        dstl.ThemTheLoaiKhiSuaSachKhacMa(ma_tl_moi);
                    }

                }

                String ma_nxb_cu = sach_cu.getMa_nxb();
                String ma_nxb_moi = sach_moi.getMa_nxb();

                if(!ma_nxb_cu.equals(ma_nxb_moi)){
                    boolean tonTaiNXB = false;
                    for (NhaXuatBan nxb : dsnxb.getDSNhaXuatBan()) {
                        if (nxb.getMa_nxb().equals(ma_nxb_moi)) {
                            tonTaiNXB = true;
                            break;
                        }
                    }

                    if (!tonTaiNXB) {
                        System.out.println("Nha xuat ban moi chua ton tai. Vui long nhap thong tin nha xuat ban moi:");
                        dsnxb.ThemNhaXuatBanKhiSuaSachKhacMa(ma_nxb_moi);
                    }
                }
                
                dss.set(i,sach_moi); //thay the sach cu thanh sach moi
                GhiFileSach("src/main/java/com/example/Sach.txt");
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
                dstg.ThemTacGiaKhiSachKhacMa(ma_tg);
                //tim trong dstg, xem co ai co ma do chua
                tg_hien_co = dstg.TimTacGiaTheoMa(ma_tg);
            }
        }
        if(ma_tl!=null){
            //kt co sach moi thi the loai da co trong danh sach chua?
            TheLoai tl_hien_co = dstl.TimTheLoaiTheoMa(ma_tl);
            if(tl_hien_co==null){
                //neu the loai k ton tai->them moi
                System.out.println("The loai chua ton tai. Vui long nhap");
                dstl.ThemTheLoaiKhiSachKhacMa(ma_tl);
                //tim trong dstl, xem co sach co ma do chua
                tl_hien_co = dstl.TimTheLoaiTheoMa(ma_tl);
            }
        }

        if(ma_nxb!=null){
            //kt co sach moi thi nxb da co trong danh sach chua?
            NhaXuatBan nxb_hien_co = dsnxb.TimNhaXuatBanTheoMa(ma_nxb);
            if(nxb_hien_co==null){
                //neu nxb k ton tai->them moi
                System.out.println("Nha Xuat Ban chua ton tai. Vui long nhap");
                dsnxb.ThemNhaXuatBanKhiSachKhacMa(ma_nxb);
                //tim trong dsnxb, xem co sach co ma do chua
                nxb_hien_co = dsnxb.TimNhaXuatBanTheoMa(ma_nxb);
            }
        }
        dss.add(sach_moi);
        GhiFileSach("src/main/java/com/example/Sach.txt");
    }
    public void ThemSachTheoMa(Sach s) {
        dss.add(s);
        GhiFileSach("src/main/java/com/example/Sach.txt");
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
                dss.remove(i);
                found = true;
                System.out.println("Da xoa thanh cong sach co ma "+ma);
                break;
            }
        }
        if(!found){
            System.out.println("Khong tim thay ma "+ma);
        }else{
            GhiFileSach("src/main/java/com/example/Sach.txt");
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
            GhiFileSach("src/main/java/com/example/Sach.txt");
        }
    }

    //tao ra 1 danh sach chi rieng cua tac gia do thoi - tao cai nay vi can xoa tg,tl,nxb
    public ArrayList<Sach> getDSSachCuaTacGia(String ma_tg){
        ArrayList<Sach> result = new ArrayList<>();
        for(Sach s : dss){
            if(s.getMa_tac_gia().equals(ma_tg)){
                result.add(s);
            }
        }
        return result;
    }

    public ArrayList<Sach> getDSSachCuaTheLoai(String ma_tl){
        ArrayList<Sach> result = new ArrayList<>();
        for(Sach s : dss){
            if(s.getMa_the_loai().equals(ma_tl)){
                result.add(s);
            }
        }
        return result;
    }

    public ArrayList<Sach> getDSSachCuaNhaXuatBan(String ma_nxb){
        ArrayList<Sach> result = new ArrayList<>();
        for(Sach s : dss){
            if(s.getMa_nxb().equals(ma_nxb)){
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
    public int timkiemma(String ma) {
        if (ma == null || ma.trim().isEmpty()) return -1;
        String cleanMa = ma.trim();
        for (int i = 0; i < dss.size(); i++) {
            if (dss.get(i) != null && dss.get(i).getMa_sach().equals(cleanMa)) {
                return i;
              }
            }
            return -1;
    }
}

