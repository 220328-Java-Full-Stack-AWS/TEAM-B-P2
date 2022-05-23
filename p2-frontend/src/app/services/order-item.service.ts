import { Injectable } from '@angular/core';
import { IProduct } from '../types/IProduct';
import { Product } from './product.service';

@Injectable({
  providedIn: 'root'
})
export class OrderItemService {

  constructor() { }

}

export class OrderItem {
  product: IProduct;
  orderId: number;
  quantity:number;


  constructor(product : IProduct ,orderId: number) {
    this.product = {...product, discount : product.discount ?? 0};
    this.orderId = orderId;
    this.quantity=1;

  }
}
