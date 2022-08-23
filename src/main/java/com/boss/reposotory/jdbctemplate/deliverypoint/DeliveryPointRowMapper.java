package com.boss.reposotory.jdbctemplate.deliverypoint;

import com.boss.domain.DeliveryPoint;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.boss.reposotory.deliverypoint.DeliveryPointTables.ADDRESS_POINT;
import static com.boss.reposotory.deliverypoint.DeliveryPointTables.CREATION_DATE;
import static com.boss.reposotory.deliverypoint.DeliveryPointTables.HOURS;
import static com.boss.reposotory.deliverypoint.DeliveryPointTables.ID_POINT;
import static com.boss.reposotory.deliverypoint.DeliveryPointTables.MODIFICATION_DATE;

@Component
public class DeliveryPointRowMapper implements RowMapper <DeliveryPoint> {

    @Override
    public DeliveryPoint mapRow(ResultSet rs, int i) throws SQLException {

        DeliveryPoint deliveryPoint = new DeliveryPoint();

        deliveryPoint.setIdPoint(rs.getLong(ID_POINT));
        deliveryPoint.setAddressPoint(rs.getString(ADDRESS_POINT));
        deliveryPoint.setHours(rs.getString(HOURS));
        deliveryPoint.setCreationDate(rs.getTimestamp(CREATION_DATE));
        deliveryPoint.setModificationDate(rs.getTimestamp(MODIFICATION_DATE));


        return deliveryPoint;
    }
}

