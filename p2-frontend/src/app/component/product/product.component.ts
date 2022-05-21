import { IProduct } from 'src/app/types/IProduct';
import { Component,Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  @Input() item? : IProduct;

  constructor() { }

  ngOnInit(): void {
  }

}