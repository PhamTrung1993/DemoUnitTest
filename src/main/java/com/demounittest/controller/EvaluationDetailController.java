package com.demounittest.controller;

import com.demounittest.model.EvaluationDetails;
import com.demounittest.service.evaluationdetails.IEvaluationDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
public class EvaluationDetailController {
    @Autowired
    private IEvaluationDetailService evaluationDetailService;

    @GetMapping("/evaluationDetails")
    public ResponseEntity<Iterable<EvaluationDetails>> getEvaluationDetails(){
        return new
                ResponseEntity<>(evaluationDetailService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/evaluationDetails")
    public ResponseEntity<EvaluationDetails> createEvaluationDetail(@RequestBody EvaluationDetails evaluationDetails){
        return new
                ResponseEntity<>(evaluationDetailService.save(evaluationDetails), HttpStatus.OK);
    }

    @PutMapping("/evaluationDetails/{id}")
    public ResponseEntity<EvaluationDetails> editEvaluationDetail(@RequestBody EvaluationDetails evaluationDetails, @PathVariable Long id){
        Optional<EvaluationDetails> evaluationDetailsOptional = evaluationDetailService.findById(id);
        if (!evaluationDetailsOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        evaluationDetails.setId(id);
        return new ResponseEntity<>(evaluationDetailService.save(evaluationDetails), HttpStatus.OK);
    }

    @DeleteMapping("/evaluationDetails/{id}")
    public ResponseEntity<EvaluationDetails> deleteEvaluation(@PathVariable Long id){
        Optional<EvaluationDetails> evaluationDetailsOptional = evaluationDetailService.findById(id);
        if (!evaluationDetailsOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        evaluationDetailService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
