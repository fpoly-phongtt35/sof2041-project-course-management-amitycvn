/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.ArrayList;
import java.util.List;
import model.ChuyenDe;
import utils.JDBCHelper;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Admin
 */
public class ChuyenDeRepository extends EduSysRepository<ChuyenDe, String> {

    String insert = """
                    INSERT INTO [dbo].[CHUYENDE]
                               ([MACD]
                               ,[TENCD]
                               ,[HOCPHI]
                               ,[THOILUONG]
                               ,[HINH]
                               ,[MOTA])
                         VALUES
                               (?,?,?,?,?,?)
                    """;

    String update = """
                    UPDATE [dbo].[CHUYENDE]
                       SET [TENCD] = ?
                          ,[HOCPHI] = ?
                          ,[THOILUONG] = ?
                          ,[HINH] = ?
                          ,[MOTA] = ?
                     WHERE MaCd = ?
                    """;

    String delete = """
                    DELETE FROM [dbo].[CHUYENDE]
                          WHERE Macd = ?
                    """;

    String selectAll = """
                       select * from chuyende
                       """;

    String selectById = """
                       select * from chuyende where macd = ?
                       """;

    @Override
    public void insert(ChuyenDe entity) {
        JDBCHelper.executeUpdate(insert,
                 entity.getMaCD(),
                 entity.getTenCD(),
                 entity.getHocPhi(),
                 entity.getThoiLuong(),
                 entity.getHinh(),
                entity.getMoTa());
    }

    @Override
    public void update(ChuyenDe entity) {
        JDBCHelper.executeUpdate(update, 
                entity.getTenCD(),
                entity.getHocPhi(),
                entity.getThoiLuong(),
                entity.getHinh(),
                entity.getMoTa(),
                entity.getMaCD());
    }

    @Override
    public void delete(String id) {
        JDBCHelper.executeUpdate(delete, id);
    }

    @Override
    public ChuyenDe selectById(String id) {
        List<ChuyenDe> list = this.selectBySQL(selectById, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ChuyenDe> selectAll() {
        return this.selectBySQL(selectAll);
    }

    @Override
    public List<ChuyenDe> selectBySQL(String sql, Object... args) {
        try {
            List<ChuyenDe> list = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                list.add(new ChuyenDe(rs.getString("MaCD"),
                        rs.getString("tencd"),
                        rs.getDouble("hocPhi"),
                        rs.getInt("thoiluong"),
                        rs.getString("hinh"),
                        rs.getString("mota")));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static void main(String[] args) {
        ChuyenDeRepository chuyenDeRepository = new ChuyenDeRepository();
        System.out.println(chuyenDeRepository.selectAll());
        System.out.println(chuyenDeRepository.selectById("CD008"));
        System.out.println("----------------------------------");
//        chuyenDeRepository.insert(new ChuyenDe("CD008", "Java", 30000, 60, "java.png", "Java 3"));
//        chuyenDeRepository.update(new ChuyenDe("CD008", "Java", 30000, 60, "java.png", "Java 333333"));
        System.out.println(chuyenDeRepository.selectAll());
        
    }

}
