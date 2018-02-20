package cust_ms;

import cust_ms.dao.CustomerDao;
import cust_ms.db.Customer;
import cust_ms.service.CustomerService;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "unittest")
public class CustomerMsApplicationTests {
    
        @MockBean
        private CustomerDao customerDao;
        
        @Autowired
        CustomerService customerService;

	@Test
	public void contextLoads() {
	}
        
        @Test
        public void checkCustomerServiceInjection(){
            Assert.assertNotNull(customerService);
        }
        
        @Test
        public void testGetCustomers(){
            BDDMockito.given(customerDao.getCustomers())
                    .willReturn(Arrays.asList(new Customer("Test1","Test1"),new Customer("Test2","Test2")));
            List<?>testList = customerService.getCustomers();
            Assert.assertNotNull(testList);
            Assert.assertTrue(testList.size()== 2);
            Assert.assertTrue(testList.contains(new Customer("Test1","Test1"))&& testList.contains(new Customer("Test2","Test2")));
        }
        
        @Test
        public void testAddNewCustomer(){
            Customer customer = new Customer("Test","Test");
            BDDMockito.given(customerDao.getCustomers()).willReturn(Collections.emptyList());
            BDDMockito.given(customerDao.addCustomer(customer)).willReturn(true);
            String resultMessage = customerService.addCustomer(customer);
            Assert.assertTrue("Customer added to database.".equals(resultMessage));
            Mockito.verify(customerDao, times(1)).getCustomers();
            Mockito.verify(customerDao, times(1)).addCustomer(customer);
        }
        
        @Test
        public void testAddExistingCustomer(){
            Customer customer = new Customer("Test","Test");
            BDDMockito.given(customerDao.getCustomers()).willReturn(Arrays.asList(customer));     
            String resultMessage = customerService.addCustomer(customer);
            Assert.assertTrue("Customer already exists.".equals(resultMessage));
            Mockito.verify(customerDao, times(1)).getCustomers();
            Mockito.verify(customerDao, times(0)).addCustomer(customer);
        }
        
        @Test
        public void testDeleteExistingCustomer(){
            Customer customer = new Customer("Test","Test");
            BDDMockito.given(customerDao.getCustomers()).willReturn(Arrays.asList(customer));
            BDDMockito.given(customerDao.deleteCustomer(customer)).willReturn(true);
            String resultMessage = customerService.deleteCustomer(customer);
            Assert.assertTrue("Customer deleted.".equals(resultMessage));
            Mockito.verify(customerDao, times(1)).getCustomers();
            Mockito.verify(customerDao, times(1)).deleteCustomer(customer);
        }
        
        @Test
        public void testDeleteNotExistingCustomer(){
            Customer customer = new Customer("Test","Test");
            BDDMockito.given(customerDao.getCustomers()).willReturn(Collections.emptyList());
            BDDMockito.given(customerDao.deleteCustomer(customer)).willReturn(true);
            String resultMessage = customerService.deleteCustomer(customer);
            Assert.assertTrue("No such customer!".equals(resultMessage));
            Mockito.verify(customerDao, times(1)).getCustomers();
            Mockito.verify(customerDao, times(0)).deleteCustomer(customer);
        } 
        
        @Test
        public void testFindingCustomer(){
            Customer customer = new Customer("Test","Test");
            BDDMockito.given(customerDao.getCustomers()).willReturn(Arrays.asList(customer));
            String resultMessage = customerService.findCustomer(customer);
            Assert.assertTrue("Customer exists in the database".equals(resultMessage));
            Mockito.verify(customerDao, times(1)).getCustomers();
        }
        
        @Test
        public void testNotFindingCustomer(){
            Customer customer = new Customer("Test","Test");
            BDDMockito.given(customerDao.getCustomers()).willReturn(Collections.emptyList());
            String resultMessage = customerService.findCustomer(customer);
            Assert.assertTrue("Customer does not exist in the database".equals(resultMessage));
            Mockito.verify(customerDao, times(1)).getCustomers();
        }
       
}
