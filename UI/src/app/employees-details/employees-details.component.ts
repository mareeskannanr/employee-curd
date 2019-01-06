import { Component, OnInit } from '@angular/core';
import { EmployeesService } from '../services/employees.service';

@Component({
  selector: 'app-employees-details',
  templateUrl: './employees-details.component.html',
  styleUrls: ['./employees-details.component.css']
})
export class EmployeesDetailsComponent implements OnInit {

  employee:any = {};

  modal = {
    title: "",
    body: ""
  };

  designationArray = ["Developer", "Senior Developer", "Manager", "Team Lead", "VP", "CEO"];

  constructor(private employeeService: EmployeesService) { 
    if(employeeService.data) {
      this.employee = employeeService.data;
      employeeService.data = "";
    }
  }

  ngOnInit() {}

  saveEmployee = () => this.employeeService.saveEmployee(this.employee).subscribe(
    result => {
      this.modal = {
        title: "Success",
        body: "Employee Information Updated Successfully!"
      };
      (<any>$('#responseModal')).modal('show');
    }, err => {
      if(err.status === 500) {
        this.modal = {
          title: "Failure",
          body: "Sorry something went wrong!"
        };
        (<any>$('#responseModal')).modal('show');
      } else {
        console.error(err);
      }
    }
  );

}
