/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cust_ms.controller;

import cust_ms.db.Customer;
import cust_ms.service.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CustomerController {
    
    @Autowired
    CustomerService customerService;    
    
    @RequestMapping(method = RequestMethod.POST, path = "/addcustomer")
    public String addCustomer(@RequestBody Customer body){
        return customerService.addCustomer(body);
    }
    
    @RequestMapping("/getcustomers")
    public List<Customer> getCustomer(){
        return customerService.getCustomers();
    }
    
    @RequestMapping(method = RequestMethod.POST, path = "/deletecustomer")
    public String deteteCustomer(@RequestBody Customer body){
        return customerService.deleteCustomer(body);
    }
    
    @RequestMapping(method = RequestMethod.POST, path = "/findcustomer")
    public String findCustomer(@RequestBody Customer customer){
        return customerService.findCustomer(customer);
    }
}
