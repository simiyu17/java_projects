import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  public isAuthenticated(): boolean {
    const token = window.sessionStorage.getItem('access_token');
    const helper = new JwtHelperService();

    if (!token) {
      return false;
    }

    return !helper.isTokenExpired(token);
  }
}
