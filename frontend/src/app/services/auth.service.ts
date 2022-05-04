import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService
{

  constructor() { }

  login(username: string, password: string)
  {
    if (username === 'test' && password === '123456') {
      localStorage.setItem('username', username);
      return true;
    }
    return false;
  }
}
