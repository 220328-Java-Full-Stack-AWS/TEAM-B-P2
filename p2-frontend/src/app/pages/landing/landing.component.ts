import { ProductService } from 'src/app/services/product.service';
import { Component, OnInit } from '@angular/core';
import { BannerComponent } from 'src/app/component/banner/banner.component';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent implements OnInit {

  constructor(private productService:  ProductService) { }

  ngOnInit(): void {
    this.productService.setSelectedCategory("");
  }

}
