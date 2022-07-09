package com.project.bookess.controller;

import com.project.bookess.dto.CustomResponse;
import com.project.bookess.model.Book;
import com.project.bookess.model.User;
import com.project.bookess.services.BookService;
import com.project.bookess.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class AdminController {

    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;

    @GetMapping("/api/admin/users")
    public ResponseEntity<List<User>> showAllUsers() {
        return new ResponseEntity<>(userService.findAllUser(),HttpStatus.OK);
    }
    @GetMapping("/api/admin/users/{userId}")
    public ResponseEntity<?> showUserById(@PathVariable int userId) {
        Optional<User> optUser = userService.getUserById(userId);
        if(optUser.isPresent()) {
            return new ResponseEntity<>(optUser.get(),HttpStatus.OK);
        }

        return new ResponseEntity<>(new CustomResponse(LocalDateTime.now(),"User not found!", HttpStatus.OK.value()),HttpStatus.OK);
    }

    @PutMapping("/api/admin/user")
    public ResponseEntity<CustomResponse> updateUser(@RequestBody User user) {
        boolean result = userService.updateUser(user);
        if(result) {
            return new ResponseEntity<>(new CustomResponse(LocalDateTime.now(),"User updated Successfully!", HttpStatus.OK.value()),HttpStatus.OK);
        }

        return new ResponseEntity<>(new CustomResponse(LocalDateTime.now(),"Error while updating! try after some time!", HttpStatus.OK.value()),HttpStatus.OK);
    }

    @DeleteMapping("/api/admin/user/{userId}")
    public ResponseEntity<CustomResponse> deleteUser(@PathVariable int userId) {
        boolean result = userService.deleteUser(userId);
        if(result) {
            return new ResponseEntity<>(new CustomResponse(LocalDateTime.now(),"User deleted Successfully!", HttpStatus.OK.value()),HttpStatus.OK);
        }

        return new ResponseEntity<>(new CustomResponse(LocalDateTime.now(),"Error while deleting! try after some time!", HttpStatus.OK.value()),HttpStatus.OK);
    }

    @PostMapping("/api/admin/book")
    public ResponseEntity<CustomResponse> addBook(@RequestBody Book book) {
        boolean result = bookService.addBook(book);
        if(result) {
            return new ResponseEntity<>(new CustomResponse(LocalDateTime.now(),"Book added Successfully!", HttpStatus.OK.value()),HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomResponse(LocalDateTime.now(),"Error while adding! try after some time!", HttpStatus.OK.value()),HttpStatus.OK);
    }
    @PutMapping("/api/admin/book")
    public ResponseEntity<CustomResponse> updateBook(@RequestBody Book book) {
        boolean result = bookService.updateBook(book);
        if(result) {
            return new ResponseEntity<>(new CustomResponse(LocalDateTime.now(),"Book updated Successfully!", HttpStatus.OK.value()),HttpStatus.OK);
        }

        return new ResponseEntity<>(new CustomResponse(LocalDateTime.now(),"Error while updating! try after some time!", HttpStatus.OK.value()),HttpStatus.OK);
    }

    @DeleteMapping("/api/admin/book/{bookId}")
    public ResponseEntity<CustomResponse> deleteBook(@PathVariable int bookId) {
        boolean result = userService.deleteUser(bookId);
        if(result) {
            return new ResponseEntity<>(new CustomResponse(LocalDateTime.now(),"Book deleted Successfully!", HttpStatus.OK.value()),HttpStatus.OK);
        }

        return new ResponseEntity<>(new CustomResponse(LocalDateTime.now(),"Error while deleting! try after some time!", HttpStatus.OK.value()),HttpStatus.OK);
    }
}
