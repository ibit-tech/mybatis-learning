package tech.ibit.mybatis.learning.configuration.demo.datasource.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

/**
 * C3p0DataSourceFactory
 *
 * @author IBIT程序猿
 */
public class C3p0DataSourceFactory extends UnpooledDataSourceFactory {

    public C3p0DataSourceFactory() {
        this.dataSource = new ComboPooledDataSource();
    }

}
