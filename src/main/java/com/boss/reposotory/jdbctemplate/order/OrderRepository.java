package com.boss.reposotory.jdbctemplate.order;

import com.boss.domain.Order;
import com.boss.reposotory.order.OrderRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Primary
public class OrderRepository implements OrderRepositoryInterface {


    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final OrderRowMapper orderRowMapper;

    @Override
    public Order findById(Long id) {
        return jdbcTemplate.queryForObject("select * from phoneshop.order where id_order = " + id, orderRowMapper);
    }

    @Override
    public Optional<Order> findOne(Long id) {
        return Optional.of(findById(id));
    }

    @Override
    public List<Order> findAll() {
        return findAll(DEFAULT_FIND_ALL_LIMIT, DEFAULT_FIND_ALL_OFFSET);
    }

    @Override
    public List<Order> findAll(int limit, int offset) {
        return jdbcTemplate.query("" +
                "select * from phoneshop.order limit" + limit + "offset" + offset, orderRowMapper);
    }

    @Override
    public Order create(Order object) {

        final String insertQuery =
                "insert into phoneshop.order (id_user, id_phone, id_point, payment_type, total_sum," +
                        "creation_date, modification_date) " + "values (:idUser, :idPhone, :idPoint, :paymentType," +
                        ":totalSum, :creationDate, :modificationDate);";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("idUser", object.getIdUser());
        mapSqlParameterSource.addValue("idPhone", object.getIdPhone());
        mapSqlParameterSource.addValue("idPoint", object.getIdPoint());
        mapSqlParameterSource.addValue("paymentType", object.getPaymentType());
        mapSqlParameterSource.addValue("totalSum", object.getTotalSum());
        mapSqlParameterSource.addValue("creationDate", object.getCreationDate());
        mapSqlParameterSource.addValue("modificationDate", object.getModificationDate());

        namedParameterJdbcTemplate.update(insertQuery, mapSqlParameterSource);

        Long lastInsertId = namedParameterJdbcTemplate.query("select currval('phoneshop.order_id_order_seq') as " +
                "last_id_order", resultSet ->
        {
            resultSet.next();
            return resultSet.getLong("last_id_order");
        }) ;
        return findById(lastInsertId);

    }

    @Override
    public Order update(Order object) {
        final String updateQuery =
                "update phoneshop.order set id_order = :idOrder, id_user = :idUser, id_phone = :idPhone," +
                        "id_point = :idPoint, payment_type = :paymentType, total_sum = :totalSum," +
                        "creation_date = :creationDate, modification_date = :modificationDate where id_order = :idOrder";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("idOrder", object.getIdOrder());
        mapSqlParameterSource.addValue("idUser", object.getIdUser());
        mapSqlParameterSource.addValue("idPhone", object.getIdPhone());
        mapSqlParameterSource.addValue("idPoint", object.getIdPoint());
        mapSqlParameterSource.addValue("paymentType", object.getPaymentType());
        mapSqlParameterSource.addValue("totalSum", object.getTotalSum());
        mapSqlParameterSource.addValue("creationDate", object.getCreationDate());
        mapSqlParameterSource.addValue("modificationDate", object.getModificationDate());

        namedParameterJdbcTemplate.update(updateQuery, mapSqlParameterSource);
        return findById(object.getIdOrder());

    }

    @Override
    public Long delete(Long id) {
        jdbcTemplate.update("delete  from phoneshop.order where id_order = ?", new Object[] {id});
        return id;
    }


    @Override
    public Map<String, Object> getMapOfOrder() {
        return null;
    }
}
