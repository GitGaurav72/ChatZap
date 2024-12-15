import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-friend-profile',
  standalone: true,
  imports: [],
  templateUrl: './friend-profile.component.html',
  styleUrl: './friend-profile.component.css'
})
export class FriendProfileComponent {

   // Mock data for now; replace with actual API data in production
   @Input() friend = {
    avatar: 'assets/default-avatar.png', // Path to the avatar image
    firstName: 'John',
    lastName: 'Doe',
    about: 'Loves to travel and code.'
  };
}
