import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from 'src/app/services/product.service';
import { CategoryType } from 'src/app/types/constants';
import { IProduct } from 'src/app/types/IProduct';

@Component({
  selector: 'app-featured-products',
  templateUrl: './featured-products.component.html',
  styleUrls: ['./featured-products.component.css']
})
export class FeaturedProductsComponent implements OnInit {

  constructor(private productService: ProductService) { }

  public productList: IProduct[] = [];

  ngOnInit(): void {
    this.productService.viewAllProducts().subscribe((data: IProduct[]) => {
      this.productList = this.productService.filterByKeywords(data, 'bestseller');
    });
  }

}
