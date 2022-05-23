
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartService } from 'src/app/services/cart.service';
@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  constructor(private router: Router, private cartService: CartService) { }

  public totalProducts: number = 0;

  ngOnInit(): void {
    this.cartService.getProducts()
    .subscribe(res =>{
      let total = 0;
      res.forEach(item=>{total += item.quantity});
      this.totalProducts = total;
    })
  }
}
