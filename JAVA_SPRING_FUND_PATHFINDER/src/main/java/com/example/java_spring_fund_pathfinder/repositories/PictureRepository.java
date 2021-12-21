package com.example.java_spring_fund_pathfinder.repositories;

import com.example.java_spring_fund_pathfinder.domain.entities.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
}
