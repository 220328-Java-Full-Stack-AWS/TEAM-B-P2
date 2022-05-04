import { Component, OnInit } from '@angular/core';
import { AuthService } from "../services/auth.service";

@Component({
  selector: 'app-sign-in-sign-up',
  templateUrl: './sign-in-sign-up.component.html',
  styleUrls: ['./sign-in-sign-up.component.css']
})
export class SignInSignUpComponent implements OnInit
{
  #email: string;
  #password: string;
  authService: AuthService;

  constructor()
  {
    this.#email = "";
    this.#password = "";
    this.authService = new AuthService();
  }

  ngOnInit(): void
  {
  }

  onEmailChange(event: Event): void
  {
    this.#email = (<HTMLInputElement>event.target).value;
  }

  onPasswordChange(event: Event): void
  {
    this.#password = (<HTMLInputElement>event.target).value;

  }

  login(): void
  {
    this.authService.login(this.#email, this.#password);
  }

}
