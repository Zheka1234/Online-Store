package com.boss.repository.jdbctemplate.user;

import com.boss.domain.User;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.boss.repository.user.UserTables.BUYS;
import static com.boss.repository.user.UserTables.CHANGED;
import static com.boss.repository.user.UserTables.CREATION;
import static com.boss.repository.user.UserTables.ID_USER;
import static com.boss.repository.user.UserTables.IS_DELETED;
import static com.boss.repository.user.UserTables.LOGIN_USER;
import static com.boss.repository.user.UserTables.NAME_USERS;
import static com.boss.repository.user.UserTables.PASSWORD_USERS;
import static com.boss.repository.user.UserTables.SURNAME_USERS;

@Component
public class UserRowMapper implements RowMapper<User> {

    private static final Logger log = Logger.getLogger(UserRowMapper.class);
    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {

        log.info("User row mapping start");

        User user = new User();

        user.setIdUser(rs.getLong(ID_USER));
        user.setNameUsers(rs.getString(NAME_USERS));
        user.setSurnameUsers(rs.getString(SURNAME_USERS));
        user.setIsDeleted(rs.getBoolean(IS_DELETED));
        user.setBuys(rs.getDouble(BUYS));
        user.setLogin_user(rs.getString(LOGIN_USER));
        user.setPassword_users(rs.getString(PASSWORD_USERS ));
        user.setCreationDate(rs.getTimestamp(CREATION));
        user.setModificationDate(rs.getTimestamp(CHANGED));

        log.info("User row mapping end");
        return user;


    }
}
