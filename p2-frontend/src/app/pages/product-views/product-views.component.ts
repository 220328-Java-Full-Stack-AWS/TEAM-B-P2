import { Component, Input, OnInit } from '@angular/core';
import { of } from 'rxjs';
import { CartService } from 'src/app/services/cart.service';

import { Product, ProductService } from '../../services/product.service';


@Component({
  selector: 'app-product-views',
  templateUrl: './product-views.component.html',
  styleUrls: ['./product-views.component.css']
})
export class ProductViewsComponent implements OnInit {

  constructor(private productService: ProductService, private cartService: CartService) { }

  @Input() quantity: number = 0;

  public productList: any[] = [
    new Product("RINGS", "diamond ring", 10, "ring", 10, 1),
    new Product("NECKLACES", "pearl necklace", 10, "necklace", 15, 2),
    new Product("EARRINGS", "ruby earrings", 10, "earrings", 35, 3),
    new Product("BRACELETS", "gold bracelet", 10, "bracelet", 45, 4),
    new Product("RINGS", "wedding ring", 10, "ring", 10, 5),
    new Product("NECKLACES", "silver necklace", 10, "necklace", 35, 6),
    new Product("EARRINGS", "diamond earring", 10, "earring", 50, 7),
    new Product("BRACELETS", "silver bracelet", 10, "bracelet", 30, 8)
  ]

  addToCart(product: any, quantity: number){
    this.cartService.addToCart(product)
    this.quantity = quantity
    console.log('here is the qty: ', quantity);
  }


  // addToCart(quantity: number, product: Product): void {
  //   console.log(quantity);
  //   this.cartService.addProductToCart(quantity, product)
  // }

  ngOnInit(): void {
    // let array: any[] = new Array;
    // sessionStorage.setItem("cart", JSON.stringify(array))
    this.productList.forEach((a:any) =>{
      Object.assign(a,{total:a.price});
    });
  }

}
