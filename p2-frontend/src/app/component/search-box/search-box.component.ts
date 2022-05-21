import { SearchService } from './../../services/search.service';
import { Component, OnInit } from '@angular/core';
import { IProduct } from 'src/app/IProduct';

@Component({
  selector: 'app-search-box',
  templateUrl: './search-box.component.html',
  styleUrls: ['./search-box.component.css']
})
export class SearchBoxComponent implements OnInit {
  entry : String = "";
  image : String = "";

  result : IProduct = {
    productId : 0,
    name : "",
    description : "",
    price : 0,
    inventory : 0
  }

  search(input: String){
    this.searchService.getSearchResult(input)
    .subscribe((data:any) => {
      console.log(data)
      this.result = {
        productId:data.productId,
        name : data.name,
        description : data.stats[0].base_stat,
        price : data.stats[1].base_stat,
        inventory : data.stats[2].base_stat,
      }
    })
  }

  constructor(private searchService : SearchService) { }

  ngOnInit(): void {
  }

}
