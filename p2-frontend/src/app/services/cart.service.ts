import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, retry, throwError, BehaviorSubject, Observable } from 'rxjs';
import { IProduct } from '../types/IProduct';
import { OrderItem, OrderItemService } from './order-item.service';
import { User } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class CartService {


  public cartItemList: OrderItem[]=[];
  public productList = new BehaviorSubject<OrderItem[]>([]);
  constructor(private orderItemService: OrderItemService, private http: HttpClient) { }

  url = "http://localhost:8080/checkout"
  //
  getProducts(){
    return this.productList.asObservable();
  }
  // setProduct(product: any){
  //   this.cartItemList.push(product);
  //   this.productList.next(product);
  // }

  addToCart(product: IProduct){
    let orderItem = this.cartItemList.find(item => item.product.productId === product.productId)

    if(orderItem){
      orderItem.quantity++;
    }
    else{
      orderItem = new OrderItem(product, 1)
      this.cartItemList.push(orderItem);
    }

    this.productList.next(this.cartItemList)
    console.log(this.cartItemList);

  }

  removeCartItem(product: OrderItem){
    console.log("made it to remove cart item");
    this.cartItemList.map((a:OrderItem, index: number)=>{
      if(product === a){
        this.cartItemList.splice(index, 1);
      }
    })
    this.productList.next(this.cartItemList);
  }

  removeAllCartItems(){
    this.cartItemList = [];
    this.productList.next(this.cartItemList);
  }

  // getTotalProductPrice(): number{
  //   let productTotal = 0;
  //   this.cartItemList.map((a:OrderItem)=>{
  //     productTotal = a.total;
  //   })
  //   return productTotal;
  // }

  getTotalPrice(): number{
    let grandTotal = 0;
    this.cartItemList.forEach((a:OrderItem)=>{
      const itemTotalAmount= (100-a.product.discount)*a.product.price/100*a.quantity
      grandTotal += itemTotalAmount;
    })
    return grandTotal;
  }

  checkout(orderItemList: any[], user: User): Observable<any> {
    let cartDto = new CartDto(orderItemList, user);
    console.log('This is the cart we want to pass', cartDto);
    return this.http.post<any>(this.url, JSON.stringify(cartDto), {headers: new HttpHeaders({ 'Content-Type': 'application/json' }) })
    .pipe(
      retry(1),
      catchError(this.errorHandler),
    );
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



  // addProductToCart(quantity: number, product: Product) {
  //   let token = sessionStorage.getItem("cart")
  //   console.log("how many are we gettin? ", quantity);
  //   console.log(token, 'some radomon');
  //   if (!token) {
  //     console.log('cart is empty');
  //   }
  //   else {
  //     console.log(JSON.parse(token));
  //     let array = JSON.parse(token);
  //     let orderItem = new OrderItem(product, quantity, 1);
  //     array.push(orderItem);
  //     sessionStorage.setItem("cart", JSON.stringify(array));
  //   }
  // }


export class CartDto {
  orderItemList: OrderItem[];
  user: User;

  constructor(orderItemList: OrderItem[], user: User) {
    this.orderItemList = orderItemList;
    this.user = user;
  }
}
