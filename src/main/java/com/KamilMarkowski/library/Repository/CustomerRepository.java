package com.KamilMarkowski.library.Repository;

import com.KamilMarkowski.library.Model.Author;
import com.KamilMarkowski.library.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CustomerRepository  extends JpaRepository<Customer,Long> {

    @Query("SELECT a FROM Customer a  WHERE a.gender = :gender")
    List<Customer> findAuthorByGender(@Param("gender") String gender);

    @Query(value = "SELECT * FROM Customer a where a.firstName like %:keyword% OR a.lastName like %:keyword% OR a.country like %:keyword% OR a.city like %:keyword% OR a.pesel like %:keyword% ", nativeQuery = true)
    List<Customer> findByKeyword(@Param("keyword") String keyword);
}
