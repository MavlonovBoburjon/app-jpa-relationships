package uz.silver.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.silver.appjparelationships.entity.Faculty;
import uz.silver.appjparelationships.entity.University;
import uz.silver.appjparelationships.model.FacultyDto;
import uz.silver.appjparelationships.repository.FacultyRepo;
import uz.silver.appjparelationships.repository.UniversityRepo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    @Autowired
    FacultyRepo facultyRepo;
    @Autowired
    UniversityRepo universityRepo;

    @PostMapping
    public String addFaculty(@RequestBody FacultyDto facultyDto){
        boolean exists = facultyRepo.existsByNameAndUniversityId(facultyDto.getName(), facultyDto.getUniversityId());
        if (exists){
            return "This University such faculty exists";
        }
        Faculty faculty=new Faculty();
        faculty.setName(facultyDto.getName());
        Optional<University> optionalUniversity = universityRepo.findById(facultyDto.getUniversityId());
        if (!optionalUniversity.isPresent()){
            return "University Not Found";
        }
        faculty.setUniversity(optionalUniversity.get());
        facultyRepo.save(faculty);
        return "Faculty Saved";
    }

    @GetMapping("/byUniversityId/{universityId}")
    public List<Faculty> getFacultiesByUniversityId(@PathVariable Integer universityId){
        List<Faculty> allByUniversityId = facultyRepo.findAllByUniversityId(universityId);
        return allByUniversityId;
    }

    @DeleteMapping("/{id}")
    public String deleteFaculty(@PathVariable Integer id){
        try {
            facultyRepo.deleteById(id);
            return "Faculty Deleted";
        }catch (Exception e){
            return "Faculty Not Found";
        }
    }
}
