package com.boss.reposotory.jdbctemplate;

import com.boss.domain.User;
import com.boss.reposotory.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
@Primary
public class JdbcTemplateUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final UserRowMapper userRowMapper;
    @Override
    public User findById(Long id) {
        return jdbcTemplate.queryForObject("select + from phoneshop.users where id_user = " + id, userRowMapper);
    }

    @Override
    public Optional<User> findOne(Long id) {
        return Optional.of(findById(id));
    }

    @Override
    public List<User> findAll() {
        return findAll(DEFAULT_FIND_ALL_LIMIT, DEFAULT_FIND_ALL_OFFSET);
    }

    @Override
    public List<User> findAll(int limit, int offset) {
        return jdbcTemplate.query("select * from phoneshop.users limit " + limit + " offset " + offset, userRowMapper);
    }

    @Override
    public User create(User object) {
        final String insertQuery =
                "insert into phoneshop.users (name_users, surname_users, is_deleted, buys, login_user, password_users," +
                        " creation_date, modification_date) " + "values (:nameUsers, :surnameUsers, :isDeleted, :buys, :loginUser," +
                        " :password_users, :creation_date, :modification_date);";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("nameUser", object.getUserName());
        mapSqlParameterSource.addValue("surname", object.getUserSurname());
        mapSqlParameterSource.addValue("isDeleted", object.getIsDeleted());
        mapSqlParameterSource.addValue("buys", object.getBuys());
        mapSqlParameterSource.addValue("log", object.getLoginUser());
        mapSqlParameterSource.addValue("pass", object.getPasswordUser());
        mapSqlParameterSource.addValue("creationDate", object.getCreationDate());
        mapSqlParameterSource.addValue("modificationDate", object.getModificationDate());


        namedParameterJdbcTemplate.update(insertQuery, mapSqlParameterSource);

        Long lastInsertId= namedParameterJdbcTemplate.query("select currval('phoneshop.users_id_user_seq') as last_id",
                resultSet -> {
                    resultSet.next();
                    return resultSet.getLong("Last_id");
                });
        return findById(lastInsertId);
    }

    @Override
    public User update(User object) {
        return null;
    }

    @Override
    public Long delete(Long id) {
        jdbcTemplate.update("delete from phoneshop.users where id_user = " + id);
        return id;
    }

    @Override
    public Map<String, Object> getUserStats() {
        return jdbcTemplate.query("select phoneshop.get_user_stats_average_buys(true)", resultSet -> {
            resultSet.next();
            return Collections.singletonMap("avg", resultSet.getDouble(1));
        });
    }
}
