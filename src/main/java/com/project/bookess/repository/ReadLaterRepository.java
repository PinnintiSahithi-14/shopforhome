package com.project.bookess.repository;

import com.project.bookess.model.ReadLater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadLaterRepository extends JpaRepository<ReadLater,Integer> {
}
