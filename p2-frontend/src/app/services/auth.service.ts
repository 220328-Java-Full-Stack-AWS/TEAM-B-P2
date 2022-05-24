import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, retry, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  baseUrl: string = "http://P2-env.eba-mfpb3muj.us-east-1.elasticbeanstalk.com/auth";

  constructor(private http: HttpClient) { }

  login(body: object): Observable<any> {
    console.log("Post: ", this.baseUrl, body)
    return this.http.post<any>(this.baseUrl, JSON.stringify(body), {headers: new HttpHeaders({ 'Content-Type': 'application/json' }) })
      .pipe(
        retry(3),
        catchError(this.errorHandler),
      )
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


export class AuthDto {
  username: String;
  password: String;

  constructor(_username: String, _password: String) {
    this.username = _username;
    this.password = _password;
  }
}

