/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.KhoaHoc;
import utils.JDBCHelper;

/**
 *
 * @author Admin
 */
public class KhoaHocRepository extends EduSysRepository<KhoaHoc, Integer> {

    String insert = """
                    INSERT INTO [dbo].[KHOAHOC]
                               ([MACD]
                               ,[HOCPHI]
                               ,[THOILUONG]
                               ,[NGAYKG]
                               ,[GHICHU]
                               ,[MANV])
                         VALUES
                               (?,?,?,?,?,?)
                    """;
    String update = """
                    UPDATE [dbo].[KHOAHOC]
                       SET [MACD] = ?
                          ,[HOCPHI] = ?
                          ,[THOILUONG] = ?
                          ,[NGAYKG] = ?
                          ,[GHICHU] = ?
                          ,[MANV] = ?
                          ,[NGAYTAO] = ?
                     WHERE makh = ?
                    """;
    String delete = """
                    delete from khoahoc where makh = ?
                    """;
    String selectAll = """
                       select * from khoahoc
                       """;
    String selectById = """
                        select * from khoahoc where makh = ?
                        """;

    @Override
    public void insert(KhoaHoc entity) {
        JDBCHelper.executeUpdate(insert,
                entity.getMaCD(),
                entity.getHocPhi(),
                entity.getThoiLuong(),
                entity.getNgayKG(),
                entity.getGhiChu(),
                entity.getMaNv());
    }

    @Override
    public void update(KhoaHoc entity) {
        JDBCHelper.executeUpdate(update,
                entity.getMaCD(),
                entity.getHocPhi(),
                entity.getThoiLuong(),
                entity.getNgayKG(),
                entity.getGhiChu(),
                entity.getMaNv(),
                entity.getMaKH());
    }

    @Override
    public void delete(Integer id) {
        JDBCHelper.executeUpdate(delete, id);
    }

    @Override
    public KhoaHoc selectById(Integer id) {
        return this.selectBySQL(selectById, id).get(0);
    }

    @Override
    public List<KhoaHoc> selectAll() {
        return this.selectBySQL(selectAll);
    }

    @Override
    public List<KhoaHoc> selectBySQL(String sql, Object... args) {
        try {
            List<KhoaHoc> list = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                list.add(new KhoaHoc(rs.getInt("makh"), rs.getString("macd"), rs.getDouble("hocphi"), rs.getInt("thoiLuong"), rs.getDate("ngaykg"), rs.getString("ghichu"), rs.getString("manv")));
            }
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<KhoaHoc> selectByChuyenDe(String macd) {
        String SQL = "SELECT * FROM KhoaHoc WHERE MaCD = ?";
        return this.selectBySQL(SQL, macd);
    }

}
