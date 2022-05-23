import { Component, OnInit } from '@angular/core';
import { User, UserService } from '../../services/user.service';
import { HttpHeaders, HttpRequest } from '@angular/common/http';


@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.scss']
})
export class UserRegistrationComponent implements OnInit {

  constructor(private userService: UserService) { }

  username: string = "";
  password: string = "";
  fName: string = "";
  lName: string = "";
  email: string = "";

  onClickRegister(username: string, password: string, fName: string, lName: string, email: string) {
    let user = new User(this.username, this.password, this.fName, this.lName, this.email, "");
    let options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    }
    this.userService.createUser(user, options).subscribe((data) => { console.log("returned data: ", data) })
  }

  ngOnInit(): void {
  }

}
