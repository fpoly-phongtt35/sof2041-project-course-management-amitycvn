/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.ArrayList;
import java.util.List;
import model.NhanVien;
import utils.JDBCHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class NhanVienRepository extends EduSysRepository<NhanVien, String> {

    String insert = """
                    INSERT INTO [dbo].[NHANVIEN]
                               ([MANV]
                               ,[MATKHAU]
                               ,[HOTEN]
                               ,[VAITRO])
                         VALUES
                               (?,?,?,?)
                    """;

    String update = """
                    UPDATE [dbo].[NHANVIEN]
                       SET [MATKHAU] = ?
                          ,[HOTEN] = ?
                          ,[VAITRO] = ?
                     WHERE [MANV] = ?
                    """;

    String delete = """
                    DELETE FROM [dbo].[NHANVIEN]
                          WHERE Manv = ?
                    """;

    String selectAll = """
                       select * from NhanVien
                       """;

    String selectById = """
                        select * from NhanVien where MaNV = ?
                        """;

    @Override
    public void insert(NhanVien entity) {
        JDBCHelper.executeUpdate(insert,
                entity.getMaNv(),
                entity.getMatKhau(),
                entity.getHoTen(),
                entity.isVaiTro());
    }

    @Override
    public void update(NhanVien entity) {
        JDBCHelper.executeUpdate(update,
                entity.getMatKhau(),
                entity.getHoTen(),
                entity.isVaiTro(),
                entity.getMaNv());
    }

    @Override
    public void delete(String id) {
        JDBCHelper.executeUpdate(delete, id);
    }

    @Override
    public NhanVien selectById(String id) {
        List<NhanVien> list = this.selectBySQL(selectById, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NhanVien> selectAll() {
        List<NhanVien> list = this.selectBySQL(selectAll);
        return list;
    }

    @Override
    public List<NhanVien> selectBySQL(String sql, Object... args) {
        try {
            List<NhanVien> list = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                list.add(new NhanVien(rs.getString("Manv"), rs.getString("MatKhau"), rs.getString("hoten"), rs.getBoolean("VaiTro")));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
//    private List<NhanVien> select(String sql, Object... args){
//        List<NhanVien> list = new ArrayList<>();
//        try {
//            
//        } catch (Exception e) {
//        }
//    }

    public static void main(String[] args) {
        NhanVienRepository nhanVienRepository = new NhanVienRepository();
        System.out.println(nhanVienRepository.selectAll());
        System.out.println(nhanVienRepository.selectById("NV008"));
        System.out.println("---------------------------------------");
//        nhanVienRepository.insert(new NhanVien("NV008", "a1234b5c", "Chu Minh Thuong", true));
//        nhanVienRepository.update(new NhanVien("NV008", "a1234b5c", "Amity", true));
//        nhanVienRepository.delete("NV008");

        System.out.println(nhanVienRepository.selectAll());
    }

}
