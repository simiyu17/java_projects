import { AuthService } from './auth.service';
import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpResponse,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable()
export class Token implements HttpInterceptor {

  constructor() {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const token: string = window.sessionStorage.getItem('access_token');

    if (!request.headers.has('Content-Type')) {
        if (token != null) {
            // request = request.clone({ headers: request.headers.set('Authorization', 'Bearer ' + token) });
            request = request.clone({ setHeaders: { Authorization: `Bearer ${token}`, 'Content-Type': 'application/json'}
         });
         }
        request = request.clone({ headers: request.headers.set('Content-Type', 'application/json') });
    }

    request = request.clone({ headers: request.headers.set('Accept', 'application/json') });

    //console.log('Request Headers ====>' + JSON.stringify(request.headers));


    // return next.handle(authReq);

    console.log('event--->>>', JSON.stringify(next));

    return next.handle(request).pipe(
      map((event: HttpEvent<any>) => {
          if (event instanceof HttpResponse) {
              console.log('event--->>>', event);
          }
          return event;
      }, (err: any) => {
        if (err instanceof HttpErrorResponse) {
          if (err.status === 401) {
          //  this.auth.collectFailedRequest(request);
          }
          if (err.status === 403) {
            console.log('The error ====>' + err.message);
            }
        }
      }));

  }

}