import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GlobalserviceService } from '../globalservice.service';
import { UsersService } from '../users.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-registeruser',
  templateUrl: './registeruser.component.html',
  styleUrls: ['./registeruser.component.css']
})
export class RegisteruserComponent implements OnInit {
  
  userForm: FormGroup;
  notSuccessfully = false;
  msg: string = 'Success';
  constructor(private fb: FormBuilder, private us: UsersService, private gs: GlobalserviceService, private router: Router) {
    this.gs.setShowMenu(false);
    this.createUserForm();
   }

   createUserForm(): void {
    this.userForm = this.fb.group({
      fullname: [null, Validators.required],
      username: [null, Validators.required],
      password: [null, Validators.required],
      password2: [null, Validators.required]
    });
  }

  onUserFormSubmit() {
    if (this.userForm.value.password != this.userForm.value.password2) {
      this.notSuccessfully = true;
      this.msg = 'Passwords do not match';
    } else {
      this.us.addUserInfo(this.userForm.value)
        .subscribe((res: any) => {

          if (!res['success']) {
            this.notSuccessfully = true;
            this.msg = res['msg'];
          } else {
            this.router.navigate(['home']);
          }
        }, (err: any) => {
          console.log(err);
        });
    }
      
  }


  ngOnInit(): void {
  }

}
