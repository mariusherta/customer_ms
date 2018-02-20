/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cust_ms.service;

import cust_ms.db.Customer;
import cust_ms.dao.CustomerDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerDao customerDao;
    
    @Transactional
    public String addCustomer(Customer customer){
        String rez = "Failed!";
        List<Customer> customerList = customerDao.getCustomers();
        
        if(customerList.contains(customer)){
            rez = "Customer already exists.";
        } else if (customerDao.addCustomer(customer)){
            rez = "Customer added to database.";
        }        
        return rez;
    }
    
    public List<Customer> getCustomers(){
        return customerDao.getCustomers();
    }
    
    @Transactional
    public String deleteCustomer(Customer customer){
        String rez = "No such customer!";
        List<Customer> customerList = customerDao.getCustomers();
        
        if(customerList.contains(customer)){
            customerDao.deleteCustomer(customer);
            rez = "Customer deleted.";
        }
        
        return rez;
    }
    
    
    public String findCustomer(Customer customer){
        String rez = "Customer does not exist in the database";
        
        if(customerDao.getCustomers().contains(customer)){
            rez = "Customer exists in the database";
        }
        
        return rez;
    }
}
