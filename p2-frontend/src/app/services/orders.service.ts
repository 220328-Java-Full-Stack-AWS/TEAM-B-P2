import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, retry, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  constructor(private http: HttpClient) { }

  url = "http://localhost:8080/orders/byCurrentUser"




  displayAllOrdersForUser(user: any) {
    console.log("Made it to display orders", user);
    return this.http.post<any>(this.url, JSON.stringify(user),
      { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) })
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

}

// export class Orders {
//   orderId: number;
//   user: User;
//   creationDate: string;
  

//   constructor(orderItemList: OrderItem[], user: User, shippingAddress: Address) {
//     this.orderItemList = orderItemList;
//     this.user = user;
//     this.shippingAddress = shippingAddress;
//   }
// }
