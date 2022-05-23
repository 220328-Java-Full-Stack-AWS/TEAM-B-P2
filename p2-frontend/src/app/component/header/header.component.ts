import { Component, OnInit } from '@angular/core';
import { CartService } from 'src/app/services/cart.service';

import { Router } from '@angular/router';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  public totalProducts: number = 0;

  constructor(private cartService: CartService, private router: Router) { }

  ngOnInit(): void {

    this.cartService.getProducts()
      .subscribe(res=>{
        this.totalProducts = res.length;
      })

  }
  onHome(): void{
    this.router.navigate(['/']);
  }

}
