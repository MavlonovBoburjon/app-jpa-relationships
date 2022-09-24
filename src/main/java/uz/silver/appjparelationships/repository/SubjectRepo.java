package uz.silver.appjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.silver.appjparelationships.entity.Subject;

public interface SubjectRepo extends JpaRepository<Subject,Integer> {

    boolean existsByName(String name);
}
