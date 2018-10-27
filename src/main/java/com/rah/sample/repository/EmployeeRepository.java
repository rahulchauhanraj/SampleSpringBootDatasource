package com.rah.sample.repository;

import com.rah.sample.model.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Employee SET status=1 WHERE status=2 ORDER BY id LIMIT 100")
    void updateEmployee();
}