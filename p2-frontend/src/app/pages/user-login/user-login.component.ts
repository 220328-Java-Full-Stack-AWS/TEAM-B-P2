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
  user: any;
  username: String = "";
  password: String = "";

  onClickLogin(username: String, password: String): void {
    let authDto = new AuthDto(this.username, this.password);
    let options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    }
    let response = this.authService.login(authDto, options).subscribe((data) => {
      this.user = data;
      localStorage.setItem("currentLoginUser", JSON.stringify(this.user));
      console.log("returned data: ", data)
    })
  }
  onClickRegister(): void {

  }



  ngOnInit(): void {
  }

}
