package uz.silver.appjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.silver.appjparelationships.entity.Student;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student,Integer> {
}
