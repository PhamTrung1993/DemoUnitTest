package com.demounittest.controller;

import com.demounittest.model.EvaluationDetails;
import com.demounittest.model.Evaluations;
import com.demounittest.service.evaluation.IEvaluationService;
import com.demounittest.service.evaluationdetails.IEvaluationDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class EvaluationController {
    @Autowired
    private IEvaluationService evaluationService;

    @Autowired
    private IEvaluationDetailService evaluationDetailService;

    @GetMapping("/evaluations")
    public ResponseEntity<Iterable<Evaluations>> getEvaluations(){
        return new ResponseEntity<>(evaluationService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/evaluations")
    public ResponseEntity<Evaluations> createEvaluation(@RequestBody Evaluations evaluations){
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        evaluations.setCreateDate(date);
        return new ResponseEntity<>(evaluationService.save(evaluations), HttpStatus.OK);
    }

    @GetMapping("/evaluations/{id}")
    public ResponseEntity<Evaluations> getEvaluation(@PathVariable Long id){
        Optional<Evaluations> evaluations = evaluationService.findById(id);
        if (!evaluations.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(evaluations.get(), HttpStatus.OK);
    }

    @PutMapping("/evaluations/{id}")
    public ResponseEntity<Evaluations> editEvaluation(@RequestBody Evaluations evaluations, @PathVariable Long id){
        Optional<Evaluations> evaluationsOptional = evaluationService.findById(id);
        if (!evaluationsOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        evaluations.setId(id);
        evaluations.setClasses(evaluationsOptional.get().getClasses());
        evaluations.setCoach(evaluationsOptional.get().getCoach());
        evaluations.setStudent(evaluationsOptional.get().getStudent());
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        evaluations.setCreateDate(date);
        return new ResponseEntity<>(evaluationService.save(evaluations), HttpStatus.OK);
    }

    @DeleteMapping("/evaluations/{id}")
    public ResponseEntity<Evaluations> deleteEvaluation(@PathVariable Long id){
        Optional<Evaluations> evaluations = evaluationService.findById(id);
        if (!evaluations.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        evaluationService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/evaluations/{id}/details")
    public ResponseEntity<Iterable<EvaluationDetails>> findAllByEvaluation(@PathVariable Long id){
        Optional<Evaluations> evaluations = evaluationService.findById(id);
        if(!evaluations.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Evaluations evaluations1 = evaluations.get();
        Iterable<EvaluationDetails> evaluationDetails = evaluationDetailService.findAllByEvaluation(evaluations1);
        return new ResponseEntity<>(evaluationDetails, HttpStatus.OK);
    }
}
