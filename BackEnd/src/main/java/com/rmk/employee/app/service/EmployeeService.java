package com.rmk.employee.app.service;

import com.rmk.employee.app.model.Employee;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeService {

    void uploadFile(MultipartFile employeeFile) throws Exception;

    Resource downloadErrorFile() throws Exception;

    Employee save(Employee employee);

    Employee findById(Long id);

    List<Employee> getEmpolyees();

}