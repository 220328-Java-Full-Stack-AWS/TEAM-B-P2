import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { AuthDto, AuthService } from '../../services/auth.service';


@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.scss']
})
export class UserLoginComponent implements OnInit {

<<<<<<< HEAD
  constructor(private authService: AuthService) { }
  user: any;
  username: String = "";
  password: String = "";
=======
  constructor(private authService: AuthService, private userService: UserService) { }

  username: string = "";
  password: string = "";
  
>>>>>>> 5c22040f43b7c4c83befe034782d7dbb95dea39d

  onClickLogin(username: string, password: string): void {
    let authDto = new AuthDto(this.username, this.password);
    this.authService.login(authDto).subscribe((data) => { localStorage.setItem("currentUser:", JSON.stringify(data)) })
    if (localStorage.getItem('currentUser:') == null) {
      alert("Unable to log in! Check username and password!");
    }
    else {
      window.location.href = "./product-views";
    }
<<<<<<< HEAD
    let response = this.authService.login(authDto, options).subscribe((data) => {
      this.user = data;
      localStorage.setItem("currentLoginUser", JSON.stringify(this.user));
      console.log("returned data: ", data)
    })
=======
>>>>>>> 5c22040f43b7c4c83befe034782d7dbb95dea39d
  }
  onClickRegister(): void {
    window.location.href = "./user-registration"
  }
  
  ngOnInit(): void {
  }

}
