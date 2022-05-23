import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { AuthDto, AuthService } from '../../services/auth.service';


@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.scss']
})
export class UserLoginComponent implements OnInit {

  constructor(private authService: AuthService, private userService: UserService) { }

  username: string = "";
  password: string = "";
  

  onClickLogin(username: string, password: string): void {
    let authDto = new AuthDto(this.username, this.password);
    this.authService.login(authDto).subscribe((data) => { localStorage.setItem("currentUser:", JSON.stringify(data)) })
    if (localStorage.getItem('currentUser:') == null) {
      alert("Unable to log in! Check username and password!");
    }
    else {
      window.location.href = "./product-views";
    }
  }
  onClickRegister(): void {
    window.location.href = "./user-registration"
  }
  
  ngOnInit(): void {
  }

}
