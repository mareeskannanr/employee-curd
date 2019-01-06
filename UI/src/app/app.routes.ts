import { Routes } from '@angular/router';

import { EmployeesListComponent } from './employees-list/employees-list.component';
import { EmployeesDetailsComponent } from './employees-details/employees-details.component';

export const routes: Routes = [
    {
        path: 'employee/:id',
        component: EmployeesDetailsComponent
    }, {
        path: '',
        component: EmployeesListComponent
    }
];