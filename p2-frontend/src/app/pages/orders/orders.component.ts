import { Component, OnInit } from '@angular/core';
import { OrdersService } from 'src/app/services/orders.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

  public ordersList: any[] = [];

  userId: any;

  constructor(private orderService: OrdersService) { }

  ngOnInit(): void {
    let token = localStorage.getItem("currentUser")
    console.log("inside of ngoninit")
    if (!token) {
      window.alert("user not logged in.")
    } else {
      this.userId = JSON.parse(token)
      console.log("current user", this.userId)
      this.orderService.displayAllOrdersForUser(this.userId).subscribe((res)=>{
      this.ordersList = res;
    });
    }
  }
}
