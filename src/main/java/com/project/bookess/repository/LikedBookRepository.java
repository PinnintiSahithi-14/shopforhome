package com.project.bookess.repository;

import com.project.bookess.model.LikedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikedBookRepository extends JpaRepository<LikedBook,Integer> {
}
