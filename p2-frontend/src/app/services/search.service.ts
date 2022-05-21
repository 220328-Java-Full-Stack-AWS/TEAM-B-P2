
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class SearchService {

  getSearchResult(searchInput : String): Observable<any>{
    return this.http.get('http://localhost:8080/products/all' + searchInput);
  }

  constructor(private http:HttpClient) { }
}
