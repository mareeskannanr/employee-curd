package com.rmk.employee.app.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rmk.employee.app.config.AppConfig;
import com.rmk.employee.app.exception.AppException;
import com.rmk.employee.app.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CommonUtils {

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private Environment env;

    public List<String> validateObject(Employee employee) {
        List<String> errorList = new ArrayList<>();
        Validator validator = appConfig.getValidator();

        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        violations.stream().forEach(item -> {
            errorList.add(item.getMessage());
        });

        return errorList;
    }

    public void writeErrorLog(List<String> errorList) throws Exception {

        String errorFilePath = getErrorFilePath();
        Path rootLocation = Paths.get(errorFilePath);

        if (!Files.exists(rootLocation)) {
            Files.createFile(rootLocation);
        }

        Files.write(rootLocation, String.join("\n", errorList).getBytes());
    }

    public String convertJsonToCSV(JsonNode employee) {
        String[] properties = new String[] { "name", "department", "designation", "salary", "joining_date" };
        List<String> tempEmployee = Arrays.stream(properties).map(item -> item = employee.has(item) && !employee.isNull() ? employee.get(item).asText() : "").collect(Collectors.toList());
        return String.join(",", tempEmployee);
    }

    public String getErrorFilePath() {
        return env.getProperty("error.file.path");
    }

}
