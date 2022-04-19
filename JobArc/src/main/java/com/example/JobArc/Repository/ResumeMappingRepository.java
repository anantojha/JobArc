package com.example.JobArc.Repository;

import com.example.JobArc.Entity.ResumeMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ResumeMappingRepository extends JpaRepository<ResumeMapping, Long>{

    @Query("SELECT resumeId FROM ResumeMapping WHERE jobseekerId = id")
    Long findJobseekerResume(@Param("id") Long id);

    @Query("SELECT resumeId FROM ResumeMapping  WHERE jobseekerId IN :ids")
    List<Long> findAllResumes(@Param("ids") Collection<Long> idList);


}
