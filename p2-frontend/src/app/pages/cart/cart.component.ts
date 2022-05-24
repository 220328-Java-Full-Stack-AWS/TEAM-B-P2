import { OrderItem } from 'src/app/services/order-item.service';
import { Component, OnInit } from '@angular/core';
import { CartService } from 'src/app/services/cart.service';
import { NgForOf } from '@angular/common';
import { Product } from 'src/app/services/product.service';

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
      window.location.href = "./checkout";
    }
  }

  ngOnInit(): void {
    // this.cartItems
    // let token = localStorage.getItem("cart")
    // if(token != null){
    //   JSON.parse(token);
    //   console.log(token);
    //   let p: string
    //   for(p in JSON.parse(token)){
    //     pid: Product = p.p
    //     this.cartService.addToCart(product);
    //   }
    // }
    this.cartService.getProducts()
      .subscribe(res=>{
        this.orderItems = res;
        this.grandTotal = this.cartService.getTotalPrice();
      })

  }

}
