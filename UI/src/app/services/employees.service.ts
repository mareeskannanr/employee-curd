import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EmployeesService {

  baseUrl = "http://localhost:8080/api/";

  data: any = "";

  constructor(private http:HttpClient) { }

  uploadCSV = fileData => this.http.post(this.baseUrl + 'upload', fileData, {responseType: "text"});

  downloadErrorFile = () => this.http.get(this.baseUrl + 'download', {responseType: "text"});

  saveEmployee = employee => this.http.post(this.baseUrl + 'employee', employee);

  getEmployees = () => this.http.get(this.baseUrl + 'employees');

}
