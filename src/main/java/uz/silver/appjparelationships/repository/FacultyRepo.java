package uz.silver.appjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.silver.appjparelationships.entity.Faculty;

import java.util.List;

public interface FacultyRepo extends JpaRepository<Faculty,Integer> {
    boolean existsByNameAndUniversityId(String name,Integer universityId);

    List<Faculty> findAllByUniversityId(Integer university_id);
}
