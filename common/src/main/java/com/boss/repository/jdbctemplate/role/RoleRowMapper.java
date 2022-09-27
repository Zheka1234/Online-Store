package com.boss.repository.jdbctemplate.role;

import com.boss.domain.Role;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.boss.repository.role.RoleTable.CREATION_DATE;
import static com.boss.repository.role.RoleTable.ID_ROLE;
import static com.boss.repository.role.RoleTable.MODIFICATION_DATE;
import static com.boss.repository.role.RoleTable.ROLE_NAME;

@Component
public class RoleRowMapper implements RowMapper<Role> {


    private static final Logger log = Logger.getLogger(RoleRowMapper.class);

    @Override
    public Role mapRow(ResultSet rs, int i) throws SQLException {
        log.info("Role row mapping start");

        Role role = new Role();

        role.setIdRole(rs.getLong(ID_ROLE));
        role.setRoleName(rs.getString(ROLE_NAME));
        role.setCreationDate(rs.getTimestamp(CREATION_DATE));
        role.setModificationDate(rs.getTimestamp(MODIFICATION_DATE));

        log.info("Role row mapping end");
        return role;
    }
}
