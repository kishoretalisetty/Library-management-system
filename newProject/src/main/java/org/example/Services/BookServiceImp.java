package org.example.Services;

import org.example.Dtos.BookRequestDto;
import org.example.Entities.Book;
import org.example.Repositorys.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImp implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void addBook(BookRequestDto bookRequestDto){
        Book book=new Book();
        book.setName(bookRequestDto.getName());

        if(bookRequestDto.getCreatedDate()!=null && bookRequestDto.getCreatedDate().equals("")){
            book.setCreatedDate(bookRequestDto.getCreatedDate());
        }
        else{
            book.setCreatedDate(Date.valueOf(LocalDate.now()));
        }

        if(bookRequestDto.getUpdatedDate()!=null && bookRequestDto.getUpdatedDate().equals("")){
            book.setUpdatedDate(bookRequestDto.getUpdatedDate());
        }
        else{
            book.setUpdatedDate(Date.valueOf(LocalDate.now()));
        }

        book.setAvailable(true);
        book.setAuothorName(bookRequestDto.getAuothorName());

        bookRepository.save(book);
    }

    @Override
    public boolean isAvailble(String bookName){
       List<Book> bookList= bookRepository.findAll();
       List<Book> booksByName=bookList.stream().
               filter(b->b.getName().equals(bookName)).
               collect(Collectors.toList());

       for(Book book:booksByName){
           if(book.isAvailable())return true;
       }

       return false;
    }

    @Override
    public void deleteBook(Book book){
        bookRepository.delete(book);
    }

    @Override
    public Book getBookById(Long bookId){
        Optional<Book> op= bookRepository.findById(bookId);
        return op.get();
    }

    @Override
   public  Book getBook(String bookName){
       List<Book> bookList= bookRepository.findAll();
       System.out.println(bookList);
       List<Book> booksByName=bookList.stream().
               filter(b->b.getName().equals(bookName)).
               collect(Collectors.toList());

        System.out.println(booksByName);
       for(Book book:booksByName){
           if(book.isAvailable()){
               updateBook(book,false);
              return book;
           }
       }

       throw new RuntimeException("Book is Not Available");

   }

   @Override
   public  void updateBook(Book book, boolean b){
        book.setAvailable(b);
    }
}
