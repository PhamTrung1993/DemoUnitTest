package com.demounittest.controller;

import com.demounittest.model.Categories;
import com.demounittest.model.Outcomes;
import com.demounittest.service.categories.ICategoryService;
import com.demounittest.service.outcome.IOutComesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
public class OutComesController {
    @Autowired
    private IOutComesService outComesService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/outcomes")
    public ResponseEntity<Iterable<Outcomes>> getAllOutComes(){
        Iterable<Outcomes> outComes = outComesService.findAll();
        return new ResponseEntity<>(outComes, HttpStatus.OK);
    }

    @PostMapping("/outcomes")
    public ResponseEntity<Outcomes> createOutComes(@RequestBody Outcomes outComes){
        return new ResponseEntity<>(outComesService.save(outComes), HttpStatus.OK);
    }

    @GetMapping("/outcomes/{id}")
    public ResponseEntity<Outcomes> getOutComes(@PathVariable Long id){
        Optional<Outcomes> optionalOutComes = outComesService.findById(id);
        if (!optionalOutComes.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalOutComes.get(), HttpStatus.OK);
    }

    @DeleteMapping("/outcomes/{id}")
    public ResponseEntity<Outcomes> deleteOutComes(@PathVariable Long id){
        Optional<Outcomes> optionalOutComes = outComesService.findById(id);
        if (!optionalOutComes.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        outComesService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/outcomes/{id}")
    public ResponseEntity<Outcomes> editOutComes(@PathVariable Long id, @RequestBody Outcomes outComes){
        Optional<Outcomes> optionalOutComes = outComesService.findById(id);
        if (!optionalOutComes.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        outComes.setId(id);
        return new ResponseEntity<>(outComesService.save(outComes), HttpStatus.OK);
    }

    @GetMapping("/outcomes/name")
    public ResponseEntity<Outcomes> findByTitle(@RequestParam String name){
        Optional<Outcomes> optionalOutComes = outComesService.findByTitle(name);
        if (!optionalOutComes.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalOutComes.get(), HttpStatus.OK);
    }

    @GetMapping("/outcomes/{id}/categories")
    public ResponseEntity<Iterable<Categories>> findAllByOutComes(@PathVariable Long id){
        Optional<Outcomes> outcomes = outComesService.findById(id);
        if (!outcomes.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Iterable<Categories> categories = categoryService.findAllByOutComes(outcomes.get());
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
