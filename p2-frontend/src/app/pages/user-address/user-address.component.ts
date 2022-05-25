import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Address, AddressService, AllAddress, CurrentAddress } from 'src/app/services/address.service';
import { Router } from '@angular/router';
import { User } from 'src/app/services/user.service';
import { faWindowRestore } from '@fortawesome/free-solid-svg-icons';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-user-address',
  templateUrl: './user-address.component.html',
  styleUrls: ['./user-address.component.scss']
})
export class UserAddressComponent implements OnInit {
  addressId: any;
  number: string = "";
  street: string = "";
  city: string = "";
  state: string = "";
  zipCode: string = "";
  userId: any;

  editNumber: string ="";



  public addresses: Address[] = [];
  


  // currentAddress: CurrentAddress = {
  //   addressId: this.addressId,
  //   number: '',
  //   street: '',
  //   city: '',
  //   state: '',
  //   zipCode: '',
  //   userId: 1
  // };

  constructor(
    private addressService: AddressService,
    private router: Router
  ) { }



  addNewAddress(): void {


    console.log("adding new address...")

    let address = new Address(this.number, this.street, this.city, this.state, this.zipCode, this.userId)
    //let currentAddress = new CurrentAddress(this.addressId)
    window.alert('You have added to your current address')
    //tested referring the address and httpOptions object in addressService, switched back because it didn't work
    this.addressService.postNewAddress(address).subscribe((data: any) => { console.log("returned data: ", data) });
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
    return this.addressService.getAddressById(address).subscribe((data: Address) => { console.log("returned data: ", data) });
  }


  showAllUserAddresses(user: User): any {
    console.log("trying to retrieve all user addresses...")
    
    //address body need to change to a body with "id" = 1

    return this.addressService.getAddressByUser(user).subscribe((data: Address[]) => {
      console.log("returned data: ", data);
      this.addresses = data;
      console.log("checking to make sure the data saves properly to this.addresses", this.addresses)
    });
  }


  changeAddress(address: Address) {
    this.addressService.setCurrentAddress(address, this.userId)
    this.router.navigate(['/update-address']);
  }

  deleteAddress(address: Address): void {
    console.log("deleting your address...");
    console.log(address);
    const aId = address.addressId
    let options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'addressId': aId + ""
      })
    }
    this.addressService.deleteAddress("/deleteByHeader", address, options).subscribe((data: any) => {
      console.log("returned data: ", data)
    });
  }


  ngOnInit(): void {

    let token = localStorage.getItem("currentUser")
    console.log("inside of ngoninit")
    if (!token) {
      window.alert("user not logged in.")
    } else {
      this.userId = JSON.parse(token)
      console.log("current user", this.userId)
      this.showAllUserAddresses(this.userId)
    }
  }

}
