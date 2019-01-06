package com.rmk.employee.app.controller;

import com.rmk.employee.app.exception.AppException;
import com.rmk.employee.app.model.Employee;
import com.rmk.employee.app.service.EmployeeService;
import com.rmk.employee.app.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class AppRestCtrl {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/upload")
    public ResponseEntity uploadEmployeeFile(@RequestParam("file") MultipartFile employeeCSV) throws Exception {

        if(!employeeCSV.getOriginalFilename().endsWith(".csv")) {
            throw new AppException(AppConstants.INVALID_FILE);
        }

        if(employeeCSV.isEmpty()) {
            throw new AppException(AppConstants.EMPTY_FILE);
        }

        employeeService.uploadFile(employeeCSV);

        return ResponseEntity.ok(AppConstants.FILE_UPLOAD_MSG);
    }

    @GetMapping("/download")
    public ResponseEntity downloadErrorFile() throws Exception {
        Resource errorFile = employeeService.downloadErrorFile();
        System.out.println(errorFile.getFilename());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + errorFile.getFilename())
                .body(errorFile);
    }

    @PostMapping("/employee")
    public ResponseEntity saveEmployee(@RequestBody Employee employee) throws AppException {
        if(employee.getId() == null) {
            throw new AppException(AppConstants.INVALID_EMP_ID_MSG);
        }

        Employee previousEmployee = employeeService.findById(employee.getId());
        if(previousEmployee == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(employeeService.save(employee));
    }

    @GetMapping("/employees")
    public ResponseEntity getEmployees() {
        return ResponseEntity.ok(employeeService.getEmpolyees());
    }

}
