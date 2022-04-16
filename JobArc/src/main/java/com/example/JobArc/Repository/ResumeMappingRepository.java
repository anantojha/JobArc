package com.example.JobArc.Repository;


import com.example.JobArc.Entity.Resume;
import com.example.JobArc.Entity.ResumeMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeMappingRepository extends JpaRepository<ResumeMapping, Long>{

}
