import { ProductService } from './services/product.service';
import { IProduct } from './types/IProduct';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'front-end-testing';

  constructor() {}

  ngOnInit(): void {

  }
}