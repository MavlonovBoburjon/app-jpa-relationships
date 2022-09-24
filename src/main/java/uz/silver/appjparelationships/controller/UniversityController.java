package uz.silver.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.silver.appjparelationships.entity.Address;
import uz.silver.appjparelationships.entity.University;
import uz.silver.appjparelationships.model.UniversityDto;
import uz.silver.appjparelationships.repository.AddressRepo;
import uz.silver.appjparelationships.repository.UniversityRepo;

import java.util.List;
import java.util.Optional;

@RestController
public class UniversityController {

    @Autowired
    UniversityRepo universityRepo;
    @Autowired
    AddressRepo addressRepo;

    //CREATE
    @RequestMapping(value = "/university",method = RequestMethod.POST)
    public String addUniversity(@RequestBody UniversityDto universityDto){
        Address address=new Address(universityDto.getCity(),universityDto.getDistrict(),universityDto.getStreet());
        Address savedAddress = addressRepo.save(address);
        University university=new University();
        university.setName(universityDto.getName());
        university.setAddress(savedAddress);
        universityRepo.save(university);
        return "University Saved";
    }

    //READ
    @RequestMapping(value = "/university",method = RequestMethod.GET)
    public List<University> getUniversities(){
        List<University> universities=universityRepo.findAll();
        return universities;
    }

    //UPDATE
    @RequestMapping(value = "/university/{id}",method = RequestMethod.PUT)
    public String editUniversity(@PathVariable Integer id,@RequestBody UniversityDto universityDto){
        Optional<University> optionalUniversity = universityRepo.findById(id);
        if (optionalUniversity.isPresent()){
            University university=optionalUniversity.get();
            university.setName(universityDto.getName());
            Address address=university.getAddress();
            address.setCity(universityDto.getCity());
            address.setDistrict(universityDto.getDistrict());
            address.setStreet(universityDto.getStreet());
            addressRepo.save(address);
            universityRepo.save(university);
            return "University updated";
        }else {
            return "University Not Found";
        }
    }

    //DELETE
    @RequestMapping(value = "/university/{id}",method = RequestMethod.DELETE)
    public String deleteUniversity(@PathVariable Integer id){
        universityRepo.deleteById(id);
        return "university deleted";
    }

}
