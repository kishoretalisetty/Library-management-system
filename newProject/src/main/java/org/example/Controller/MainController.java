package org.example.Controller;

import org.example.Dtos.AddUserBookMappingDto;
import org.example.Dtos.BookRequestDto;
import org.example.Dtos.UserRequestDto;
import org.example.Services.BookService;
import org.example.Services.UserBookMappingService;
import org.example.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/")
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserBookMappingService userBookMappingService;


    //User CURD
    @PostMapping(path="/addUser") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestBody UserRequestDto req) {

        userService.addUser(req);

        return "User Saved";
    }

    @GetMapping(path = "/getUserById")
    public @ResponseBody UserRequestDto getUserById(Long userId){
       return  userService.getUserById(userId);
    }

    @DeleteMapping(path = "/deleteUser")
    public void deleteUser(@RequestBody UserRequestDto req){
        userService.deleteUser(req);
    }

    @DeleteMapping(path = "/deleteUserById")
    public void deleteUserById(Long userId){
        userService.deleteUserById(userId);
    }

    //Book Crud

    @PostMapping(path = "/addBook")
    public @ResponseBody String addNewBook(@RequestBody BookRequestDto bookRequestDto){
        bookService.addBook(bookRequestDto);
        return "Book Saved";
    }

    @GetMapping(path = "/isBookAvailable")
    public @ResponseBody boolean isAvailable(@RequestBody String bookName){
      return bookService.isAvailble(bookName);
    }
    //   void deleteBook(Book book);

   // Book getBook(String bookName);

   // void updateBook(Book book);

    @PostMapping(path = "/addUserBookMapping")
    public @ResponseBody String addUserBookMapping(@RequestBody AddUserBookMappingDto addUserBookMappingDto){
        userBookMappingService.addUserBookMappingReq(addUserBookMappingDto.getUserId(), addUserBookMappingDto.getBookName());
        return "UserBookMapping Saved";
    }

    @PutMapping(path = "/bookSubmission")
    public @ResponseBody String bookSubmission(@RequestBody Long userId, @RequestBody String bookName){
       return  userBookMappingService.UserBookSubmissionByName(userId, bookName);
    }


}
