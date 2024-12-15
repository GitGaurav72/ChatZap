import { Component, Input, OnInit, ElementRef, ViewChild, AfterViewChecked } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LocalStorageService } from '../LocalStorageService';
import { UserModel } from '../models/user.model';
import { Message } from '../models/message.model';
import {Router} from '@angular/router';

@Component({
  selector: 'app-chat-window',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './chat-window.component.html',
  styleUrls: ['./chat-window.component.css'],
  providers: [LocalStorageService]
})
export class ChatWindowComponent implements OnInit, AfterViewChecked {
  @Input() id!: number;
  @Input() senderId!: number;
  @ViewChild('messagess') messagess!: ElementRef;  // Refers to the container holding chat messages

  messages: any[] = [];  // Store messages array
  newMessage: string = '';  // Bind the input to this variable
  groupedMessages: { date: string, messages: any[] }[] = [];

  user: UserModel | undefined;

  constructor(private http: HttpClient, private localStorageService: LocalStorageService, private router: Router) { }

  ngOnInit(): void {
    const token = this.localStorageService.get('token'); // Get the JWT token from localStorage or sessionStorage

    const headers = new HttpHeaders({
      "Content-Type": "application/JSON",
      'Authorization': `Bearer ${token}` // Include the token in the Authorization header
    });

    const url1 = `http://localhost:8080/chatzap/api/users/detail/${this.senderId}`;
    this.http.get<UserModel>(url1, { headers }).subscribe({
      next: (data: UserModel) => {
        this.user = data;
        console.log('User details:', data);
      },
      error: (error) => {
        console.error('Error fetching user details:', error);
      }
    });

    const url = `http://localhost:8080/chatzap/user/api/${this.id}/Message/${this.senderId}`;
    this.http.get<any[]>(url, { headers }).subscribe({
      next: (data: any[]) => {
        // Grouping messages by date and reversing
        const groupedMessagesObj = data.reduce((acc, message) => {
          const messageDate = new Date(message.timestamp).toLocaleDateString();
          const messageTime = new Date(message.timestamp).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });

          if (!acc[messageDate]) {
            acc[messageDate] = [];
          }

          acc[messageDate].push({
            text: message.content,
            time: messageTime,
            sent: message.senderId === this.id
          });

          return acc;
        }, {});

        // Reverse the order of messages and date groups
        this.groupedMessages = Object.keys(groupedMessagesObj).map(date => ({
          date,
          messages: groupedMessagesObj[date].reverse() // Reverse messages within each date group
        })).reverse(); // Reverse the entire group to show the latest first

        console.log('Grouped Messages by Date:', this.groupedMessages);
      },
      error: (error) => {
        console.error('Error fetching messages:', error);
      }
    });
  }

  // Scroll to the bottom of the message container
  scrollToBottom(): void {
    try {
      this.messagess.nativeElement.scrollTop = this.messagess.nativeElement.scrollHeight;
    } catch (err) {
      console.error('Scroll failed:', err);
    }
  }

  // Called after every view check (to handle changes after rendering)
  ngAfterViewChecked(): void {
    this.scrollToBottom();  // Automatically scroll after each view update
  }

// Handle sending a message
sendMessage() {
  if (this.newMessage.trim()) {
    const currentDate = new Date().toLocaleDateString();
    const newMessageObj = {
      text: this.newMessage,
      time: new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' }),
      sent: true
    };
    const token = this.localStorageService.get('token'); // Get the JWT token from localStorage or sessionStorage

    const headers = new HttpHeaders({
      "Content-Type": "application/JSON",
      'Authorization': `Bearer ${token}` // Include the token in the Authorization header
    });

    const url1 = `http://localhost:8080/chatzap/user/api/${this.id}/sendMsg/${this.senderId}`;
    const body = this.newMessage ;

    this.http.post<Message>(url1, body, { headers }).subscribe({
      next: (data: Message) => {
        // `data` is typed as `Message`, so you can access properties like `data.content`
        console.log('Received message:', data);
        // You can also assign `data` to a message variable if you need to store it
      },
      error: (error) => {
        console.error('Error sending message:', error);
      }
    });

    // Clone `groupedMessages` to trigger change detection
    let updatedGroupedMessages = [...this.groupedMessages];
    
    // Check if there's already a group for today's date
    const todayGroup = updatedGroupedMessages.find(group => group.date === currentDate);

    if (todayGroup) {
      // If today's group exists, add the message to the end (bottom) of today's messages
      todayGroup.messages.push(newMessageObj);
    } else {
      // Otherwise, create a new group for today's date at the end of groupedMessages
      updatedGroupedMessages.push({
        date: currentDate,
        messages: [newMessageObj]
      });
    }

    // Reassign `groupedMessages` to trigger Angular's change detection
    this.groupedMessages = updatedGroupedMessages;

    // Reset the input field and scroll to the bottom
    console.log('Sending message:', this.newMessage);
    this.newMessage = '';  // Clear the input
    this.scrollToBottom();  // Scroll to the bottom after sending the message
  }
}
showProfile(friendId: number) {
  this.router.navigate(['/friend-profile', friendId]);
}

}

