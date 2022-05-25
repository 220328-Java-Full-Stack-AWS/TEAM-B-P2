import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-view',
  templateUrl: './user-view.component.html',
  styleUrls: ['./user-view.component.css']
})
export class UserViewComponent implements OnInit {

  constructor() { }


  goToAddress(): void{
    if(localStorage.getItem("currentUser") === null){
      alert("no one logged in");
    }
    else{
      window.location.href = "./user-address.component.html";
    }
  }



  ngOnInit(): void {
  }

}
