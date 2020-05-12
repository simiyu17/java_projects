import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Router, CanActivate, RouterStateSnapshot, ActivatedRouteSnapshot } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate{

  constructor(public auth: AuthService, public router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    console.log('Is aunthenticated ====>' + this.auth.isAuthenticated());
      if (this.auth.isAuthenticated()) {
        return true;
      }

    this.router.navigate(['login'], { queryParams: { returnUrl: state.url } });
    return false;
  }
}
