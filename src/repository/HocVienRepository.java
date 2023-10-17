/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import model.HocVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.JDBCHelper;
/**
 *
 * @author Admin
 */
public class HocVienRepository extends EduSysRepository<HocVien, Integer>{

    String insert = """
                    INSERT INTO [dbo].[HOCVIEN]
                               ([MAKH]
                               ,[MANH]
                               ,[DIEM])
                         VALUES
                               (?,?,?)
                    """;
    String update = """
                    UPDATE [dbo].[HOCVIEN]
                       SET [MAKH] = ?
                          ,[MANH] = ?
                          ,[DIEM] = ?
                     WHERE mahv = ?
                    """;
    String delete = """
                    delete from hocvien where mahv = ?
                    """;
    String selectAll = """
                       select * from hocvien
                       """;
    String selectById = """
                        select*from hocvien where mahv = ?
                        """;
    
    @Override
    public void insert(HocVien entity) {
        JDBCHelper.executeUpdate(insert,
                entity.getMaNH(),
                entity.getMaKH(),
                entity.getDiem());
    }

    @Override
    public void update(HocVien entity) {
        JDBCHelper.executeUpdate(update,
                entity.getMaNH(),
                entity.getMaKH(),
                entity.getDiem(),
                entity.getMaHV());
    }

    @Override
    public void delete(Integer id) {
        JDBCHelper.executeUpdate(delete, id);
    }

    @Override
    public HocVien selectById(Integer id) {
        return this.selectBySQL(selectById, id).get(0);
    }

    @Override
    public List<HocVien> selectAll() {
        return this.selectBySQL(selectAll);
    }

    @Override
    public List<HocVien> selectBySQL(String sql, Object... args) {
        try {
            List<HocVien> list = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                list.add(new HocVien(rs.getInt("mahv"), rs.getInt("makh"), rs.getString("manh"), rs.getDouble("diem")));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static void main(String[] args) {
        HocVienRepository hocVienRepository =  new HocVienRepository();
        System.out.println(hocVienRepository.selectAll());
    }
    
}
