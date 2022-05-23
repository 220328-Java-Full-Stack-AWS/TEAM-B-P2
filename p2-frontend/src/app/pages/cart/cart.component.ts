import { OrderItem } from 'src/app/services/order-item.service';
import { Component, OnInit } from '@angular/core';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  public orderItems: OrderItem[] = [];
  public grandTotal: number = 0;
  constructor(private cartService: CartService) { }
  // cartItems = sessionStorage.getItem("product");

  removeProduct(product: any){
    console.log("cart component remove item");
    this.cartService.removeCartItem(product);
  }

  removeAllProducts(){
    this.cartService.removeAllCartItems();
  }

  goToCheckout(){
    let token = localStorage.getItem('currentUser')
    if(!token){
      alert("You are not logged in");
    }
    else{
      let user = JSON.parse(token);
      this.cartService.checkout(this.cartService.orderItemList, user).subscribe((data) => {
        console.log('this is the data from the back', data);
      });
      console.log("go to checkout:", user, this.cartService.orderItemList);
    }
  }

  ngOnInit(): void {
    // this.cartItems
    this.cartService.getProducts()
      .subscribe(res=>{
        this.orderItems = res;
        this.grandTotal = this.cartService.getTotalPrice();
      })

  }

}
