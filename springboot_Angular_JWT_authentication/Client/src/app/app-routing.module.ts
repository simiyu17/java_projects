import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProfileComponent } from './profile/profile.component';
import { RegisteruserComponent } from './registeruser/registeruser.component';
import { LoginComponent } from './login/login.component';
import { UsersComponent } from './users/users.component';
import { AuthGuard } from './auth-guard';


const routes: Routes = [{
  path: 'home',
  redirectTo: '/students', pathMatch: 'full'
},
{
  path: 'students',
  component: ProfileComponent,
   canActivate: [AuthGuard]
},
{
  path: 'users',
  component: UsersComponent,
  canActivate: [AuthGuard]
},
{
  path: 'regusers',
  component: RegisteruserComponent
},
{
  path: 'login',
  component: LoginComponent
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
