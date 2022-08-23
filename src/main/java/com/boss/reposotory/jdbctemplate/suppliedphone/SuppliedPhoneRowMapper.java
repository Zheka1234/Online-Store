package com.boss.reposotory.jdbctemplate.suppliedphone;

import com.boss.domain.SuppliedPhone;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.boss.reposotory.suupliedphone.SuppliedPhoneTables.CREATION_DATE;
import static com.boss.reposotory.suupliedphone.SuppliedPhoneTables.ID_PHONE;
import static com.boss.reposotory.suupliedphone.SuppliedPhoneTables.ID_SUPPLIED_PHONE;
import static com.boss.reposotory.suupliedphone.SuppliedPhoneTables.ID_SUPPLIERS;
import static com.boss.reposotory.suupliedphone.SuppliedPhoneTables.MODIFICATION_DATE;

@Component
public class SuppliedPhoneRowMapper implements RowMapper <SuppliedPhone> {
    @Override
    public SuppliedPhone mapRow(ResultSet rs, int i) throws SQLException {

        SuppliedPhone suppliedPhone = new SuppliedPhone();

        suppliedPhone.setIdSuppliedPhone(rs.getLong(ID_SUPPLIED_PHONE));
        suppliedPhone.setIdPhone(rs.getLong(ID_PHONE));
        suppliedPhone.setIdSuppliers(rs.getLong(ID_SUPPLIERS ));
        suppliedPhone.setCreationDate(rs.getTimestamp(CREATION_DATE));
        suppliedPhone.setModificationDate(rs.getTimestamp(MODIFICATION_DATE));

        return suppliedPhone;
    }
}
