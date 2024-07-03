import { Component } from '@angular/core';
import { StorageService } from './auth/services/storage/storage.service';
import { Router, NavigationEnd } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'car_rental_angular';

  isCustomerLoggedIn: boolean = false;
  isAdminLoggedIn: boolean = false;

  constructor(private router: Router, private storageService: StorageService) {}

  ngOnInit() {
    this.updateLoginStatus();

    // Subscribe to router events to update login status on navigation
    this.router.events.subscribe(event => {
      if (event.constructor.name === "NavigationEnd") {
        this.updateLoginStatus();
      }
    });
  }

  updateLoginStatus() {
    this.isCustomerLoggedIn = this.storageService.isCustomerLoggedIn();
    this.isAdminLoggedIn = this.storageService.isAdminLoggedIn();
  }

  logout() {
    this.storageService.logout();
    this.router.navigateByUrl("/login"); // Corrected navigation URL
  }

}
