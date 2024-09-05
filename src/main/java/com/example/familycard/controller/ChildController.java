package com.example.familycard.controller;

import com.example.familycard.model.Child;
import com.example.familycard.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController 
@RequestMapping("/api/children") 
public class ChildController {
    private final ChildService childService;

    @Autowired 
    public ChildController(ChildService childService) { 
        this.childService = childService;
    }

    @GetMapping 
    public List<Child> getAllChildren() {
        return childService.findAll(); 
    }

    @GetMapping("/{id}")      
    public ResponseEntity<Child> getChildById(@PathVariable Long id) { 
        Optional<Child> child = childService.findById(id);
        return child.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Child createChild(@RequestBody Child child) {
        return childService.save(child);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Child> updateChild(@PathVariable Long id, @RequestBody Child updatedChild) {
        return childService.findById(id) 
                .map(existingChild -> { 
                    existingChild.setName(updatedChild.getName());
                    existingChild.setBalance(updatedChild.getBalance());
                    Child savedChild = childService.save(existingChild);
                    return ResponseEntity.ok(savedChild);
                }).orElseGet(() -> ResponseEntity.notFound().build()); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChild(@PathVariable Long id) {
        if (childService.findById(id).isPresent()) {
            childService.deleteById(id);
            return ResponseEntity.noContent().build(); 
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

