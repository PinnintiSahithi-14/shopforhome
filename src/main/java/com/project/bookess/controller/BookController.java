package com.project.bookess.controller;

import com.project.bookess.dto.CustomResponse;
import com.project.bookess.dto.ReadLaterDto;
import com.project.bookess.model.Book;
import com.project.bookess.model.LikedBook;
import com.project.bookess.model.ReadLater;
import com.project.bookess.services.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/api/books")
    public List<Book> showAllBooks() {
        return bookService.showAllBooks();
    }

    @GetMapping("/api/books/{bookId}")
    public ResponseEntity<?> showBookById(@PathVariable int bookId) {
        Optional<Book> optBook = bookService.getBookById(bookId);
        if(optBook.isPresent()) {
            return new ResponseEntity<>(optBook.get(),HttpStatus.OK);
        }

        return new ResponseEntity<>(new CustomResponse(LocalDateTime.now(),"Book not found!", HttpStatus.OK.value()),HttpStatus.OK);
    }

    @PostMapping("/api/user/books/read-later")
    public ResponseEntity<CustomResponse> addBookToReadLater(@RequestBody ReadLaterDto readLaterDto) {
        boolean result = bookService.addBookToReadLater(readLaterDto);
        if(result) {
            return new ResponseEntity<>(new CustomResponse(LocalDateTime.now(),"Book added to read later Successfully!", HttpStatus.OK.value()),HttpStatus.OK);
        }

        return new ResponseEntity<>(new CustomResponse(LocalDateTime.now(),"Error while adding! try after some time!", HttpStatus.OK.value()),HttpStatus.OK);
    }

    @PostMapping("/api/user/books/liked-book")
    public ResponseEntity<CustomResponse> addBookToLikeSection(@RequestBody ReadLaterDto readLaterDto) {
        boolean result = bookService.addBookToLikeSection(readLaterDto);
        if(result) {
            return new ResponseEntity<>(new CustomResponse(LocalDateTime.now(),"Book added to like section Successfully!", HttpStatus.OK.value()),HttpStatus.OK);
        }

        return new ResponseEntity<>(new CustomResponse(LocalDateTime.now(),"Error while adding! try after some time!", HttpStatus.OK.value()),HttpStatus.OK);
    }

    @GetMapping("/api/user/books/read-later")
    public ResponseEntity<List<ReadLater>> showReadLaterBooks() {
        return new ResponseEntity<>(bookService.getAllReadLaterBook(),HttpStatus.OK);
    }

    @GetMapping("/api/user/books/liked-book")
    public ResponseEntity<List<LikedBook>> showLikedBooks() {
        return new ResponseEntity<>(bookService.getAllLikedBook(),HttpStatus.OK);
    }
}
