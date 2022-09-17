package com.boss.repository.jdbctemplate.user;

import com.boss.domain.User;
import com.boss.repository.user.UserRepository;
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
        return jdbcTemplate.queryForObject("select * from phoneshop.users where id_user = " + id, userRowMapper);
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
                "insert into phoneshop.users ( name_users, surname_users, is_deleted, buys, login_user, password_users," +
                        " creation_date, modification_date) " + "values ( :nameUsers, :surnameUsers, :isDeleted, :buys, :loginUser," +
                        " :passwordUsers, :creationDate, :modificationDate);";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("nameUsers", object.getNameUsers());
        mapSqlParameterSource.addValue("surnameUsers", object.getSurnameUsers());
        mapSqlParameterSource.addValue("isDeleted", object.getIsDeleted());
        mapSqlParameterSource.addValue("buys", object.getBuys());
        mapSqlParameterSource.addValue("loginUser", object.getLogin_user());
        mapSqlParameterSource.addValue("passwordUsers", object.getPassword_users());
        mapSqlParameterSource.addValue("creationDate", object.getCreationDate());
        mapSqlParameterSource.addValue("modificationDate", object.getModificationDate());


        namedParameterJdbcTemplate.update(insertQuery, mapSqlParameterSource);

        Long lastInsertId = namedParameterJdbcTemplate.query("select currval('phoneshop.users_id_user_seq') as last_id_user",
                resultSet -> {
                    resultSet.next();
                    return resultSet.getLong("Last_id_user");
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
        return jdbcTemplate.query("select phoneshop.get_user_stats_average_buys(false)", resultSet -> {
            resultSet.next();
            return Collections.singletonMap("avg", resultSet.getDouble(1));
        });
    }

    @Override
    public Optional<User> findByLogin(String login) {
        final String searchByLoginQuery = "select * from phoneshop.users where login_user = :login";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("login_user", login);

        return Optional.of(namedParameterJdbcTemplate.queryForObject(searchByLoginQuery, mapSqlParameterSource, userRowMapper));
    }
}
