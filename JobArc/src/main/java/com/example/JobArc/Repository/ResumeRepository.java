package com.example.JobArc.Repository;

import com.example.JobArc.Entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long>{
    @Query("SELECT r FROM Resume r WHERE r.id = :id")
    Resume getResumeFromId(@Param("id") Long id);
}
