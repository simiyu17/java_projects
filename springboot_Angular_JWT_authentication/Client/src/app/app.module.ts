import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProfileComponent } from './profile/profile.component';
import {DataTablesModule} from 'angular-datatables';
import { SlimLoadingBarModule } from 'ng2-slim-loading-bar';
import { StudentService } from './student.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms' 
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DatePipe } from '@angular/common';
import { UsersComponent } from './users/users.component';
import { NavigateComponent } from './navigate/navigate.component';
import { NoNavigateComponent } from './no-navigate/no-navigate.component';
import { LoginComponent } from './login/login.component';
import { RegisteruserComponent } from './registeruser/registeruser.component';
import { AuthService } from './auth.service';
import { AuthGuard } from './auth-guard';
import { Token } from './Token';

@NgModule({
  declarations: [
    AppComponent,
    ProfileComponent,
    UsersComponent,
    NavigateComponent,
    NoNavigateComponent,
    LoginComponent,
    RegisteruserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    DataTablesModule,
    SlimLoadingBarModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    BsDatepickerModule.forRoot()
  ],
  providers: [StudentService, DatePipe, AuthGuard, AuthService, { provide: HTTP_INTERCEPTORS, useClass: Token,  multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
