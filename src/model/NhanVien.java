/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class NhanVien {

    private String maNv;
    private String matKhau;
    private String hoTen;
    private boolean vaiTro;

    @Override
    public String toString() {
        return "NhanVien{" + "maNv=" + maNv + ", matKhau=" + matKhau + ", hoTen=" + hoTen + ", vaiTro=" + vaiTro + '}';
    }

    public String getMaNv() {
        return maNv;
    }

    public void setMaNv(String maNv) {
        this.maNv = maNv;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public NhanVien(String maNv, String matKhau, String hoTen, boolean vaiTro) {
        this.maNv = maNv;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.vaiTro = vaiTro;
    }

    public boolean isVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(boolean vaiTro) {
        this.vaiTro = vaiTro;
    }



    public NhanVien() {
    }
}
