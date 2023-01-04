package com.demounittest.controller;

import com.demounittest.model.*;
import com.demounittest.service.classes.ClassesService;
import com.demounittest.service.coach.ICoachService;
import com.demounittest.service.evaluation.IEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin("*")
public class CoachController {
    @Autowired
    private ICoachService coachService;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClassesService classesService;

    @Autowired
    private IEvaluationService evaluationService;


    @GetMapping("/coaches")
    public ResponseEntity<Iterable<Coach>> getAllCoaches() {
        return new ResponseEntity<>(coachService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/coaches")
    public ResponseEntity<Coach> createNewCoach(@RequestBody Coach coach) {
        String defaultPassword = "123456@Abc";
        String encodePassword = NoOpPasswordEncoder.getInstance().encode(defaultPassword);
        coach.setPassword(encodePassword);
        Set<Roles> roles = new HashSet<>();
        roles.add(new Roles(2L, RoleName.ROLE_COACH.toString()));
        coach.setRoles(roles);
        return new ResponseEntity<>(coachService.save(coach), HttpStatus.OK);
    }

    @GetMapping("/coaches/{id}")
    public ResponseEntity<Coach> getCoach(@PathVariable Long id) {
        Optional<Coach> coachOptional = coachService.findById(id);
        return coachOptional.map(coach -> new ResponseEntity<>(coach, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/coaches/{id}")
    public ResponseEntity<Coach> updateCoachInfo(@PathVariable Long id, @RequestBody Coach coach) {
        Optional<Coach> coachOptional = coachService.findById(id);
        if (!coachOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String email = coachOptional.get().getEmail();
        String password = coachOptional.get().getPassword();
        String coachId = coachOptional.get().getCoachId();
        coach.setId(id);
        coach.setEmail(email);
        coach.setPassword(password);
        coach.setCoachId(coachId);
        Set<Roles> roles = new HashSet<>();
        roles.add(new Roles(2L, RoleName.ROLE_COACH.toString()));
        coach.setRoles(roles);
        coachService.save(coach);
        return new ResponseEntity<>(coach, HttpStatus.OK);
    }

    @DeleteMapping("/coaches/{id}")
    public ResponseEntity<Coach> deleteCoach(@PathVariable Long id) {
        Optional<Coach> coachOptional = coachService.findById(id);
        if (!coachOptional.isPresent()) {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        coachService.remove(id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/coaches/{id}/classes")
    public ResponseEntity<Iterable<Classes>> listClassOfCoach(@PathVariable Long id) {
        Optional<Coach> coach = coachService.findById(id);
        if (!coach.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Iterable<Classes> classes = classesService.findAllByCoach(coach.get());
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @PutMapping("/coaches/change-password/{id}")
    public ResponseEntity<Coach> changePassword(@PathVariable Long id, @RequestBody Coach coach) {
        Optional<Coach> coachOptional = coachService.findById(id);
        if (!coachOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String newPassword = NoOpPasswordEncoder.getInstance().encode(coach.getPassword());
        coach.setPassword(newPassword);
        coach.setId(id);
        coach.setRoles(coachOptional.get().getRoles());
        coachService.save(coach);
        return new ResponseEntity<>(coach, HttpStatus.OK);
    }

    @GetMapping("/coaches/{id}/evaluations")
    public ResponseEntity<Iterable<Evaluations>> findAllByCoach(@PathVariable Long id){
        Optional<Coach> coach = coachService.findById(id);
        if(!coach.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(evaluationService.findAllByCoach(coach.get()), HttpStatus.OK);
    }
}
