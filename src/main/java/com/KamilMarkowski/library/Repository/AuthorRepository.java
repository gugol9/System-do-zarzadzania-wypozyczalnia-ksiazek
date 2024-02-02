package com.KamilMarkowski.library.Repository;

import com.KamilMarkowski.library.Model.Author;
import com.KamilMarkowski.library.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AuthorRepository extends JpaRepository<Author,Long> {


    @Query("SELECT a FROM Author a  WHERE a.gender = :gender")
    List<Author> findAuthorByGender(@Param("gender") String gender);

    @Query("SELECT a FROM Author a  WHERE a.nobel = 'tak'")
    List<Author> getActorsWithNobel();

    @Query(value = "SELECT * FROM Author a where a.firstName like %:keyword% OR a.lastName like %:keyword% OR a.country like %:keyword% OR a.city like %:keyword% ", nativeQuery = true)
    List<Author> findByKeyword(@Param("keyword") String keyword);
}
