import { Injectable } from '@angular/core';
import { Product } from './product.service';

@Injectable({
  providedIn: 'root'
})
export class OrderItemService {

  constructor() { }

}

export class OrderItem {
  productId: Product;
  quantity: number; 
  orderId: number;
  itemTotalAmount: number;

  constructor(productId: Product, quantity: number, orderId: number) {
    this.productId = productId;
    this.quantity = quantity;
    this.orderId = orderId;
    this.itemTotalAmount = productId.price * quantity;
  }
}
