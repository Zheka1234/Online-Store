package com.boss.repository.jdbctemplate.order;

import com.boss.domain.Order;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.boss.repository.order.OrderTables.CREATION_DATE;
import static com.boss.repository.order.OrderTables.ID_ORDER;
import static com.boss.repository.order.OrderTables.ID_PHONE;
import static com.boss.repository.order.OrderTables.ID_POINT;
import static com.boss.repository.order.OrderTables.ID_USER;
import static com.boss.repository.order.OrderTables.MODIFICATION_DATE;
import static com.boss.repository.order.OrderTables.PAYMENT_TYPE;
import static com.boss.repository.order.OrderTables.TOTAL_SUM;

@Component
public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int i) throws SQLException {

        Order order = new Order();

        order.setIdOrder(rs.getLong(ID_ORDER));
        order.setIdUser(rs.getLong(ID_USER));
        order.setIdPhone(rs.getLong(ID_PHONE));
        order.setIdPoint(rs.getLong(ID_POINT));
        order.setPaymentType(rs.getString(PAYMENT_TYPE));
        order.setTotalSum(rs.getDouble(TOTAL_SUM));
        order.setCreationDate(rs.getTimestamp(CREATION_DATE));
        order.setModificationDate(rs.getTimestamp(MODIFICATION_DATE));

        return order;
    }
}
