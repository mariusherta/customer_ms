/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cust_ms.dao;

import cust_ms.db.Customer;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import org.springframework.jdbc.core.RowMapper;

public class CustomerRowMapper implements RowMapper{

    @Override
    public Customer mapRow(ResultSet rs, int rowNumber) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getInt("id"));
        customer.setFirstName(rs.getString("firstName"));
        customer.setLastName(rs.getString("lastName"));
        return customer;
    }

    
    
}
