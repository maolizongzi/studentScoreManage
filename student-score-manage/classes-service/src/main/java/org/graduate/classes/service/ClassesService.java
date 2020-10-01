package org.graduate.classes.service;

import org.graduate.classes.repository.model.Classes;
import org.springframework.stereotype.Service;

@Service
public interface ClassesService {
    void addClasses(Classes classes);
}
