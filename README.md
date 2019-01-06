# employee-curd
Employee CURD operation using Spring Boot and Angular6

Folder -> Details
------------------
BackEnd --> Spring-Boot implementation<br>
UI --> Angular UI code<br>
ScreenShots --> Screenshots of backend and ui results<br>

--------------------------------------------------------------------------------------------------------------------
UI Task (Angular-6)
--------------------------------------------------------------------------------------------------------------------
1. Create an UI which allow to upload csv file containing employee information<br>
     a. Employee csv contains the following columns: name, department, designation, salary, joining date
2. Show the uploaded Employee information in a table.
3. Table should have pagination capabilities.
4. Any updates in the employee information should be reflected when refreshing the table or page.
5. Provide link to download the file with error records

--------------------------------------------------------------------------------------------------------------------
Server API Task
--------------------------------------------------------------------------------------------------------------------
1. Expose service to consume uploaded file.
2. Validation Check Below
3. Service to update the employee information

--------------------------------------------------------------------------------------------------------------------
Validation
--------------------------------------------------------------------------------------------------------------------
* Beside basic validation for the uploaded file.
* Following are the Validation for each field:<br>
     name -> can only contain alphabets<br>
     department -> alphanumeric with -_* as special characters<br>
     designation -> Developer, Senior Developer, Manager, Team Lead, VP, CEO<br>
     salary -> can only contain Numeric value<br>
     joining date -> yyyy-MM-dd format
