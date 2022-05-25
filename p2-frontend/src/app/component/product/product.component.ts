import { IProduct } from 'src/app/types/IProduct';
import { AfterContentInit, AfterViewChecked ,Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit, AfterViewChecked {

  itemNewPrice = 0;
  @Input() item?: IProduct;
  @Input() show?: boolean;

  constructor(private router: Router, private cartService: CartService) {
  }
  ngOnInit(): void {

  }
  ngAfterViewChecked(): void {
    const price = this.item?.price ?? 0;
    const discount = this.item?.discount ?? 0
    this.itemNewPrice = (price * (100 - discount)) / 100;
  }
  onClick() {
    this.router.navigate([`/product-details/${this.item?.productId}`]);
  }
  onAddToCartClick() {
    if (this.item) this.cartService.addToCart(this.item)
  }


}
