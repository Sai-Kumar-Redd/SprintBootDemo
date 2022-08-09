package service;
import com.capg.Springboot.dto.CustomerDTO;
import com.capg.Springboot.entity.Customer;
import com.capg.Springboot.exception.CustomerNotFoundException;
import java.util.List;
public interface CustomerService {

    public void createCustomerRecord(CustomerDTO customerDTO);
    public List<Customer> fetchAllCustomers();
    public void deleteACustomer(Integer customerId) throws CustomerNotFoundException;
    public CustomerDTO fetechcustomerById(Integer customerId);
}