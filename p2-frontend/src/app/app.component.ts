import { ProductService } from './services/product.service';
import { IProduct } from './types/IProduct';
import { Component, OnInit } from '@angular/core';
import { CartService } from './services/cart.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'front-end-testing';

  constructor(private cartService: CartService) {}

  ngOnInit(): void {
    // let token = localStorage.getItem("cart");
    // if(!token){
    //   console.log("there was nothing in the cart");
    // }
    // else{
    //   let cart = JSON.parse(token);
    //   cart.forEach((a:any)=>{
    //     this.cartService.addToCart(a.product);
    //     console.log(a.product);
    //   });
    // }
  }
}
