package uk.ac.ebi.test.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.ebi.test.entities.Person;
import uk.ac.ebi.test.repositories.PersonRepository;

import java.net.URI;
import java.util.List;

@RestController
@Api(tags = "Person Entity")
@RequestMapping(path = "persons")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @ApiOperation(value = "Save a list of person entities")
    @RequestMapping(method = RequestMethod.POST, path = "listOfPersons", produces = "application/json")
    @ResponseBody
    public ResponseEntity search(@RequestBody List<Person> persons) {
        personRepository.save(persons);
        return ResponseEntity.created(URI.create("/persons")).build();
    }
}
