package com.hx.futrues.service.impl;

import com.hx.futrues.entity.Teacher;
import com.hx.futrues.repository.TeacherRepository;
import com.hx.futrues.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements ITeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<Teacher> getTeacherList() {
        return teacherRepository.findAll();
    }
}
