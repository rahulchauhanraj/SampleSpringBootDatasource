package com.rah.sample;

import com.rah.sample.boot.Application;
import com.rah.sample.model.Employee;
import com.rah.sample.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class RepositoryTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    EmployeeRepository repository;

    @Test
    public void testMeUpdate() {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        for (int i = 0; i<120; i++) {
            final int count = i;
            threadPool.submit(() -> update(count));
        }

        threadPool.shutdown();
        int count = 1;
        try {
            while (!threadPool.isTerminated()) {
                System.out.println("Awaiting count " + count);
                threadPool.awaitTermination(1000, TimeUnit.MILLISECONDS);
                count++;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMeCreate() {

        ExecutorService threadPool = Executors.newFixedThreadPool(4);

        for (int i = 1; i<2; i++) {
            Employee e = new Employee();
            e.setFirstname("test" + i);
            e.setStatus(1);
            threadPool.submit(() -> repository.save(e));
        }

        threadPool.shutdown();
        int count = 1;
        try {
            while (!threadPool.isTerminated()) {
                System.out.println("Awaiting count " + count);
                threadPool.awaitTermination(1000, TimeUnit.MILLISECONDS);
                count++;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void update (int count) {
        repository.updateEmployee();
        System.out.println("Count :" + count);
    }

}
