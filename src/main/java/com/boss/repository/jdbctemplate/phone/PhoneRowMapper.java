package com.boss.repository.jdbctemplate.phone;

import com.boss.domain.Phone;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.boss.repository.phone.PhoneTables.BRAND;
import static com.boss.repository.phone.PhoneTables.COLOR;
import static com.boss.repository.phone.PhoneTables.CREATION_DATE;
import static com.boss.repository.phone.PhoneTables.DESCRIPTION;
import static com.boss.repository.phone.PhoneTables.ID_PHONE;
import static com.boss.repository.phone.PhoneTables.IN_STOCK;
import static com.boss.repository.phone.PhoneTables.MODEL;
import static com.boss.repository.phone.PhoneTables.MODIFICATION_DATE;
import static com.boss.repository.phone.PhoneTables.PRICE;


@Component
public class PhoneRowMapper implements RowMapper <Phone> {
    @Override
    public Phone mapRow(ResultSet rs, int i) throws SQLException {

        Phone phone = new Phone();

        phone.setIdPhone(rs.getLong(ID_PHONE));
        phone.setBrand(rs.getString(BRAND));
        phone.setModel(rs.getString(MODEL));
        phone.setColor(rs.getString(COLOR));
        phone.setDescription(rs.getString(DESCRIPTION));
        phone.setPrice(rs.getDouble(PRICE));
        phone.setCreationDate(rs.getTimestamp(CREATION_DATE));
        phone.setModificationDate(rs.getTimestamp(MODIFICATION_DATE));
        phone.setInStock(rs.getBoolean(IN_STOCK));

        return phone;
    }
}
