package com.demounittest.controller;


import com.demounittest.model.Evaluations;
import com.demounittest.model.Templates;
import com.demounittest.service.evaluation.IEvaluationService;
import com.demounittest.service.template.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
public class TemplateController {
    @Autowired
    private ITemplateService templateService;

    @Autowired
    private IEvaluationService evaluationService;

    @GetMapping("/templates")
    public ResponseEntity<Iterable<Templates>> getAllTemplates(){
        return new ResponseEntity<>(templateService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/templates")
    public ResponseEntity<Templates> createTemplate(@RequestBody Templates templates){
        return new ResponseEntity<>(templateService.save(templates), HttpStatus.OK);
    }

    @PutMapping("/templates/{id}")
    public ResponseEntity<Templates> editTemplate(@RequestBody Templates templates, @RequestParam Long id){
        Optional<Templates> optionalTemplates = templateService.findById(id);
        if (!optionalTemplates.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        templates.setId(id);
        templateService.save(templates);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/templates/{id}")
    public ResponseEntity<Templates> getTemplate(@PathVariable Long id){
        Optional<Templates> templatesOptional = templateService.findById(id);
        return templatesOptional.map(templates -> new ResponseEntity<>(templates, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/templates/{id}")
    public ResponseEntity<Templates> deleteTemplate(@PathVariable Long id){
        Optional<Templates> templatesOptional = templateService.findById(id);
        if (!templatesOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        templateService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/templates/{id}/evaluations")
    public ResponseEntity<Iterable<Evaluations>> findAllEvaluationByTemplate(@PathVariable Long id){
        Optional<Templates> templates = templateService.findById(id);
        if (!templates.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(evaluationService.findAllByTemplates(templates.get()), HttpStatus.OK);
    }
}
