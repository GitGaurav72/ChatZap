import { Component, Input, OnInit, ElementRef, ViewChild, AfterViewChecked } from '@angular/core';
import { HttpClient, HttpHeaders, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LocalStorageService } from '../LocalStorageService';
import { UserModel } from '../models/user.model';
import { Message } from '../models/message.model';
import {Router} from '@angular/router';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-friend-profile',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './friend-profile.component.html',
  styleUrl: './friend-profile.component.css',
  providers: [LocalStorageService]
})
export class FriendProfileComponent implements OnInit {
  friendId!: number;
  user: UserModel | undefined;
  @Input() friend = {
    avatar: 'assets/default-avatar.png', // Path to the avatar image
    firstName: '',
    lastName: '',
    about: 'Loves to travel and code.'
  };

  constructor(
    private http: HttpClient,
    private localStorageService: LocalStorageService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    // Retrieve friendId from route parameters
    this.route.params.subscribe(params => {
      this.friendId = +params['friendId']; // Convert to a number using '+'
      console.log('Friend ID:', this.friendId);

      // Fetch user details
      this.fetchUserDetails();
    });
  }

  fetchUserDetails(): void {
    const token = this.localStorageService.get('token'); // Get the JWT token

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });

    const url1 = `http://localhost:8080/chatzap/api/users/detail/${this.friendId}`;
    this.http.get<UserModel>(url1, { headers }).subscribe({
      next: (data: UserModel) => {
        this.user = data;
        console.log('User details:', data);
      },
      error: (error) => {
        console.error('Error fetching user details:', error);
      }
    });
  }
}
