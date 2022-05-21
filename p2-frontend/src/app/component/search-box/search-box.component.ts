import { ProductService } from 'src/app/services/product.service';
import { SearchService } from './../../services/search.service';
import { Component, OnInit } from '@angular/core';
import { IProduct } from 'src/app/IProduct';

@Component({
  selector: 'app-search-box',
  templateUrl: './search-box.component.html',
  styleUrls: ['./search-box.component.css']
})
export class SearchBoxComponent implements OnInit {
  entry : string = "";
  image : string = "";

  search(input: string){
    this.productService.setKeywordFilter(input);
  }

  constructor(private productService : ProductService) { }

  ngOnInit(): void {
  }

}
