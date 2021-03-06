import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, retry, throwError } from 'rxjs';
import { User } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  baseUrl: string = "http://localhost:8080/auth";

  constructor(private http: HttpClient) { }


  login(body: object, options: object): Observable<any> {
    console.log("Post: ", this.baseUrl, body, options)
    return this.http.post<User>(this.baseUrl, JSON.stringify(body), options)
      .pipe(
        retry(3),
        catchError(this.errorHandler)
      )

  }



  errorHandler(e: any): any {
    console.log("Error handler invoked...");
    let errorMessage = '';
    if (e.error instanceof ErrorEvent) {
      // Get client-side error
      alert("Did you lose connection?")
    } else {
      // Get server-side error
      alert('Invalid username or password');
      errorMessage = `Error Code: ${e.status}\nMessage: ${e.message}`;
    }
    console.log(errorMessage);
    return throwError(() => new Error(errorMessage));
  }



}


export class AuthDto {
  username: string;
  password: string;

  constructor(_username: string, _password: string) {
    this.username = _username;
    this.password = _password;
  }
}

