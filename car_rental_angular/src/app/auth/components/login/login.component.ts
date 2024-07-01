import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { StorageService } from '../../services/storage/storage.service';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  isSpinning: boolean = false;
  loginForm!: FormGroup;

  constructor(private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private storageService: StorageService, // Inject StorageService
    private message: NzMessageService
  ) { }

  ngOnInit() {
    this.loginForm = this.fb.group({
      email: [null, [Validators.required, Validators.email]],
      password: [null, [Validators.required]]
    });
  }

  login() {
    console.log(this.loginForm.value);
    this.authService.login(this.loginForm.value).subscribe((res) => {
      console.log(res);
      if (res.userId != null) {
        const user = {
          id: res.userId,
          role: res.userRole
        }
        this.storageService.saveUser(user); // Use instance method
        this.storageService.saveToken(res.jwt); // Use instance method
        if (this.storageService.isAdminLoggedIn()) { // Use instance method
          this.router.navigateByUrl("/admin/dashboard");
        } else if (this.storageService.isCustomerLoggedIn()) { // Use instance method
          this.router.navigateByUrl("/customer/dashboard");
        } else {
          this.message.error("Bad credentials", { nzDuration: 3000 });
        }
      }
    });
  }

}
