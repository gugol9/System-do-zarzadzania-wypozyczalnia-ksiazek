package com.KamilMarkowski.library.Service;

import com.KamilMarkowski.library.Model.Author;
import com.KamilMarkowski.library.Model.Book;
import com.KamilMarkowski.library.Model.Customer;
import com.KamilMarkowski.library.Model.Employee;
import com.KamilMarkowski.library.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> sortByTitle(String columnName){
        Sort sort = Sort.by(columnName).ascending();
        return employeeRepository.findAll(sort);
    }

    public List<Employee> getEmployeeByPosition(String position ){
        return employeeRepository.findEmployeeByPosition(position);
    }

    public List<Employee> findByKeyword(String keyword){return employeeRepository.findByKeyword(keyword); }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public void addEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    public List<Employee> getEmployeesByGender(String gender){
        return employeeRepository.findEmployeeByGender(gender);
    }

    public Employee getEmployeeById(Long id) {
        return  employeeRepository.findById(id).get();
    }

    public void deleteById(Long id){
        employeeRepository.deleteById(id);
    }

    public void updateEmployee(Employee employee){
        employeeRepository.save(employee);
    }


}