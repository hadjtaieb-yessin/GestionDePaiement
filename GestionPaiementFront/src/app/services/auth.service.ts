import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { decode } from 'punycode';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) { }

  login(credentials): Observable<any> {
    return this.http.post(environment.API_BASE_URL + 'auth/signin', {
      username: credentials.username,
      password: credentials.password
    }, httpOptions);
  }
  register(user): Observable<any> {
    return this.http.post(environment.API_BASE_URL + 'auth/signup', {
      username: user.username,
      email: user.email,
      password: user.password
    }, httpOptions);
  }

  updatePassword(password:string, newPassword:string): Observable<any>{
    const name=localStorage.getItem('userName');
    const decoded = decode(localStorage.getItem('token'));
    const username = decoded.sub;
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', 'Bearer ' + token);
      // headers['lazyInit'].set(null);
      // headers['lazyInit'].set(null)
      // headers['normalizedNames'].set({'Authorization':'Authorization'});
      console.log(headers);
    return this.http.post(
      environment.API_BASE_URL +'auth/change-password'+'?name='+ name +'&newPassword='+ newPassword +'&password='+password,{ headers: headers }
    );
  }
}
