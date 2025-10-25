package com.example;

public class SachGiaoKhoa extends Sach {
    private String mon;
    private String lop;
    public SachGiaoKhoa(){
        
    }
    public SachGiaoKhoa(String ma_sach,String ten_sach,String ma_the_loai,String ma_tac_gia,String ma_nxb,Ngay ngay_xuat_ban,String mon,String lop){
        super(ma_sach,ten_sach,ma_the_loai,ma_tac_gia,ma_nxb,ngay_xuat_ban);
        this.mon = mon;
        this.lop = lop;
    }
    public String getMon() {
        return mon;
    }

    public void setMon(String mon) {
        this.mon = mon;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }
    @Override
    public String toFileString() {
        return "GiaoKhoa, " + getMa_sach() + ", " + getTen_sach() + ", " + getMa_the_loai()
            + ", " + getMa_tac_gia() + ", " + getMa_nxb() + ", " + getNgay_xuat_ban()
            + ", " + mon + "," + lop;
    }
    @Override
    public String toString() {
        return String.format(super.toString() + " %s | %s |", this.mon, this.lop);
    }
}

