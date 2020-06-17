package tech.ibit.mybatis.learning.common.tools;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ScriptRunner 例子
 *
 * @author IBIT程序猿
 */
public class ScriptRunnerTest {

    @Test
    public void testScriptRunner() {

        try (Connection connection = DriverManager.getConnection(
                "jdbc:hsqldb:mem:mybatis-test",
                "sa", "")) {

            ScriptRunner scriptRunner = new ScriptRunner(connection);
            scriptRunner.setLogWriter(null);
            scriptRunner.runScript(Resources.getResourceAsReader("init-table.sql"));
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

}
