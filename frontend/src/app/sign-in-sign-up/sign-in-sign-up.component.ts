import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sign-in-sign-up',
  templateUrl: './sign-in-sign-up.component.html',
  styleUrls: ['./sign-in-sign-up.component.css']
})
export class SignInSignUpComponent implements OnInit
{
  #email: string;
  #password: string;

  constructor()
  {
    this.#email = "";
    this.#password = "";
  }

  ngOnInit(): void
  {
  }

  onEmailChange(event: Event): void
  {
    this.#email = (<HTMLInputElement>event.target).value;
    console.log(this.#email);
  }

  onPasswordChange(event: Event): void
  {
    this.#password = (<HTMLInputElement>event.target).value;
    console.log(this.#password);
  }

}
