package com.KamilMarkowski.library.Repository;


import com.KamilMarkowski.library.Model.Author;
import com.KamilMarkowski.library.Model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface DepartmentRepository   extends JpaRepository<Department,Long> {

    @Query("SELECT d FROM Department d  WHERE d.street = :street")
    List<Department> findDepartmentByStreet(@Param("street") String street);


    @Query("SELECT d FROM Department d  WHERE d.street = :city")
    List<Department> findDepartmentByCity(@Param("city") String city);

}
