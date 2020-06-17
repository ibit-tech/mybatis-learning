package tech.ibit.mybatis.learning.commonlib.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ServiceLoader;

/**
 * 数据库相关工具类
 *
 * @author IBIT程序猿
 */
public class DbUtils {


    /**
     * 初始化表和数据
     *
     * @param url      数据库url
     * @param user     用户名
     * @param password 密码
     * @return Connection 对象
     * @throws SQLException SQL异常
     * @throws IOException  IO异常
     */
    public static Connection init(String url, String user, String password)
            throws SQLException, IOException {
        // 加载驱动文件
        ServiceLoader.load(java.sql.Driver.class);
        Connection connection = DriverManager.getConnection(url,
                user, password);
        ScriptRunner scriptRunner = new ScriptRunner(connection);
        scriptRunner.setLogWriter(null);
        scriptRunner.runScript(Resources.getResourceAsReader("init-table.sql"));
        scriptRunner.runScript(Resources.getResourceAsReader("init-data.sql"));
        return connection;
    }


    /**
     * 关闭连接
     *
     * @param closeable autoCloseable对象
     */
    public static void close(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
