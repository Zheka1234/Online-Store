package com.boss.repository.jdbctemplate.role;

import com.boss.domain.Role;
import com.boss.repository.role.RoleRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Primary
public class RoleRepository implements RoleRepositoryInterface {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final RoleRowMapper roleRowMapper;


    @Override
    public Role findById(Long id) {
        return jdbcTemplate.queryForObject("select * from phoneshop.role where id_role = " + id, roleRowMapper);
    }

    @Override
    public Optional<Role> findOne(Long id) {
        return Optional.of(findById(id));
    }

    @Override
    public List<Role> findAll() {
        return  findAll(DEFAULT_FIND_ALL_LIMIT, DEFAULT_FIND_ALL_OFFSET);
    }

    @Override
    public List<Role> findAll(int limit, int offset) {
        return jdbcTemplate.query("select * from phoneshop.role limit " + limit + " offset " + offset, roleRowMapper);
    }

    @Override
    public Role create(Role object) {
        final String insertQuery =
                "insert into phoneshop.role (role_name, creation_date, modification_date) " +
                        " values (:roleName, :creationDate, :modificationDate);";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("roleName", object.getRoleName());
        mapSqlParameterSource.addValue("creationDate", object.getCreationDate());
        mapSqlParameterSource.addValue("modificationDate", object.getModificationDate());

        namedParameterJdbcTemplate.update(insertQuery, mapSqlParameterSource);

        Long lastInsertId = namedParameterJdbcTemplate.query("SELECT currval('phoneshop.role_id_role_seq') as last_id",
                resultSet -> {
                    resultSet.next();
                    return resultSet.getLong("last_id");
                });

        return findById(lastInsertId);
    }

    @Override
    public Role update(Role object) {
        return null;
    }

    @Override
    public Long delete(Long id) {
        jdbcTemplate.update("delete from phoneshop.role where id_role = " + id);
        return id;
    }

    @Override
    public List<Role> findRoleByUserId(Long userId) {
        return jdbcTemplate.query(
                "select * from phoneshop.role " +
                        " inner join phoneshop.l_role_users lru on role.id_role = lru.id_role " +
                        " where lru.id_user= " + userId,
                roleRowMapper);
    }
}
