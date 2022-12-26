package com.demounittest.service.student;

import com.demounittest.model.Classes;
import com.demounittest.model.Student;
import com.demounittest.service.GeneralService;

public interface IStudentService extends GeneralService<Student> {
    Iterable<Student> findAllByClasses(Classes classes);
}
