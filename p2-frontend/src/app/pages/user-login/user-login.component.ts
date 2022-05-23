import { HttpHeaders, HttpRequest } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AuthDto, AuthService } from '../../services/auth.service';


@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {

  constructor(private authService: AuthService) { }

  username: String = "";
  password: String = "";
  user: any;

  onClickLogin(username: String, password: String): void {
    let authDto = new AuthDto(this.username, this.password);
    let options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    }
    this.authService.login(authDto, options)
      .subscribe((data) => {
        console.log("returned data: ", data)
        this.user = data;
        console.log("logged in user ", this.user);
        localStorage.setItem("currentUser", this.user.userName);

        let rand = localStorage.getItem("currentUser")
        console.log("retrieved from local", rand)
      });
  }
  onClickRegister(): void {

  }



  ngOnInit(): void {
  }

}
