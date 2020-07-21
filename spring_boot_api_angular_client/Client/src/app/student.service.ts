import { Injectable } from '@angular/core';
import { Observable, of, throwError, from } from 'rxjs';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { catchError, tap, map } from 'rxjs/operators';
import Student from './Student'


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  apiUrl = 'http://localhost:8082/api/students';

  constructor(private http: HttpClient) { }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
  
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead
  
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }


  getStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(`${this.apiUrl}/`)
      .pipe(
        tap(students => console.log('fetched students')),
        catchError(this.handleError('getStudents', []))
      );
  }

  getStudentById(id: string): Observable<Student> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Student>(url).pipe(
      tap(_ => console.log(`fetched student id=${id}`)),
      catchError(this.handleError<Student>(`getStudentById id=${id}`))
    );
  }

  addStudent(student: Student): Observable<Student> {
    return this.http.post<Student>(`${this.apiUrl}/`, student, httpOptions).pipe(
      tap(_ => console.log(`added student`)),
      catchError(this.handleError<Student>('addStudent'))
    );
  }

  updateStudent(id: string, student: Student): Observable<any> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.put(url, student, httpOptions).pipe(
      tap(_ => console.log(`updated student id=${id}`)),
      catchError(this.handleError<any>('updateStudent'))
    );
  }

  deleteStudent(id: string): Observable<Student> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.delete<Student>(url, httpOptions).pipe(
      tap(_ => console.log(`deleted student id=${id}`)),
      catchError(this.handleError<Student>('deleteStudent'))
    );
  }
}
