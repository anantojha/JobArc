package com.example.JobArc.Repository;

import com.example.JobArc.Entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    @Query("SELECT jobseekerId FROM JobApplication WHERE jobId = :idJob")
    List<Long> findAllApplicants(@Param("idJob") Long idJob);

    @Query("SELECT j.id FROM JobApplication j WHERE j.jobseekerId = :idJobseeker and j.jobId = :idJob")
    List<Long> ckeckIfApplicationExists(@Param("idJobseeker") Long idJobseeker, @Param("idJob") Long idJob);

    @Query("SELECT jobId FROM JobApplication WHERE jobseekerId = :idJobseeker")
    List<Long> findAllApplicantJobs(@Param("idJobseeker") Long idJobseeker);
}
