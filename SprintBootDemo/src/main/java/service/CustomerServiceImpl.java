package service;

import com.capg.Springboot.dto.CustomerDTO;
import com.capg.Springboot.entity.Customer;
import com.capg.Springboot.exception.CustomerNotFoundException;
import com.capg.Springboot.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public void createCustomerRecord(CustomerDTO customerDTO) {

        try{
            // System.out.println("");
            Customer customer = new Customer();
            //  customer.getCustomerId();
            customer.setCustomerType(customerDTO.getCustomerType());
            customer.setEmail(customerDTO.getEmail());
            customer.setFirstName(customerDTO.getFirstName());
            customer.setLastName(customerDTO.getLastName());
            customerRepository.save(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }return;
    }

    @Override
    public List<Customer> fetchAllCustomers() {
        List<Customer> customers= customerRepository.findAll();
        List<Customer> customerDTOList=new  ArrayList<>();
        customerDTOList= customers.stream().map(customer->{

            Customer customerDTO =new Customer();
            customerDTO.setEmail(customer.getEmail());
            customerDTO.setFirstName(customer.getFirstName());
            customerDTO.setLastName(customer.getLastName());
            customerDTO.setCustomerType(customer.getCustomerType());
            customerDTO.setCustomerId(customer.getCustomerId());
            return customerDTO;
        }).collect(Collectors.toList());
        return customerDTOList;
    }

    @Override
    public void deleteACustomer(Integer customerId) {
        Optional<Customer> optionalCustomerDTO=customerRepository.findById(customerId);
        if(optionalCustomerDTO.isPresent())
            customerRepository.deleteById(customerId);
        try {
            throw new CustomerNotFoundException("Delete Operation failed \n No customer Found with id: "+customerId);
        } catch (CustomerNotFoundException e) {
            e.printStackTrace();
        }


    }

    @Override
    public CustomerDTO fetechcustomerById(Integer customerId) {
        Customer customer=null;
        Optional<Customer>optionalCustomerDTO=customerRepository.findById(customerId);
        if(optionalCustomerDTO.isPresent()) {
            customer=optionalCustomerDTO.get();
            CustomerDTO customerDTO =new CustomerDTO();
            customerDTO.setEmail(customer.getEmail());
            customerDTO.setFirstName(customer.getFirstName());
            customerDTO.setLastName(customer.getLastName());
            customerDTO.setCustomerType(customer.getCustomerType());
            customerDTO.setCustomerId(customer.getCustomerId());
            return customerDTO;
        }
        else {
            try {
                throw new CustomerNotFoundException("Get operation Failed \n No Customer Found with Customer Id:"+ customerId);
            } catch (CustomerNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
