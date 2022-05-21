import { IProduct } from 'src/app/types/IProduct';
import { Component, OnInit } from '@angular/core';
import { CategoryType } from 'src/app/types/constants';
import { ProductService } from 'src/app/services/product.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  public categoryTypes = Object.values(CategoryType);
  constructor(private productService: ProductService,
    private router: Router) { }
  result: IProduct[] = [];
  onClick(type: CategoryType): void {
    this.productService.setSelectedCategory(type);
    this.router.navigate(['/product-views', { category: type }]);
  }

  ngOnInit(): void {

  }

}
