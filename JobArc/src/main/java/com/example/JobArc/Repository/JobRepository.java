package com.example.JobArc.Repository;

import com.example.JobArc.Entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    @Query("SELECT j FROM Job j WHERE j.id IN :ids")
    List<Job> findAllEmployerJobs(@Param("ids") Collection<Long> idList);

    @Query("SELECT j FROM Job j ORDER BY j.createDate desc")
    List<Job> findRecentJobs();

    @Query("SELECT id FROM Job where description like %:keyword% " +
            "or title like %:keyword% or employerName like %:keyword% or location like %:keyword%")
    List<Long> searchForKeywordInJobs(String keyword);
}
