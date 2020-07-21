import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navigate',
  templateUrl: './navigate.component.html',
  styleUrls: ['./navigate.component.css']
})
export class NavigateComponent implements OnInit {

  userfullName: string;
  constructor(private router: Router) { 
    this.userfullName = window.sessionStorage.getItem('user_full_name');
  }

  
  logout(){
    window.sessionStorage.removeItem('access_token');
    window.sessionStorage.removeItem('user_full_name');
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() =>
    this.router.navigate(['home']));
  }

  ngOnInit(): void {
  }

}
