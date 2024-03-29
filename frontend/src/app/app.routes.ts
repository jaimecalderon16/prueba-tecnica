import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';

export const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  {
    path: "home",
    component: LoginComponent
  },
  {
    path: "dashboard",
    component: DashboardComponent
  },
];
