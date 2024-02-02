package com.KamilMarkowski.library.Service;

import com.KamilMarkowski.library.Model.Book;
import com.KamilMarkowski.library.Model.Department;
import com.KamilMarkowski.library.Repository.DepartmentRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;


    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public void addDepartment(Department department){
        departmentRepository.save(department);
    }


    public List<Department> getAllDepartments(){
        return departmentRepository.findAll();
    }

    public void deleteById(Long id){
        departmentRepository.deleteById(id);
    }

    public void updateDepartment(Department department){
        departmentRepository.save(department);
    }


    public List<Department> getDepartmentByStreet(String street ){
        return departmentRepository.findDepartmentByStreet(street);
    }

    public List<Department> getDepartmentByCity(String city ){
        return departmentRepository.findDepartmentByCity(city);
    }


    public List<Department> sortByName(String columnName){
        Sort sort = Sort.by(columnName).ascending();
        return departmentRepository.findAll(sort);
    }


    public Department getDepartmentByID(Long id) {
        return  departmentRepository.findById(id).get();
    }

}
