import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './authentication/login/login.component';
import { RegisterComponent } from './authentication/register/register.component';

const routes: Routes = [

{ path: '', pathMatch: 'full', redirectTo: 'auth-login' },
{ path: 'auth-login', component: LoginComponent },
{ path: 'register', component: RegisterComponent },
{
  path: 'patients',

    loadChildren: () => import('./components/patient.module').then(m => m.PatientModule)
  }
  //{ path: 'patients/:id', component: PatientReaderComponent, canActivate: [AuthGuard] }

  ];




@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
