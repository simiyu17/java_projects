import { Component, OnInit } from '@angular/core';
import { GlobalserviceService } from '../globalservice.service';
import { Router } from '@angular/router';
import { Validators, FormGroup, FormBuilder } from '@angular/forms';
import { UsersService } from '../users.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
 
  userloginForm: FormGroup;
  invalidLogin = false;
  msg: string;
  constructor(private fb: FormBuilder, private us: UsersService,private gs: GlobalserviceService, private router: Router) { 
    this.gs.setShowMenu(false);
    this.createUserloginForm();
  }

  createUserloginForm(): void {
    this.userloginForm = this.fb.group({
      username: [null, Validators.required],
      password: [null, Validators.required]
    });
  }

  onUserLoginSubmit() {
    this.us.usersignin(this.userloginForm.value).subscribe(data => {

      if (!data['success']) {
        this.invalidLogin = true;
        this.msg = data['msg'];
      } else {
        window.sessionStorage.setItem('access_token', data['token']);
        window.sessionStorage.setItem('user_full_name', data['userFullName']);
        this.router.navigate(['home']);
      }
    }, error => {
        alert(error.error.error_description);
    });
  }

  ngOnInit(): void {
   
  }

}
