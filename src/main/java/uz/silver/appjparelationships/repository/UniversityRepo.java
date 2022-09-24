package uz.silver.appjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.silver.appjparelationships.entity.University;

@Repository
public interface UniversityRepo extends JpaRepository<University,Integer> {
}
