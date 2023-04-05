package com.internetx.service;

import com.internetx.domain.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public  class RoleRowMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        Role role = new Role();
        role.setId(rs.getInt("id"));
        role.setUserId(rs.getInt("user_id"));
        role.setAdmin(rs.getInt("role_admin") == 1);
        role.setDevelop(rs.getInt("role_develop") == 1);
        role.setCctld(rs.getInt("role_cctld") == 1);
        role.setGtld(rs.getInt("role_gtld") == 1);
        role.setBilling(rs.getInt("role_billing") == 1);
        role.setRegistry(rs.getInt("role_registry") == 1);
        role.setCanReadPurchases(rs.getInt("role_purchase_read") == 1);
        role.setCanWritePurchases(rs.getInt("role_purchase_write") == 1);
        role.setCanWriteSales(rs.getInt("role_sale_write") == 1);
        role.setCanExecuteSql(rs.getInt("role_sql") == 1);
        return role;
    }

}