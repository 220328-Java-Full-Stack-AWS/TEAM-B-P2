import { Component, OnInit } from '@angular/core';
import { Address } from 'src/app/services/address.service';
import { CartService } from 'src/app/services/cart.service';
import { AddressService } from 'src/app/services/address.service';
import { OrderItem } from 'src/app/services/order-item.service';
import { User } from 'src/app/services/user.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss']
})
export class CheckoutComponent implements OnInit {

  public orderItems: OrderItem[] = [];
  public grandTotal: number = 0;

  user: any;

  billingNumber: string = "";
  billingStreet: string = "";
  billingCity: string = "";
  billingState: string = "";
  billingZipCode: string = "";

  shippingNumber: string = "";
  shippingStreet: string = "";
  shippingCity: string = "";
  shippingState: string = "";
  shippingZipCode: string = "";
  shippingAddressId: number = 0;

  cardNumber: string = "";
  cardName: string = "";
  expirationDate: string = "";
  CVV: string = "";


  constructor(private cartService: CartService, private addressService: AddressService) { }

  

  goToCheckout() {

    let billingAddress = new Address(this.billingNumber, this.billingStreet, this.billingCity, this.billingState, this.billingZipCode, this.user);
    let shippingAddress = new Address(this.shippingNumber, this.shippingStreet, this.shippingCity, this.shippingState, this.shippingZipCode, this.user);
    
    // this.addressService.getAddressById(billingAddress).subscribe((data) => {
    //   console.log('this is the data from the back', data);
    //   let temp = JSON.stringify(data);
    //   if (!temp) {
    //     this.addressService.postNewAddress(billingAddress).subscribe((d) => {
    //       console.log('new address created', d);
    //     })
    //   } else {
    //     console.log("address exists")
    //   }
    // });

    // this.addressService.getAddressById(shippingAddress).subscribe((data) => {
    //   console.log('this is the data from the back', data);
    //   let temp = JSON.stringify(data);
    //   if (!temp) {
    //     this.addressService.postNewAddress(shippingAddress).subscribe((d) => {
    //       console.log('new address created', d);
    //     })
    //   } else {
    //     console.log("address exists")
    //   }
    // });


    // this.addressService.postNewAddress(billingAddress).subscribe((d) => {
    //   console.log('new address created', d);
    // })

    this.addressService.postNewAddress(shippingAddress).subscribe((d) => {
      console.log('new address created', d);
      shippingAddress = d;
    })
    
    console.log('shipping address created', shippingAddress)
    this.cartService.checkout(this.cartService.orderItemList, this.user, shippingAddress).subscribe((data) => {
      console.log('this is the data from the back', data);
    });
    console.log("go to checkout:", this.user, this.cartService.orderItemList, billingAddress, shippingAddress);
    
  }

  ngOnInit(): void {
    let token = localStorage.getItem('currentUser')
    let u: User;
    if (!token) {
      alert("You are not logged in");
    }
    else {
      u = JSON.parse(token);
      this.user = u;
    }
    this.cartService.getProducts()
      .subscribe(res => {
        this.orderItems = res;
        this.grandTotal = this.cartService.getTotalPrice();
      })
  }

}



