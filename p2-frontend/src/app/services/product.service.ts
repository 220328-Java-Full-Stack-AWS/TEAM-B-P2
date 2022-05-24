import { CategoryType } from '../types/constants';
import { IProduct } from '../types/IProduct';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, Observable, retry, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  baseUrl: string = "http://p2-s3-bucket.s3-website-us-east-1.amazonaws.com/products";
  products: IProduct[] = [];
  selectedCategory="";

  constructor(private http: HttpClient) { }

  viewAllProducts(): Observable<any> {
    return this.http.get<object>(this.baseUrl + "/all", { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) })
      .pipe(
        retry(1),
        catchError(this.errorHandler)
      )
  }

  viewProductById(id:string): Observable<any> {
    return this.http.get<object>(this.baseUrl + `/id/${id}`, { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) })
      .pipe(
        retry(1),
        catchError(this.errorHandler)
      )
  }

  viewCategorizedProducts(category: CategoryType): Observable<any> {
    return this.http.get<object>(this.baseUrl + `/search/cat?category=${category}`, { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) })
      .pipe(
        retry(1),
        catchError(this.errorHandler)
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


  setSelectedCategory(category : CategoryType | "") {
    this.selectedCategory = category;
  }

  getSelectedCategory() {
    return this.selectedCategory ;
  }

  filterByKeywords(data: IProduct[], keyword: string) {
    return (keyword === "" || keyword == null) ? data : data.filter(p => p.keywords.includes(keyword) ||
      p.description.toLocaleLowerCase().includes(keyword) ||
      p.name.toLowerCase().includes(keyword));
  }

}

export class Product {
  category: String;
  description: String;
  inventory: number;
  name: String;
  price: number;
  productId: number;

  constructor(_category: String, _description: String, _inventory: number, _name: String, _price: number, _productId: number) {
    this.category = _category;
    this.description = _description;
    this.inventory = _inventory;
    this.name = _name;
    this.price = _price;
    this.productId = _productId;
  }
}
