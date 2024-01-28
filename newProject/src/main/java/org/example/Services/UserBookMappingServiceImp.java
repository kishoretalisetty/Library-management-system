package org.example.Services;

import org.example.Entities.Book;
import org.example.Entities.User;
import org.example.Entities.UserBookMapping;
import org.example.Repositorys.UserBookMappingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.concurrent.TimeUnit.DAYS;

@Service
public class UserBookMappingServiceImp implements UserBookMappingService{

    private int fine = 20;
    @Autowired
    private UserBookMappingRepository userBookMappingRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    @Override
    public void addUserBookMappingReq(Long userId, String bookName){
        User user=new ModelMapper().map(userService.getUserById(userId),User.class);
        Book book=bookService.getBook(bookName);

        UserBookMapping userBookMapping=new UserBookMapping();

        userBookMapping.setBookId(book.getId());
        userBookMapping.setUserId(user.getId());

        userBookMapping.setCreatedDate(Date.valueOf(LocalDate.now()));
        userBookMapping.setUpdatedDate(Date.valueOf(LocalDate.now()));
        userBookMapping.setStartDate(Date.valueOf(LocalDate.now()));
        userBookMapping.setEndDate(Date.valueOf(LocalDate.now().plusDays(10)));
        userBookMapping.setFine((long) fine);

     userBookMappingRepository.save(userBookMapping);

    }


    @Override
    public String UserBookSubmissionByName(Long userId, String bookName){
        List<UserBookMapping> userBookMappingList =userBookMappingRepository.findAll();

        List<UserBookMapping> userList=userBookMappingList.stream().
                filter(m->m.getUserId()==userId).collect(Collectors.toList());

        List<UserBookMapping> userbooksList=userList.stream().
                filter(m->m.getSubmissionDate()==null).collect(Collectors.toList());

        long fineForTheBook=0;

        for(UserBookMapping userBookMapping:userbooksList){
            Book book=bookService.getBookById(userBookMapping.getBookId());
            if(book.getName().equals(bookName)){
                bookService.updateBook(book,true);
                fineForTheBook=calculateFine(userBookMapping);
            }
        }
        return "User need to pay the fine :- "+fineForTheBook;


    }

    public long calculateFine(UserBookMapping userBookMapping){
         LocalDate endDate=LocalDate.ofInstant(userBookMapping.getEndDate().toInstant(),
                 ZoneId.systemDefault());
         long numOfDays=0;
         if(LocalDate.now().isAfter(endDate)){
             numOfDays= ChronoUnit.DAYS.between(endDate, LocalDate.now());
         }
         userBookMapping.setSubmissionDate(Date.valueOf(LocalDate.now()));
         userBookMappingRepository.save(userBookMapping);

         return (numOfDays*userBookMapping.getFine());
    }

//    public int nowManyBooksHoldByUser(Long userId){
//
//    }
}
