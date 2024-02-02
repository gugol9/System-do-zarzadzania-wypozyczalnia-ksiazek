package com.KamilMarkowski.library.Service;


import com.KamilMarkowski.library.Model.Author;
import com.KamilMarkowski.library.Model.Book;
import com.KamilMarkowski.library.Model.Customer;
import com.KamilMarkowski.library.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void addCustomer(Customer customer){
        customerRepository.save(customer);
    }

    public List<Customer> getCustomerByGender(String gender){
        return customerRepository.findAuthorByGender(gender);
    }


    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return  customerRepository.findById(id).get();
    }

    public void updateCustomer(Customer customer){
        customerRepository.save(customer);
    }

    public void deleteById(Long id){
        customerRepository.deleteById(id);
    }

    public List<Customer> findByKeyWord(String keyword){return customerRepository.findByKeyword(keyword);}


    public List<Customer> sortByTitle(String columnName){
        Sort sort = Sort.by(columnName).ascending();
        return customerRepository.findAll(sort);
    }
}
