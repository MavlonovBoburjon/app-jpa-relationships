package uz.silver.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uz.silver.appjparelationships.entity.Subject;
import uz.silver.appjparelationships.repository.SubjectRepo;

import java.util.List;

@RestController
public class SubjectController {

    @Autowired
    SubjectRepo subjectRepo;

    @RequestMapping(value = "/subject",method =RequestMethod.POST)
    public String addSubject(@RequestBody Subject subject){
        boolean existsByName = subjectRepo.existsByName(subject.getName());
        if(existsByName){
            return "This Subject Already exists";
        }
        subjectRepo.save(subject);
        return "Subject Added";
    }

    @RequestMapping(value = "/subject",method = RequestMethod.GET)
    public List<Subject> getSubject(){
        List<Subject> subjects=subjectRepo.findAll();
        return subjects;
    }
}
