import { IProduct } from 'src/app/IProduct';
import { Component, OnInit } from '@angular/core';
import { CategoryType } from 'src/app/constraints/constants';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  public  categoryTypes = Object.values(CategoryType);
  constructor(private productService: ProductService) { }
  result: IProduct[] = [];
  onClick(type: CategoryType): void {
    console.log(this.productService.getProducts().filter(p => p.category === type));
  }

  ngOnInit(): void {

  }

}
