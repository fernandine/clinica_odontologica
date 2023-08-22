import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './authentication/login/login.component';
import { RegisterComponent } from './authentication/register/register.component';
import { RecoverPasswordComponent } from './authentication/recover-password/recover-password.component';
import { RecoverSuccessComponent } from './authentication/recover-success/recover-success.component';
import { NewPasswordComponent } from './authentication/new-password/new-password.component';

const routes: Routes = [

{ path: '', pathMatch: 'full', redirectTo: 'auth-login' },
{ path: 'auth-login', component: LoginComponent },
{ path: 'register', component: RegisterComponent },
{ path: 'recover-password', component: RecoverPasswordComponent },
{ path: 'recover-password/:token', component: NewPasswordComponent },
{ path: 'recover-success', component: RecoverSuccessComponent },


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
