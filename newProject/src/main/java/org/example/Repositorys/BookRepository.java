package org.example.Repositorys;

import org.example.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


//    @Query(nativeQuery = true, value = "select * from Book where id = :id")
//    public String getBookById(long id);
}
