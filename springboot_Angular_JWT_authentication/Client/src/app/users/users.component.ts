import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import UserInfo from '../UserInfo';
import { UsersService } from '../users.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  showmenu: boolean = true;

  users: UserInfo[];
  

  constructor(private us: UsersService, private router: Router) { }

  dtOptions: DataTables.Settings = {};
  ngOnInit(): void {

    this.us.getUserInfos()
    .subscribe((res: any) => {
      this.users = res;
      console.log(this.users);
    }, err => {
      console.log(err);
    });

    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
      processing: true
    };
  }

}
