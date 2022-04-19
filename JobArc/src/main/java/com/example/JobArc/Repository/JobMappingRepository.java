package com.example.JobArc.Repository;

import com.example.JobArc.Entity.JobMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JobMappingRepository extends JpaRepository<JobMapping, Long> {

    @Query("SELECT jobId FROM JobMapping WHERE employerId = id")
    List<Long> findAllEmployerJobs(@Param("id") Long id);
}
