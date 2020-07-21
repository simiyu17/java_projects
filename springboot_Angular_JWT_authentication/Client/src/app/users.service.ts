import { Injectable } from '@angular/core';
import { Observable, of, throwError, from } from 'rxjs';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { catchError, tap, map } from 'rxjs/operators';
import UserInfo from './UserInfo'
import { GlobalserviceService } from './globalservice.service';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  constructor(private http: HttpClient, private gs: GlobalserviceService) { }

  apiUrl = `${this.gs.baseUrl}/users`

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
  
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead
  
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  usersignin (user: UserInfo) {
    return this.http.post(`${this.apiUrl}/authenticate`, JSON.stringify(user), httpOptions);
  }


  getUserInfos(): Observable<UserInfo[]> {
    return this.http.get<UserInfo[]>(`${this.apiUrl}/`)
      .pipe(
        tap(users => console.log('fetched users')),
        catchError(this.handleError('getUserInfos', []))
      );
  }

  getUserInfoById(id: string): Observable<UserInfo> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<UserInfo>(url).pipe(
      tap(_ => console.log(`fetched user id=${id}`)),
      catchError(this.handleError<UserInfo>(`getUserInfoById id=${id}`))
    );
  }

  addUserInfo(user: UserInfo): Observable<UserInfo> {
    return this.http.post<UserInfo>(`${this.apiUrl}/`, JSON.stringify(user), httpOptions);
  }


  updateUserInfo(id: string, user: UserInfo): Observable<any> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.put(url, user, httpOptions).pipe(
      tap(_ => console.log(`updated user id=${id}`)),
      catchError(this.handleError<any>('updateUserInfo'))
    );
  }

  deleteUserInfo(id: string): Observable<UserInfo> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.delete<UserInfo>(url, httpOptions).pipe(
      tap(_ => console.log(`deleted user id=${id}`)),
      catchError(this.handleError<UserInfo>('deleteUserInfo'))
    );
  }
}
