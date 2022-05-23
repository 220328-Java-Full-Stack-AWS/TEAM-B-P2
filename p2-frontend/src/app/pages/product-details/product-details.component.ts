import { ProductService } from 'src/app/services/product.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { IProduct } from 'src/app/types/IProduct';
@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {
  public product? : IProduct;
  constructor(private route: ActivatedRoute , private productService: ProductService ) {
  }


  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const productId = params['id']
      this.productService.viewProductById(productId).subscribe((product) => {
        this.product = product;
      });
    });

  }

}
