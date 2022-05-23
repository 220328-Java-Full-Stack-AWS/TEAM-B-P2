import { Component, OnInit } from '@angular/core';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  public products: any = [];
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
    let token = sessionStorage.getItem('currentUser:')
    if(!token){
      alert("You are not logged in");
    }
    else{
      let user = JSON.parse(token);
      this.cartService.checkout(this.cartService.cartItemList, user).subscribe((data) =>{
        console.log('this is the data from the back', data);
      });
      console.log("go to checkout:", token, this.cartService.cartItemList);
    }
  }

  ngOnInit(): void {
    // this.cartItems
    this.cartService.getProducts()
      .subscribe(res=>{
        this.products = res;
        this.grandTotal = this.cartService.getTotalPrice();
      })

  }

}
