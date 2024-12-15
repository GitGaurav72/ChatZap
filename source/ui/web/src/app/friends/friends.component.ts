import { Component } from '@angular/core';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-friends',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './friends.component.html',
  styleUrl: './friends.component.css'
})
export class FriendsComponent {
  friends = [
    { id: 1, name: 'Alice', avatar: 'assets/alice.jpg', status: 'Online' },
    { id: 2, name: 'Bob', avatar: 'assets/bob.jpg', status: 'Offline' },
    { id: 3, name: 'Charlie', avatar: 'assets/charlie.jpg', status: 'Online' }
  ];


  viewSentRequests() {
    console.log('Navigating to Sent Requests...');
    // Add navigation logic here
  }

  viewReceivedRequests() {
    console.log('Navigating to Received Requests...');
    // Add navigation logic here
  }

  sendChatRequest(friendId: number) {
    console.log(`Chat request sent to friend with ID: ${friendId}`);
    // Add chat request logic here
  }

  showProfile(friendId: number) {
    console.log(`Viewing profile of friend with ID: ${friendId}`);
    // Add profile view logic here
  }
}
