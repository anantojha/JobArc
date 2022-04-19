package com.example.JobArc.Repository;

import com.example.JobArc.Entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    @Query("SELECT jobseekerId FROM JobApplication WHERE jobId = jobId")
    List<Long> findAllApplicants(@Param("jobId") Long jobId);

    @Query("SELECT id FROM JobApplication WHERE jobseekerId = jobseekerId and jobId = jobId")
    List<Long> ckeckIfApplicationExists(@Param("jobseekerId") Long jobseekerId, @Param("jobId") Long jobId);

    @Query("SELECT jobId FROM JobApplication WHERE jobseekerId = jobseekerId")
    List<Long> findAllApplicantJobs(@Param("jobseekerId") Long jobseekerId);
}
