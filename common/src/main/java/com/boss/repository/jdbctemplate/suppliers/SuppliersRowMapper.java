package com.boss.repository.jdbctemplate.suppliers;

import com.boss.domain.Suppliers;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.boss.repository.suppliers.SuppliersTables.ADDRESS_SUPPLIERS;
import static com.boss.repository.suppliers.SuppliersTables.CREATION_DATE;
import static com.boss.repository.suppliers.SuppliersTables.ID_SUPPLIERS;
import static com.boss.repository.suppliers.SuppliersTables.MODIFICATION_DATE;
import static com.boss.repository.suppliers.SuppliersTables.NAME_SUPPLIERS;
import static com.boss.repository.suppliers.SuppliersTables.PHONE_SUPPLIERS;

@Component
public class SuppliersRowMapper implements RowMapper<Suppliers> {
    @Override
    public Suppliers mapRow(ResultSet rs, int i) throws SQLException {

        Suppliers suppliers = new Suppliers();

        suppliers.setIdSuppliers(rs.getLong(ID_SUPPLIERS));
        suppliers.setNameSuppliers(rs.getString(NAME_SUPPLIERS ));
        suppliers.setAddressSuppliers(rs.getString(ADDRESS_SUPPLIERS));
        suppliers.setPhoneSuppliers(rs.getString(PHONE_SUPPLIERS));
        suppliers.setCreationDate(rs.getTimestamp(CREATION_DATE));
        suppliers.setModificationDate(rs.getTimestamp(MODIFICATION_DATE));

        return suppliers;

    }
}
