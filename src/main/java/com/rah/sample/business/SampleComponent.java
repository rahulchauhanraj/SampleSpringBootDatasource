package com.rah.sample.business;

import com.rah.sample.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Component
public class SampleComponent {

    @Autowired
    DataSource dataSource;

    @Autowired
    EmployeeRepository repository;

    @PostConstruct
    public void onLoad () {

    }
}
