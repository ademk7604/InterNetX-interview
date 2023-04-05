package com.internetx.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private int id;
    private int userId; // user_id
    private boolean isAdmin; // role_admin
    private boolean isDevelop; // role_develop
    private boolean isCctld; // role_cctld
    private boolean isGtld;  // role_gtld
    private boolean isBilling; // role_billing
    private boolean isRegistry; // role_registry
    private boolean canReadPurchases; // role_purchase_read
    private boolean canWritePurchases; // role_purchase_write
    private boolean canWriteSales; // role_sale_write
    private boolean canExecuteSql; // role_sql

}
