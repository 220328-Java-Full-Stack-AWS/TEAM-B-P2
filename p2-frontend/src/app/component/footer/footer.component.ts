import { Component, OnInit } from '@angular/core';
import { faAddressBook, faPhone, faEnvelope } from '@fortawesome/free-solid-svg-icons';
import { Router } from '@angular/router';
@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {
  faAddressBook = faAddressBook;
  faPhone = faPhone;
  faEnvelope = faEnvelope;
  constructor(private router: Router) { }

  ngOnInit(): void {
  }
}
