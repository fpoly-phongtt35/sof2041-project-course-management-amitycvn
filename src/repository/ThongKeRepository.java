/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import utils.JDBCHelper;

/**
 *
 * @author Admin
 */
public class ThongKeRepository {

    private List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {

        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    // tao view thong ke BangDiem
    private List<Object[]> getBangDiem(Integer maKH) {
        String sql = """
                     EXEC sp_BangDiem @MaKH = ?;
                     """;
        String[] cols = {"manh", "hoten", "diem"};
        return this.getListOfArray(sql, cols, maKH);
    }

    public List<Object[]> getThongKeNguoiHoc() {
        String sql = """
                     exec sp_thongkenguoihoc
                     """;
        String[] cols = {"Nam", "SoLuong", "DauTien", "CuoiCung"};
        return this.getListOfArray(sql, cols);
    }

    public List<Object[]> getThongKeDiem() {
        String sql = """
                     exec sp_thongkediem
                     """;
        String[] cols = {"ChuyenDe", "SoHv", "ThapNhat", "CaoNhat", "TrungBinh"};
        return this.getListOfArray(sql, cols);
    }
    
    public List<Object[]> getThongKeDoanhThu(String nam){
        String sql = """
                     exec thongkedoanhthu ?
                     """;
        String[] cols = {"ChuyenDe", "SoKH", "DoanhThu", "ThapNhat", "CaoNhat", "TrungBinh"};
        return this.getListOfArray(sql, cols, nam);
    }

    public static void main(String[] args) {
        ThongKeRepository thongKeRepository = new ThongKeRepository();

        List<Object[]> resultList = thongKeRepository.getBangDiem(3);
        for (Object[] row : resultList) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("-----------------------------------------");
        List<Object[]> resultList1 = thongKeRepository.getBangDiem(3);
        for (Object[] row : resultList1) {
            Object manh = row[0]; // Lấy giá trị cột MANH
            Object hoten = row[1]; // Lấy giá trị cột HOTEN
            Object diem = row[2]; // Lấy giá trị cột DIEM
            System.out.println("MANH: " + manh + ", HOTEN: " + hoten + ", DIEM: " + diem);
        }

        System.out.println("-----------------------------------------");
        List<Object[]> resultList2 = thongKeRepository.getThongKeNguoiHoc();
        for (Object[] row : resultList2) {
            Object nam = row[0]; // Lấy giá trị cột MANH
            Object soLuong = row[1]; // Lấy giá trị cột HOTEN
            Object dauTien = row[2]; // Lấy giá trị cột DIEM
            Object cuoiCung = row[3]; // Lấy giá trị cột DIEM
            System.out.println(Arrays.toString(row));
            System.out.println("Nam: " + nam + "SoLuong: " + soLuong + "DauTien: " + dauTien + "CuoiCung: " + cuoiCung);
        }
        System.out.println("-----------------------------------------");
        List<Object[]> resultList3 = thongKeRepository.getThongKeDiem();
        for (Object[] row : resultList3) {
            Object chuyenDe = row[0]; // Lấy giá trị cột MANH
            Object soHv = row[1]; // Lấy giá trị cột HOTEN
            Object thapNhat = row[2]; // Lấy giá trị cột DIEM
            Object caoNhat = row[3]; // Lấy giá trị cột DIEM
            Object trungBinh = row[3]; // Lấy giá trị cột DIEM
            System.out.println(Arrays.toString(row));
            System.out.println("Nam: " + chuyenDe + "SoLuong: " + soHv + "DauTien: " + thapNhat + "CuoiCung: " + caoNhat +""+ trungBinh);
        }
        for (Object[] row : thongKeRepository.getThongKeDoanhThu("2023")) {
            System.out.println(Arrays.toString(row));
        }

    }
}
