import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Address, AddressService, AllAddress, CurrentAddress } from 'src/app/services/address.service';
import { ActivatedRoute } from '@angular/router';
import { User } from 'src/app/services/user.service';


@Component({
  selector: 'app-user-address',
  templateUrl: './user-address.component.html',
  styleUrls: ['./user-address.component.css']
})
export class UserAddressComponent implements OnInit {
  addressId: number = 1;
  number: string = "";
  street: string = "";
  city: string = "";
  state: string = "";
  zipCode: string = "";
  userId: any;



  currentAddress: CurrentAddress = {
    addressId: this.addressId,
    number: '',
    street: '',
    city: '',
    state: '',
    zipCode: '',
    userId: 1
  };

  constructor(
    private addressService: AddressService,
    private route: ActivatedRoute,

  ) { }



  addNewAddress(): void {


    console.log("adding new address...")

    let address = new Address(this.number, this.street, this.city, this.state, this.zipCode, this.userId)
    let options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    }
    //let currentAddress = new CurrentAddress(this.addressId)
    window.alert('You have added to your current address')
    //tested referring the address and httpOptions object in addressService, switched back because it didn't work
    this.addressService.postNewAddress("/address/add", address, options).subscribe((data: any) => { console.log("returned data: ", data) });
    //this.showAddress(currentAddress);
  }


  showAddress(_currentAddress: CurrentAddress): any {
    console.log("displaying current address...")

    //address body need to change to a body with "id" = 1
    let address = new CurrentAddress(this.addressId);
    let options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    }
    return this.addressService.getAddressById("/address/currentaddress", address, options).subscribe((data: Address) => { console.log("returned data: ", data) });
  }


  showAllUserAddresses(user: User): any {
    console.log("displaying current address...")

    //address body need to change to a body with "id" = 1

    return this.addressService.getAddressByUser(user).subscribe((data: Address) => { console.log("returned data: ", data) });
  }

  changeAddress(): any {
    console.log("updating your address...")
    let address = new Address(this.number, this.street, this.city, this.state, this.zipCode, this.userId);
    let options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    }
    return this.addressService.updateAddress("/address/update", address, options).subscribe((data: any) => { console.log("returned data: ", data) });

  }

  deleteAddress(): void {
    console.log("deleting your address...")
    let address = new Address(this.number, this.street, this.city, this.state, this.zipCode, this.userId);
    let options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    }
    this.addressService.deleteAddress("/address/delete", address, options).subscribe((data: any) => { console.log("returned data: ", data) });
  }


  ngOnInit(): void {
    let token = localStorage.getItem("currentLoginUser")

    if (!token) {
      window.alert("user not logged in.")
    } else {
      this.userId = JSON.parse(token)
      this.showAllUserAddresses(this.userId)
      console.log("message from ngOnInit: ", this.showAllUserAddresses(this.userId))
    }
  }

}