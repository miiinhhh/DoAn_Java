package com.example;

import java.io.*;

public class IOFile {

    public static void ghiNhaCungCap(NhaCungCap[] ds, int soLuong, String tenFile) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(tenFile))) {
            for (int i = 0; i < soLuong; i++) {
                NhaCungCap n = ds[i];
                pw.println(n.getMaNCC() + "," + n.getTenNCC() + "," + n.getDiaChi() + "," + n.getSoDienThoai());
            }
            System.out.println(" Ghi flie nha cung cap thanh cong: " + tenFile);
        } catch (IOException e) {
            System.out.println("Loi ghi file  nha cung cap: " + e.getMessage());
        }
    }

    public static int docNhaCungCap(NhaCungCap[] ds, String tenFile) {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",", -1);
                if (p.length >= 4) {
                    ds[count++] = new NhaCungCap(p[0], p[1], p[2], p[3]);
                }
            }
            System.out.println("Doc file nha cung cap thanh cong: " + tenFile + " (" + count + " dòng)");
        } catch (IOException e) {
            System.out.println("Loi doc file nha cung cap: " + e.getMessage());
        }
        return count;
    }

    public static void ghiPhieuNhap(PhieuNhap[] ds, int soLuong, String phieuFile, String chitietFile) {
        try (PrintWriter pwPhieu = new PrintWriter(new FileWriter(phieuFile));
             PrintWriter pwCT = new PrintWriter(new FileWriter(chitietFile))) {
            for (int i = 0; i < soLuong; i++) {
                PhieuNhap pn = ds[i];
                pwPhieu.println(pn.getMaPhieu() + "," + pn.getNgayNhap() + "," + (pn.getNhaCungCap()!=null? pn.getNhaCungCap().getMaNCC() : ""));
                for (int j = 0; j < pn.getSoChiTiet(); j++) {
                    ChiTietPhieuNhap ct = pn.getChiTiet()[j];
                    pwCT.println(pn.getMaPhieu() + "," + ct.getMaHang() + "," + ct.getTenHang() + "," + ct.getSoLuong() + "," + ct.getDonGia());
                }
            }
            System.out.println("Ghi file phieu nhap thanh cong.");
        } catch (IOException e) {
            System.out.println("Ghi phieu khong thanh cong: " + e.getMessage());
        }
    }

    public static int docPhieuNhap(PhieuNhap[] dsPhieu, int maxPhieu, QuanLyNhaCungCap qlNCC, String phieuFile, String chitietFile) {
        int soPhieu = 0;
        try (BufferedReader brPhieu = new BufferedReader(new FileReader(phieuFile))) {
            String line;
            while ((line = brPhieu.readLine()) != null && soPhieu < maxPhieu) {
                String[] p = line.split(",");
                if (p.length >= 3) {
                    String maPhieu = p[0];
                    String ngay = p[1];
                    String maNCC = p[2];

                    NhaCungCap ncc = qlNCC.layTheoMa(maNCC);
                    dsPhieu[soPhieu++] = new PhieuNhap(maPhieu, ngay, ncc);
                }
            }
        } catch (IOException e) {
            System.out.println("Loi doc file phieu: " + e.getMessage());
        }

        // đọc chi tiết và gán vào phiếu tương ứng
        try (BufferedReader brCT = new BufferedReader(new FileReader(chitietFile))) {
            String line;
            while ((line = brCT.readLine()) != null) {
                String[] p = line.split(",", -1);
                if (p.length >= 5) {
                    String maPhieu = p[0];
                    String maHang = p[1];
                    String tenHang = p[2];
                    int soLuong = Integer.parseInt(p[3]);
                    double donGia = Double.parseDouble(p[4]);
                    // tìm phiếu
                    PhieuNhap pn = null;
                    for (int i = 0; i < soPhieu; i++) {
                        if (dsPhieu[i].getMaPhieu().equalsIgnoreCase(maPhieu)) {
                            pn = dsPhieu[i];
                            break;
                        }
                    }
                    if (pn != null) {
                        pn.themChiTiet(new ChiTietPhieuNhap(maHang, tenHang, soLuong, donGia));
                    }
                }
            }
            System.out.println("Doc file phieu nhap chi tiet thanh cong.");
        } catch (IOException e) {
            System.out.println("Loi doc file chi tiet: " + e.getMessage());
        }

        return soPhieu;
    }
}