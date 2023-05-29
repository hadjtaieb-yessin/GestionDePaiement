import { Component, OnInit, OnDestroy } from '@angular/core';
import { TokenStorageService } from '../../services/token-storage.service';
import { AuthService } from '../../services/auth.service';
import { UserService } from '../../services/user.service';
import { ActivatedRoute } from '@angular/router';
import { environment } from 'src/environments/environment';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit, OnDestroy {
  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  currentUser: any;
  constructor(private authService: AuthService, private tokenStorage: TokenStorageService, private route: ActivatedRoute, private userService: UserService) {}

 
  ngOnInit(): void {
    const token: string = this.route.snapshot.queryParamMap.get('token');
    const error: string = this.route.snapshot.queryParamMap.get('error');
      if (this.tokenStorage.getToken()) {
        this.isLoggedIn = true;
        this.currentUser = this.tokenStorage.getUser();
      }
      else if(token){
        this.tokenStorage.saveToken(token);
        this.userService.getCurrentUser().subscribe(
              data => {
                this.login(data);
              },
              err => {
                this.errorMessage = err.error.message;
                this.isLoginFailed = true;
              }
          );
      }
      else if(error){
        this.errorMessage = error;
        this.isLoginFailed = true;
      }
    }
  
    onSubmit(): void {
      this.authService.login(this.form).subscribe(
        data => {
          this.tokenStorage.saveToken(data.accessToken);
          this.login(data.user);
        },
        err => {
          this.errorMessage = err.error.message;
          this.isLoginFailed = true;
        }
      );
    }
  
    login(user): void {
    this.tokenStorage.saveUser(user);
    this.isLoginFailed = false;
    this.isLoggedIn = true;
    this.currentUser = this.tokenStorage.getUser();
      window.location.reload();
    }
  ngOnDestroy() {
  }

}
