/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.ArrayList;
import java.util.List;
import model.NguoiHoc;
import utils.JDBCHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class NguoiHocRepository extends EduSysRepository<NguoiHoc, String>{
    String insert = """
                    INSERT INTO [dbo].[NGUOIHOC]
                               ([MANH]
                               ,[HOTEN]
                               ,[GIOITINH]
                               ,[NGAYSINH]
                               ,[DIENTHOAI]
                               ,[EMAIL]
                               ,[GHICHU]
                               ,[MANV]
                               )
                         VALUES
                               (?,?,?,?,?,?,?,?)
                    """;
    String update = """
                    UPDATE [dbo].[NGUOIHOC]
                       SET [HOTEN] = ?
                          ,[GIOITINH] = ?
                          ,[NGAYSINH] = ?
                          ,[DIENTHOAI] = ?
                          ,[EMAIL] = ?
                          ,[GHICHU] = ?
                          ,[MANV] = ?
                     WHERE manh = ?
                    """;
    String delete = """
                    delete from nguoihoc where manh = ?
                    """;
    String selectAll = """
                       select * from nguoihoc
                       """;
    String selectById = """
                        select * from nguoihoc where manh = ?
                        """;

    @Override
    public void insert(NguoiHoc entity) {
        JDBCHelper.executeUpdate(insert, 
                entity.getMaNH(),
                entity.getHoTen(),
                entity.isGioiTinh(),
                entity.getNgaySinh(),
                entity.getDienThoai(),
                entity.getEmail(),
                entity.getGhiChu(),
                entity.getMaNV()
                );
    }

    @Override
    public void update(NguoiHoc entity) {
        JDBCHelper.executeUpdate(update, 
                entity.getHoTen(),
                entity.isGioiTinh(),
                entity.getNgaySinh(),
                entity.getDienThoai(),
                entity.getEmail(),
                entity.getGhiChu(),
                entity.getMaNV(),
                entity.getMaNH()
                );
    }

    @Override
    public void delete(String id) {
        JDBCHelper.executeUpdate(delete, id);
    }

    @Override
    public NguoiHoc selectById(String id) {
        List<NguoiHoc> list = this.selectBySQL(selectById, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NguoiHoc> selectAll() {
        return this.selectBySQL(selectAll);
    }

    @Override
    public List<NguoiHoc> selectBySQL(String sql, Object... args) {
        try {
            List<NguoiHoc> list = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                list.add(new NguoiHoc(rs.getString("manh"),
                        rs.getString("hoten"), rs.getDate("Ngaysinh"), rs.getBoolean("gioitinh"), rs.getString("dienthoai"), rs.getString("email"), rs.getString("ghichu"), rs.getString("manv")));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public List<NguoiHoc> selectByKeyword(String keyword){
        String sql = """
                     select * from nguoihoc where hoten like ?
                     """;
        return this.selectBySQL(sql, "%" + keyword + "%");
    }
    
    public List<NguoiHoc> selectNotInCourse(int makh, String keyword){
        String sql = """
                     select * from nguoihoc where hoten like ?
                     and manh not in (select manh from hocvien where makh = ?)
                     """;
        return this.selectBySQL(sql, "%" + keyword + "%", makh);
    }
    
    public static void main(String[] args) {
        NguoiHocRepository nguoiHocRepository = new NguoiHocRepository();
        System.out.println(nguoiHocRepository.selectByKeyword("bob"));
        System.out.println(nguoiHocRepository.selectNotInCourse(3, "bob"));
    }
    
}
