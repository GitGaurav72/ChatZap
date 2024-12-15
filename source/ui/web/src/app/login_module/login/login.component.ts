import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { ReactiveFormsModule } from '@angular/forms';  // Import this for reactive forms
import { LocalStorageService } from '../../LocalStorageService';
import { isPlatformBrowser } from '@angular/common';
import { Inject, PLATFORM_ID } from '@angular/core';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    MatToolbarModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    ReactiveFormsModule,
    HttpClientModule,
    // Import ReactiveFormsModule for form controls
  ],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [LocalStorageService]
})
export class LoginComponent {

  loginForm: FormGroup;


  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private router: Router,
    private localStorageService: LocalStorageService
  ) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      const loginData = {
        username: this.loginForm.get('username')?.value,
        password: this.loginForm.get('password')?.value
      };

      // Send a POST request to the login API
      this.http.post('http://localhost:8080/chatzap/api/users/login', loginData).subscribe(
        (response: any) => {
          console.log('Login successful', response);

          // Assuming the response contains a JWT token
          const token = response.token;
          const id = response.id;
          /*console.log(token);*/
          // Store the JWT token in localStorage (or sessionStorage)
          if (typeof window !== 'undefined') {
					console.log('we are running on the client')
				} else {
					console.log('we are running on the server');
				}
		if (typeof window !== 'undefined') {
          this.localStorageService.set('token', token);
          }

          // Navigate to a protected page, e.g., dashboard
          this.router.navigate(['/window', id, id]);
        },
        (error) => {
          console.error('Login failed', error);
          alert('Login failed: Invalid username or password');
        }
      );
    } else {
      console.error('Form is invalid');
    }
  }
}
