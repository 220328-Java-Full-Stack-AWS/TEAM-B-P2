import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { AuthDto, AuthService } from '../../services/auth.service';
import { HttpHeaders } from '@angular/common/http';


@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.scss']
})
export class UserLoginComponent implements OnInit {


  constructor(private authService: AuthService, private userService: UserService) { }
  user: any;
  username: string = "";
  password: string = "";

  onClickLogin(username: string, password: string): void {
    let authDto = new AuthDto(this.username, this.password);
    let options = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) }

    this.authService.login(authDto, options)
      .subscribe((data) => {
        localStorage.setItem("currentUser", JSON.stringify(data));

        if (localStorage.getItem('currentUser') == null) {
          alert("Unable to log in! Check username and password!");
        }
        else {
          window.location.href = "./";
        }


      });

      
  }
  onClickRegister(): void {
    window.location.href = "./user-registration"
  }
  
  ngOnInit(): void {
  }

}
