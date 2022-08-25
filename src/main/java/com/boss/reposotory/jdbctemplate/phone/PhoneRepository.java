package com.boss.reposotory.jdbctemplate.phone;


import com.boss.domain.Phone;
import com.boss.reposotory.phone.PhoneRepositoryInterface;
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
public class PhoneRepository implements PhoneRepositoryInterface {


    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final PhoneRowMapper phoneRowMapper;

    @Override
    public Phone findById(Long id) {
        return jdbcTemplate.queryForObject("select * from phoneshop.phone where id_phone = " + id , phoneRowMapper);
    }

    @Override
    public Optional<Phone> findOne(Long id) {
        return Optional.of(findById(id));
    }

    @Override
    public List<Phone> findAll() {
        return findAll(DEFAULT_FIND_ALL_LIMIT, DEFAULT_FIND_ALL_OFFSET);
    }

    @Override
    public List<Phone> findAll(int limit, int offset) {
        return jdbcTemplate.query("" +
                "select * from phoneshop.phone limit" + limit + "offset" + offset, phoneRowMapper);
    }

    @Override
    public Phone create(Phone object) {

        final String insertQuery =
                "insert into phoneshop.phone (brand, model, color, description, price, creation_date, modification_date," +
                        "in_stock) " + "values ( :brand, :model, :color, : description, :price," +
                        ":creationDate, :modificationDate, :isStock);";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("brand", object.getBrand());
        mapSqlParameterSource.addValue("model", object.getModel());
        mapSqlParameterSource.addValue("color", object.getColor());
        mapSqlParameterSource.addValue("description", object.getDescription());
        mapSqlParameterSource.addValue("price", object.getPrice());
        mapSqlParameterSource.addValue("creationDate", object.getCreationDate());
        mapSqlParameterSource.addValue("modificationDate", object.getModificationDate());
        mapSqlParameterSource.addValue("inStock", object.getInStock());

        namedParameterJdbcTemplate.update(insertQuery, mapSqlParameterSource);

        Long lastInsertId = namedParameterJdbcTemplate.query("select currval('phoneshop.phone_id_phone_seq') as last_id_phone",
                resultSet -> {
            resultSet.next();
            return resultSet.getLong("last_id_phone");
                });
        return findById(lastInsertId);
    }

    @Override
    public Phone update(Phone object) {
        final String updateQuery =
                "update phoneshop.phone  set  id_phone = :idPhone, brand = :brand, model = :model, color = :color," +
                        "description = :description, price = :price, creation_date = :creationDate, " +
                        "modification_date = :modificationDate, in_stock = :inStock where id_phone = :idPhone";
        MapSqlParameterSource  mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("idPhone", object.getIdPhone());
        mapSqlParameterSource.addValue("brand", object.getBrand());
        mapSqlParameterSource.addValue("model", object.getModel());
        mapSqlParameterSource.addValue("color", object.getColor());
        mapSqlParameterSource.addValue("description", object.getDescription());
        mapSqlParameterSource.addValue("price", object.getPrice());
        mapSqlParameterSource.addValue("creationDate", object.getCreationDate());
        mapSqlParameterSource.addValue("modificationDate", object.getModificationDate());
        mapSqlParameterSource.addValue("inStock", object.getInStock());

        namedParameterJdbcTemplate.update(updateQuery, mapSqlParameterSource);

        return findById(object.getIdPhone());

    }

    @Override
    public Long delete(Long id) {
        jdbcTemplate.update("delete from phoneshop.phone where id_phone = ? ", new Object[] {id});
        return id;
    }

    @Override
    public Map<String, Object> getMapOfPhone() {
        return null;
    }
}
