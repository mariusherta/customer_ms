/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cust_ms.dao;

import cust_ms.db.Customer;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class CustomerDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public boolean addCustomer(Customer customer){
        String sql = "INSERT INTO customers VALUES (NULL,?,?)";
        jdbcTemplate.update(sql, customer.getFirstName(),customer.getLastName());
        return true;
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Customer> getCustomers(){
        List<Customer> customerList = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        customerList = jdbcTemplate.query(sql, new CustomerRowMapper());
        
        return customerList;
    }
    
    public boolean deleteCustomer(Customer customer){
        String sql = "DELETE FROM customers WHERE firstName = ? AND lastName = ?";
        jdbcTemplate.update(sql, customer.getFirstName(),customer.getLastName());
        return true;
    }
    
}
