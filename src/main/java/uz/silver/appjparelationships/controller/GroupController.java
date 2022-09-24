package uz.silver.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.silver.appjparelationships.entity.Faculty;
import uz.silver.appjparelationships.entity.Group;
import uz.silver.appjparelationships.model.GroupDto;
import uz.silver.appjparelationships.repository.FacultyRepo;
import uz.silver.appjparelationships.repository.GroupRepo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupRepo groupRepo;

    @Autowired
    FacultyRepo facultyRepo;

    @GetMapping
    public List<Group> getGroups() {
        return groupRepo.findAll();
    }

    @GetMapping("/byUniversityId/{universityId}")
    public List<Group> getGroupByUniversityId(@PathVariable Integer universityId) {
        return groupRepo.findAllByFaculty_University_Id(universityId);
    }

    @PostMapping
    public String addGroup(@RequestBody GroupDto groupDto) {
        Group group=new Group();
        Faculty faculty=new Faculty();
        group.setName(groupDto.getName());
        Optional<Faculty> optionalFaculty = facultyRepo.findById(groupDto.getFacultyId());
        if(!optionalFaculty.isPresent())
            return "Such faculty Not Found";
        group.setFaculty(optionalFaculty.get());
        groupRepo.save(group);
        return "Group Saved";
    }
}
