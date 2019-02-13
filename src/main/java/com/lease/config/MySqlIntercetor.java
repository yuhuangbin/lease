package com.lease.config;

import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.ConnectionProxy;
import com.alibaba.druid.proxy.jdbc.PreparedStatementProxy;
import com.alibaba.druid.sql.SQLUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * Description:
 * author: yu.hb
 * Date: 2019-02-13
 */
@Component
public class MySqlIntercetor extends FilterEventAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(MySqlIntercetor.class);

    @Override
    public boolean preparedStatement_execute(FilterChain chain, PreparedStatementProxy statement) throws SQLException {
        try {
            Long start = System.currentTimeMillis();
            boolean statementExecute = super.preparedStatement_execute(chain, statement);
            Long end = System.currentTimeMillis();
            String sql = SQLUtils.formatMySql(statement.getSql());
            LOGGER.info("执行sql[{}]，耗时{}ms",sql,end-start);
            return statementExecute;
        }catch (Exception e) {
            throw e;
        }

    }
}
