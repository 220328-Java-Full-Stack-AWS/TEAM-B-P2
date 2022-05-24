import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, retry, throwError, BehaviorSubject, Observable } from 'rxjs';
import { IProduct } from '../types/IProduct';
import { Address } from './address.service';
import { OrderItem, OrderItemService } from './order-item.service';
import { User } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class CartService {


  public orderItemList: OrderItem[]=[];
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
    let orderItem = this.orderItemList.find(item => item.productId.productId === product.productId)

    if(orderItem){
      orderItem.quantity++;
    }
    else{
      orderItem = new OrderItem(product, 1)
      this.orderItemList.push(orderItem);
    }

    this.productList.next(this.orderItemList)
    console.log(this.orderItemList);

    localStorage.removeItem("cart");
    localStorage.setItem("cart", JSON.stringify(this.orderItemList));

  }

  removeCartItem(product: OrderItem){
    console.log("made it to remove cart item");
    this.orderItemList.map((a:OrderItem, index: number)=>{
      if(product === a){
        this.orderItemList.splice(index, 1);
      }
    })
    this.productList.next(this.orderItemList);
  }

  removeAllCartItems(){
    this.orderItemList = [];
    this.productList.next(this.orderItemList);
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
    this.orderItemList.forEach((a:OrderItem)=>{
      const itemTotalAmount= (100-a.productId.discount)*a.productId.price/100*a.quantity
      grandTotal += itemTotalAmount;
    })
    return grandTotal;
  }

  checkout(orderItemList: any[], user: User, shippingAddress: Address): Observable<any> {
    let cartDto = new CartDto(orderItemList, user, shippingAddress);
    console.log('This is the cart we want to pass', cartDto);
    console.log('This is the cart we want to pass', JSON.stringify(cartDto));
    return this.http.post<any>(this.url, JSON.stringify(cartDto), { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) })
      .pipe(
      retry(0),
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
  shippingAddress: Address;
  

  constructor(orderItemList: OrderItem[], user: User, shippingAddress: Address) {
    this.orderItemList = orderItemList;
    this.user = user;
    this.shippingAddress = shippingAddress;
  }
}
