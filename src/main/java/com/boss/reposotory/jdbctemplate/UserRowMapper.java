package com.boss.reposotory.jdbctemplate;

import com.boss.domain.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.boss.reposotory.user.UserTables.BUYS;
import static com.boss.reposotory.user.UserTables.CHANGED;
import static com.boss.reposotory.user.UserTables.CREATION;
import static com.boss.reposotory.user.UserTables.ID_USER;
import static com.boss.reposotory.user.UserTables.IS_DELETED;
import static com.boss.reposotory.user.UserTables.LOGIN_USER;
import static com.boss.reposotory.user.UserTables.NAME_USERS;
import static com.boss.reposotory.user.UserTables.PASSWORD_USERS;
import static com.boss.reposotory.user.UserTables.SURNAME_USERS;

@Component
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        User user = new User();


        user.setIdUser(rs.getLong(ID_USER));
        user.setNameUsers(rs.getString(NAME_USERS));
        user.setSurnameUsers(rs.getString(SURNAME_USERS));
        user.setIsDeleted(rs.getBoolean(IS_DELETED));
        user.setBuys(rs.getDouble(BUYS));
        user.setLoginUser(rs.getString(LOGIN_USER));
        user.setPasswordUsers(rs.getString(PASSWORD_USERS ));
        user.setCreationDate(rs.getTimestamp(CREATION));
        user.setModificationDate(rs.getTimestamp(CHANGED));

        return user;


    }
}
