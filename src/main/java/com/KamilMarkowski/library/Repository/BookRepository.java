package com.KamilMarkowski.library.Repository;


import com.KamilMarkowski.library.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface BookRepository  extends JpaRepository<Book,Long> {




    @Query("SELECT  b FROM Book b WHERE b.quality = :quality")
    List<Book> findBookByQuality(@Param("quality") String quality);

    @Query("SELECT  b FROM Book b WHERE b.type = :type")
    List<Book> findBookByType(@Param("type") String type);

    @Query(value = "SELECT b.*, CASE WHEN DATEDIFF(NOW(), b.rental_date) > 90 THEN true ELSE false END AS is_overdue FROM Book b", nativeQuery = true)
    List<Book> findBookByOverdue();

    @Query(value = "SELECT * FROM Book b where b.title like %:keyword% OR b.type like %:keyword% OR b.state like %:keyword% OR b.quality like %:keyword%", nativeQuery = true)
    List<Book> findByKeyword(@Param("keyword") String keyword);

}
