import { Component, OnInit } from '@angular/core';
import { Address, AddressService, currentAddressToEdit} from 'src/app/services/address.service';
import { HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
@Component({
  selector: 'app-update-address',
  templateUrl: './update-address.component.html',
  styleUrls: ['./update-address.component.scss']
})
export class UpdateAddressComponent implements OnInit {

  addressId: any;
  number: any;
  street: string = ""
  city: string = ""
  state: string = ""
  zipCode: string = ""
  user: any;

  updatedAddress: any

  constructor(private addressService: AddressService, private router: Router) { }

  onClick(number: any,street: string,city: string,state: string,zipCode: string){
    this.number = number;
    this.street = street;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
    let updatedAddress = new currentAddressToEdit(this.addressId, number, street, city, state, zipCode, this.user)
    let options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    }
    this.addressService.updateAddress(updatedAddress, options)
      .subscribe((data)=>{
        console.log("address update complete", JSON.stringify(data));
        alert("You've update your address successfully");
        this.router.navigate(['/user-view']);
      });
  }

  ngOnInit(): void {
    console.log("made it to update address", this.addressService.getCurrentAddress())
    let address: any = this.addressService.getCurrentAddress();
    console.log("address to be passed: ", address)
    this.addressId = address.addressId
    this.number = address.number;
    this.street = address.street;
    this.city = address.city;
    this.state = address.state;
    this.zipCode = address.zipCode
    this.user = address.userId;
  }

}
