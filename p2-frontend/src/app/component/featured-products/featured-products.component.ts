import { Component, OnInit, ViewEncapsulation } from '@angular/core';

import { ProductService } from 'src/app/services/product.service';
import { IProduct } from 'src/app/types/IProduct';
// import Swiper core and required modules
import SwiperCore, { Pagination } from "swiper";

// install Swiper modules
SwiperCore.use([Pagination]);

@Component({
  selector: 'app-featured-products',
  templateUrl: './featured-products.component.html',
  styleUrls: ['./featured-products.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class FeaturedProductsComponent implements OnInit {

  constructor(private productService: ProductService ) { }

  public productList: IProduct[] = [];

  ngOnInit(): void {
    this.productService.viewAllProducts().subscribe((data: IProduct[]) => {
      this.productList = this.productService.filterByKeywords(data, 'bestseller');
    });
  }

}
