package com.example.familycard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.familycard.model.Child;
import com.example.familycard.repository.ChildRepository;    

@Service
public class ChildService {
    private final ChildRepository childRepository;

    @Autowired
    public ChildService(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    public List<Child> findAll() {
        return childRepository.findAll();
    }

    public Optional<Child> findById(Long id) {
        return childRepository.findById(id);
    }

    public Child save(Child child) {
        return childRepository.save(child);
    }

    public void deleteById(Long id) {
        childRepository.deleteById(id);
    }
}
