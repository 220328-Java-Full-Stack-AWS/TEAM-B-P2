import { Component, Input, OnInit } from '@angular/core';
import { of } from 'rxjs';
import { CartService } from 'src/app/services/cart.service';

import { Product, ProductService } from '../../services/product.service';
import { CategoryType } from '../../types/constants';
import { IProduct } from 'src/app/types/IProduct';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs';



@Component({
  selector: 'app-product-views',
  templateUrl: './product-views.component.html',
  styleUrls: ['./product-views.component.css']
})
export class ProductViewsComponent implements OnInit {
  public productList: IProduct[] = [];

  constructor(private productService: ProductService, private cartService: CartService , private route: ActivatedRoute,
    private activeRoute: ActivatedRoute,
    private router: Router) { }

  // @Input() quantity: number = 0;

  // TODO to look
  // public productList: any[] = [
  //   new Product("RINGS", "diamond ring", 10, "ring", 10, 1),
  //   new Product("NECKLACES", "pearl necklace", 10, "necklace", 15, 2),
  //   new Product("EARRINGS", "ruby earrings", 10, "earrings", 35, 3),
  //   new Product("BRACELETS", "gold bracelet", 10, "bracelet", 45, 4),
  //   new Product("RINGS", "wedding ring", 10, "ring", 10, 5),
  //   new Product("NECKLACES", "silver necklace", 10, "necklace", 35, 6),
  //   new Product("EARRINGS", "diamond earring", 10, "earring", 50, 7),
  //   new Product("BRACELETS", "silver bracelet", 10, "bracelet", 30, 8)
  // ]

  // addToCart(product: any, quantity: number){
  //   this.cartService.addToCart(product)
  //   this.quantity = quantity
  //   console.log('here is the qty: ', quantity);
  // }


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


    this.reFetch();
    this.activeRoute.params.subscribe(() => {
      this.reFetch();
    });
  }

  reFetch() {
    const categoryType = this.route.snapshot.paramMap.get('category') ?? "";
    let keyword = ""
    if (this.router.url === "/") {
      keyword = 'bestseller';
    }
    else {
      keyword = this.route.snapshot.paramMap.get('keyword')?.toLocaleLowerCase() ?? "";
    }
    if (categoryType in CategoryType) {
      this.productService.viewCategorizedProducts(categoryType as CategoryType).subscribe((data: IProduct[]) => {
        this.productList = this.productService.filterByKeywords(data, keyword);
      })
    } else {
      this.productService.viewAllProducts().subscribe((data: IProduct[]) => {
        this.productList = this.productService.filterByKeywords(data, keyword);
      })
    }
  }
}
