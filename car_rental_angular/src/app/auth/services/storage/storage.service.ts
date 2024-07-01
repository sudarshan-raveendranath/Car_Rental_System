import { Injectable, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';

const TOKEN = "token";
const USER = "user";

@Injectable({
  providedIn: 'root'
})
export class StorageService {
  private platformId: any; // Declare platformId as an instance property

  constructor(@Inject(PLATFORM_ID) platformId: any) {
    this.platformId = platformId; // Assign the injected platformId to the instance property
  }

  private isBrowser(): boolean {
    return isPlatformBrowser(this.platformId); // Use the instance property to check if it's a browser platform
  }

  saveToken(token: string): void {
    if (this.isBrowser()) {
      window.localStorage.removeItem(TOKEN);
      window.localStorage.setItem(TOKEN, token);
    }
  }

  saveUser(user: any): void {
    if (this.isBrowser()) {
      window.localStorage.removeItem(USER);
      window.localStorage.setItem(USER, JSON.stringify(user));
    }
  }

  getToken() {
    return this.isBrowser() ? window.localStorage.getItem(TOKEN) : null;
  }

  getUser() {
    return this.isBrowser() ? JSON.parse(window.localStorage.getItem(USER) || '{}') : {};
  }

  getUserRole(): string {
    const user = this.getUser();
    if (user == null) return '';
    return user.role;
  }

  isAdminLoggedIn(): boolean {
    if (this.getToken() == null) return false;
    const role: string = this.getUserRole();
    return role == 'ADMIN';
  }

  isCustomerLoggedIn(): boolean {
    if (this.getToken() == null) return false;
    const role: string = this.getUserRole();
    return role == 'CUSTOMER';
  }

  logout(): void {
    if (this.isBrowser()) {
      window.localStorage.removeItem(TOKEN);
      window.localStorage.removeItem(USER);
    }
  }
}
