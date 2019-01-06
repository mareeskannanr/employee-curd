import { Component, OnInit } from '@angular/core';

import { EmployeesService } from '../services/employees.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employees-list',
  templateUrl: './employees-list.component.html',
  styleUrls: ['./employees-list.component.css']
})
export class EmployeesListComponent implements OnInit {

  upload = {
    file: null,
    fileData: null
  };

  modal = {
    title: "",
    body: ""
  };

  employeeArray:any = [];
  totalRecords : number;
  page: number = 1;
  $: any;

  constructor(private employeeService: EmployeesService, private router: Router) { 
    this.getEmployees();
  }

  ngOnInit() {}

  getFileData = event => this.upload.fileData = event.target.files[0];

  resetModal = () => this.upload = {
    file: null,
    fileData: null
  };

  uploadCSV = () => {
    this.modal = {
      "title": "CSV File Upload Status",
      "body": `<div class='alert alert-warning'>
                  <strong>File Upload is inprogress!</strong> Please Wait.
                </div>`
    };

    (<any>$('#uploadModal')).modal('hide');
    (<any>$('#responseModal')).modal('show');
    let form = new FormData();
    form.append("file", this.upload.fileData);
    this.employeeService.uploadCSV(form).subscribe(
      result => {
        this.modal.body = `<div class='alert alert-primary'>
        <strong>${result}</strong>
      </div>`;
        this.getEmployees();
      },
      err => {
        this.modal.title = "Error"
        this.modal.body = err.error;
      }
    );

  };

  downloadErrorFile = () => {
    this.employeeService.downloadErrorFile().subscribe(
      result => {
        let blob = new Blob([result], { type: 'text/csv' });
        let url= window.URL.createObjectURL(blob);
        let a: HTMLAnchorElement = document.createElement('a') as HTMLAnchorElement;

        a.href = url;
        a.download = "errors.csv";
        document.body.appendChild(a);
        a.click();        
    
        document.body.removeChild(a);
        URL.revokeObjectURL(url);
      },
      err => {
        console.log(err);
        this.modal.title = "Error"
        this.modal.body = err.error;
      }
    );
  };

  getEmployees = () => {
    this.employeeService.getEmployees().subscribe(
      result => {
        this.employeeArray = result;
        this.totalRecords = this.employeeArray.length;
      }, err => console.error(err)
    );
  };

  openEmployee = employee => {
    this.employeeService.data = employee;
    this.router.navigateByUrl("/employee/" + employee.id);
  };

}
