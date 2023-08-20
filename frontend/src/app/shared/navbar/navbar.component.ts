import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/common/user';
import { AuthService } from 'src/app/services/auth.service';
import { LoginService } from 'src/app/services/login.service';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {

  isLoggedIn!: boolean;
  name: string = '';

  constructor(
    private router: Router,
    private authService: AuthService,
    private loginService: LoginService
    ) { }

  ngOnInit() {
    this.isLoggedIn = this.authService.isAuthenticated();
    this.name = this.getName();
    this.loginService.getLoginObservable().subscribe((loggedIn: boolean) => {
      this.isLoggedIn = loggedIn;
      this.name = loggedIn ? this.getName() : '';
    });
  }

  getName(): string {
    const currentUser = this.authService.getCurrentUser();
    return currentUser ? currentUser.name : '';
  }


  getPatients(){
    this.router.navigate(['/patients']);
  }
  login() {
    this.router.navigate(['/auth-login']);
  }
  register(){
    this.router.navigate(['/register']);
  }

  logout(): void {
    this.authService.logout();
    location.reload();
    this.router.navigate(['/auth-login']);
  }
}

