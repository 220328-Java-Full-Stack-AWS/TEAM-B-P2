import { Component, OnInit } from '@angular/core';
import { User, UserService } from '../../services/user.service';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.css']
})
export class UserUpdateComponent implements OnInit {

  username: string = "";
  password: string = "";
  firstName: string = "";
  lastName: string = "";
  email: string = "";
  phone: string = "";

  user: any;

  constructor(private userService: UserService) { 

  }

  

  onClick(_username: string, _password: string, _firstName: string, _lastName: string, _email: string, _phone: string): void {
    this.user.userName = _username;
    this.user.password = _password;
    this.user.firstName = _firstName;
    this.user.lastName = _lastName;
    this.user.email = _email;
    this.user.phoneNumber = _phone;

    console.log("this is after each field is updated", this.user)
    let options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    }
    this.userService.updateUser(this.user, options).subscribe((data) => {
      console.log("trying to update data with: ", data)
      this.user = data;
      console.log("logged in user ", this.user);
      localStorage.setItem("currentUser", this.user.userName);
    })

    
  }

  ngOnInit(): void {
    let token = localStorage.getItem("currentUser");
    if (!token) // if a is negative,undefined,null,empty value then...
    {
      console.log("no current user logged in")
    }
    else {
      console.log("current User", token);
      this.userService.getUserByUsername(token).subscribe(
        (data) => {
          console.log("returned data: ", data)
          this.user = data;
          console.log("this is the user that is currently logged in", this.user)
        })
    }
  }

}
