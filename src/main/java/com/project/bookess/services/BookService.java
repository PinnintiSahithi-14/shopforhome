package com.project.bookess.services;

import com.project.bookess.dto.ReadLaterDto;
import com.project.bookess.model.Book;
import com.project.bookess.model.LikedBook;
import com.project.bookess.model.ReadLater;
import com.project.bookess.repository.BookRepository;
import com.project.bookess.repository.LikedBookRepository;
import com.project.bookess.repository.ReadLaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    ReadLaterRepository readLaterRepository;
    @Autowired
    LikedBookRepository likedBookRepository;

    public List<Book> showAllBooks() {
        return bookRepository.findAll();
    }

    public boolean addBookToReadLater(ReadLaterDto readLaterDto) {
        Optional<Book> optBook = bookRepository.findById(readLaterDto.getBookId());
        if(optBook.isPresent()) {
            ReadLater rl = new ReadLater();
            rl.setBookId(readLaterDto.getBookId());
            rl.setUserId(readLaterDto.getUserId());
            readLaterRepository.save(rl);
            return true;
        }
        return false;
    }

    public boolean addBook(Book book) {
        Book b = bookRepository.save(book);
        return b.getId() > 0;
    }

    public boolean addBookToLikeSection(ReadLaterDto readLaterDto) {
        Optional<Book> optBook = bookRepository.findById(readLaterDto.getBookId());
        if(optBook.isPresent()) {
            LikedBook lb = new LikedBook();
            lb.setBookId(readLaterDto.getBookId());
            lb.setUserId(readLaterDto.getUserId());
            likedBookRepository.save(lb);
            return true;
        }
        return false;
    }

    public List<ReadLater> getAllReadLaterBook() {
        return readLaterRepository.findAll();
    }

    public List<LikedBook> getAllLikedBook() {
        return likedBookRepository.findAll();
    }
    public boolean updateBook(Book book) {
        Optional<Book> optBook = bookRepository.findById(book.getId());
        if(optBook.isPresent()) {
            Book u = bookRepository.save(book);
            return true;
        }
        return false;
    }

    public boolean deleteBook(int id) {
        Optional<Book> optBook = bookRepository.findById(id);
        if(optBook.isPresent()) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Book> getBookById(int id) {
        return bookRepository.findById(id);
    }
}
