package com.boss.reposotory.user;

import com.boss.domain.User;
import com.boss.exception.NoSuchEntityException;
import com.boss.util.DatabaseProperties;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.boss.reposotory.user.UserTables.BUYS;
import static com.boss.reposotory.user.UserTables.CHANGED;
import static com.boss.reposotory.user.UserTables.CREATION;
import static com.boss.reposotory.user.UserTables.ID_USER;
import static com.boss.reposotory.user.UserTables.IS_DELETED;
import static com.boss.reposotory.user.UserTables.LOGIN_USER;
import static com.boss.reposotory.user.UserTables.NAME_USERS;
import static com.boss.reposotory.user.UserTables.PASSWORD_USERS;
import static com.boss.reposotory.user.UserTables.SURNAME_USERS;


@Repository
@RequiredArgsConstructor
public class UsersRepository implements UserRepository{

    private final DatabaseProperties databaseProperties;
    @Override
    public User findById(Long id) {
        final String findByIdQuery = "select * from phoneshop.users where id_user = " + id;
        Connection connection;
        Statement statement;
        ResultSet rs;

        try {
            connection = getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(findByIdQuery);
            boolean hasRow = rs.next();
            if (hasRow) {
                return userRowMapping(rs);
            } else {
                throw new NoSuchEntityException("Entity User with id " + id + " does not exist", 404);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }

    }

    private User userRowMapping(ResultSet rs) throws SQLException {

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

    @Override
    public Optional<User> findOne(Long id) {
        return Optional.of(findById(id));
    }

    @Override
    public List<User> findAll() {
        return findAll(DEFAULT_FIND_ALL_LIMIT, DEFAULT_FIND_ALL_OFFSET);
    }

    private java.sql.Connection getConnection() throws SQLException {
        try {
            String driver = databaseProperties.getDriverName();

            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        String url = databaseProperties.getUrl();
        String port = databaseProperties.getPort();
        String dbName = databaseProperties.getName();
        String login = databaseProperties.getLogin();
        String password = databaseProperties.getPassword();

        String jdbcURL = StringUtils.join(url, port, dbName);

        return DriverManager.getConnection(jdbcURL, login, password);
    }

    @Override
    public List<User> findAll(int limit, int offset) {
        final String findAllQuery = "select * from phoneshop.users order by id_user limit " + limit + " offset " + offset;
        List<User> result = new ArrayList<>();

        Connection connection;
        Statement statement;
        ResultSet rs;

        try {
            connection = getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(findAllQuery);

            while (rs.next()) {
                result.add(userRowMapping(rs));
            }

            return result;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public User create(User object) {
        final String insertQuery =
                "insert into phoneshop.users (name_users, surname_users, is_deleted, buys, login_user, password_users," +
                        " creation_date, modification_date) " + "values (?, ?, ?, ?, ?, ?, ?. ?);";


        Connection connection;
        PreparedStatement statement;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(insertQuery);

            statement.setString(1, object.getNameUsers());
            statement.setString(2, object.getSurnameUsers());
            statement.setBoolean(3, object.getIsDeleted());
            statement.setDouble(4, object.getBuys());
            statement.setTimestamp(5, object.getCreationDate());
            statement.setTimestamp(6, object.getModificationDate());
            statement.setString(7, object.getLoginUser());
            statement.setString(8, object.getPasswordUsers());

            //executeUpdate - for INSERT, UPDATE, DELETE
            statement.executeUpdate();
            //For select
            //statement.executeQuery();

            /*Get users last insert id for finding new object in DB*/
            ResultSet resultSet = connection.prepareStatement("SELECT currval('phoneshop.users_id_user_seq') as last_id").executeQuery();
            resultSet.next();
            long userLastInsertId = resultSet.getLong("last_id");

            return findById(userLastInsertId);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public User update(User object) {
        final String updateQuery =
                "update phoneshop.users" +
                    " set " +
                        " name_users = ?, surname_users = ?, is_deleted = ?, buys = ?, creation_date = ?, modification_date = ?," +
                        "login_user = ?, password_users = ? where id_user = ?";
        Connection connection;
        PreparedStatement statement;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(updateQuery);

            statement.setString(1, object.getNameUsers());
            statement.setString(2, object.getSurnameUsers());
            statement.setBoolean(3, object.getIsDeleted());
            statement.setDouble(4, object.getBuys());
            statement.setTimestamp(5, object.getCreationDate());
            statement.setTimestamp(6, object.getModificationDate());
            statement.setString(7, object.getLoginUser());
            statement.setString(8, object.getPasswordUsers());
            statement.setLong(9, object.getIdUser());

            statement.executeUpdate();


            return findById(object.getIdUser());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public Long delete(Long id) {
        return null;
    }

    @Override
    public Map<String, Object> getUserStats() {
        return null;
    }
}
