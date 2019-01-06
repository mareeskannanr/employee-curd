package com.rmk.employee.app.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rmk.employee.app.exception.AppException;
import com.rmk.employee.app.model.Employee;
import com.rmk.employee.app.repository.EmployeeRepository;
import com.rmk.employee.app.utils.CommonUtils;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CommonUtils commonUtils;

    @Override
    public void uploadFile(MultipartFile employeeFile) throws Exception {
        CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();

        List employeeList = new CsvMapper().readerFor(ObjectNode.class)
                .with(csvSchema.withColumnSeparator(CsvSchema.DEFAULT_COLUMN_SEPARATOR))
                .readValues(employeeFile.getInputStream()).readAll();

        List<String> errorList = new ArrayList<>();

        employeeList.forEach(item -> {
            try {
                Employee employee = new ObjectMapper().readValue(item.toString(), Employee.class);
                save(employee);
            } catch (AppException | IOException e) {
                //e.printStackTrace();
                errorList.add(commonUtils.convertJsonToCSV((JsonNode) item));
            }
        });

        commonUtils.writeErrorLog(errorList);
    }

    @Override
    public Resource downloadErrorFile() throws Exception {
        Path errorFilePath = Paths.get(commonUtils.getErrorFilePath());
        Resource resource = new UrlResource(errorFilePath.toUri());
        return resource;
    }

    @Override
    public Employee save(Employee employee) throws AppException {
        List<String> errorList = commonUtils.validateObject(employee);
        if (!errorList.isEmpty()) {
            throw new AppException(String.join(",\n", errorList));
        }

        return employeeRepository.save(employee);
    }

    @Override
    public Employee findById(Long id) {
        Optional<Employee> result =  employeeRepository.findById(id);
        Employee employee = null;
        if(result.isPresent()) {
            employee = result.get();
        }

        return employee;
    }

    @Override
    public List<Employee> getEmpolyees() {
        return employeeRepository.findAll();
    }

}