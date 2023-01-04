package com.demounittest.controller;

import com.demounittest.model.Classes;
import com.demounittest.model.Programs;
import com.demounittest.service.classes.IClassesService;
import com.demounittest.service.programs.IProgramsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
public class ProgramController {
    @Autowired
    private IProgramsService programsService;

    @Autowired
    private IClassesService classesService;

    @GetMapping("/programs")
    public ResponseEntity<Iterable<Programs>> getAllPrograms(){
        return new ResponseEntity<>(programsService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/programs")
    public ResponseEntity<Programs> createNewProgram(@RequestBody Programs programs){
        return new ResponseEntity<>(programsService.save(programs), HttpStatus.OK);
    }

    @GetMapping("/programs/name")
    public ResponseEntity<Programs> findByNameProgram(@RequestParam String name){
        Optional<Programs> programsOptional = programsService.findByName(name);
        if(!programsOptional.isPresent()){
            return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(programsOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/programs/{id}")
    public ResponseEntity<Programs> getPrograms(@PathVariable Long id){
        Optional<Programs> optionalPrograms = programsService.findById(id);
        if (!optionalPrograms.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalPrograms.get(), HttpStatus.OK);
    }

    @PutMapping("/programs/{id}")
    public ResponseEntity<Programs> editPrograms(@PathVariable Long id, @RequestBody Programs programs){
        Optional<Programs> optionalPrograms = programsService.findById(id);
        if (!optionalPrograms.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        programs.setId(id);
        return new ResponseEntity<>(programsService.save(programs), HttpStatus.OK);
    }

    @DeleteMapping("/programs/{id}")
    public ResponseEntity<Programs> deletePrograms(@PathVariable Long id){
        Optional<Programs> optionalPrograms = programsService.findById(id);
        if (!optionalPrograms.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        programsService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/programs/{id}/classes")
    public ResponseEntity<Iterable<Classes>> findClassByProgram(@PathVariable Long id){
        Optional<Programs> optionalPrograms = programsService.findById(id);
        if (!optionalPrograms.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Iterable<Classes> classes= classesService.findAllByPrograms(optionalPrograms.get());
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }
}
