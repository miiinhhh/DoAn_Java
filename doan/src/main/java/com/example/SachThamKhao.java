package com.example;

public class SachThamKhao extends Sach{
    private String linh_vuc;
    private String loai_doc_gia;
    public SachThamKhao(){
        
    }
    public SachThamKhao(String ma_sach,String ten_sach,String ma_the_loai,String ma_tac_gia,String ma_nxb,Ngay ngay_xuat_ban,String linh_vuc,String loai_doc_gia){
        super(ma_sach,ten_sach,ma_the_loai,ma_tac_gia,ma_nxb,ngay_xuat_ban);
        this.linh_vuc=linh_vuc;
        this.loai_doc_gia = loai_doc_gia;
    }
    public void setLinh_vuc(String linh_vuc){
        this.linh_vuc=linh_vuc;
    }
    public String getLinh_vuc(){
        return linh_vuc;
    }
    public String getLoai_doc_gia() {
        return loai_doc_gia;
    }

    public void setLoai_doc_gia(String loai_doc_gia) {
        this.loai_doc_gia = loai_doc_gia;
    }
    @Override
    public String toFileString() {
        return "ThamKhao, " + getMa_sach() + ", " + getTen_sach() + ", " + getMa_the_loai()
            + ", " + getMa_tac_gia() + ", " + getMa_nxb() + ", " + getNgay_xuat_ban()
            + ", " + linh_vuc + "," + loai_doc_gia;
    }
    @Override
    public String toString() { 
        return String.format(super.toString()+ " %s | %s |",
             this.linh_vuc, this.loai_doc_gia);
    }
}

