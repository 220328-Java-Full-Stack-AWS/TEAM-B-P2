import { ProductService } from 'src/app/services/product.service';

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-box',
  templateUrl: './search-box.component.html',
  styleUrls: ['./search-box.component.css']
})
export class SearchBoxComponent implements OnInit {
  entry: string = "";
  image: string = "";
  constructor(private router: Router, private productService: ProductService) { }

  search(keyword: string) {
    this.router.navigate(['/product-views', { keyword, category: this.productService.getSelectedCategory() }]);
    this.entry="";
  }


  ngOnInit(): void {
  }

}
