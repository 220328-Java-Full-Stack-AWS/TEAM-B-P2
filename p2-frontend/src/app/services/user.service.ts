import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, retry, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  baseUrl: string = "http://localhost:8080/users";

  constructor(private http: HttpClient) { }

  createUser(body: object, options: object): Observable<any> {
    console.log("Post: ", this.baseUrl, body, options)
    return this.http.post<any>(this.baseUrl + "/register", JSON.stringify(body), options)
      .pipe(
        retry(3),
        catchError(this.errorHandler)
      )
  }

  updateUser(body: object, options: object): Observable<any> {
    console.log("UPDATE: ", this.baseUrl, "/update", body, options)
    return this.http.put<any>(this.baseUrl + "/update", JSON.stringify(body), options)
      .pipe(
        retry(1),
        catchError(this.errorHandler)
      )
  }

  getUserByUsername(username: string): Observable<any>{
    let options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'username': username
      })
    }
    console.log("GET: ", this.baseUrl, "/currentuser", options);
    return this.http.get<User>(this.baseUrl + "/currentuser", options);
  }
  

  errorHandler(e: any): any {
    console.log("Error handler invoked...");
    let errorMessage = '';
    if (e.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = e.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${e.status}\nMessage: ${e.message}`;
    }
    console.log(errorMessage);
    return throwError(() => new Error(errorMessage));
  }

}

export class User {
  id: number | undefined;
  userName: String;
  password: String;
  firstName: String;
  lastName: String;
  email: String;
  phone: String;

  constructor(_username: String, _password: String, _fName: String, _lName: String, _email: String, _phone: String) {
    this.userName = _username;
    this.password = _password;
    this.firstName = _fName;
    this.lastName = _lName;
    this.email = _email;
    this.phone = _phone;
  }
}