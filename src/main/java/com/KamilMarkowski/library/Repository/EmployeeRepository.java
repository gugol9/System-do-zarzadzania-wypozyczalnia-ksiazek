package com.KamilMarkowski.library.Repository;

import com.KamilMarkowski.library.Model.Author;
import com.KamilMarkowski.library.Model.Book;
import com.KamilMarkowski.library.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee,Long> {


    @Query("SELECT  e FROM Employee e WHERE e.position = :position")
    List<Employee> findEmployeeByPosition(@Param("position") String position);


    @Query("SELECT e FROM Employee e  WHERE e.gender = :gender")
    List<Employee> findEmployeeByGender(@Param("gender") String gender);

    @Query(value = "SELECT * FROM Employee e where e.firstName like %:keyword% OR e.lastName like %:keyword% OR e.country like %:keyword% OR e.city like %:keyword% OR e.position like %:keyword%", nativeQuery = true)
    List<Employee> findByKeyword(@Param("keyword") String keyword);



}
