import { Component } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, MatToolbarModule, MatIconModule, MatCardModule], // Import the MatToolbarModule directly
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'], // Correct the typo from styleUrl to styleUrls
})
export class HomeComponent {
  features = [
    { icon: 'message', title: 'Instant Messaging', description: 'Communicate in real-time with anyone, anywhere.' },
    { icon: 'group', title: 'Group Chats', description: 'Start conversations with multiple people at once.' },
    { icon: 'attach_file', title: 'File Sharing', description: 'Share documents, images, and more instantly.' },
    { icon: 'lock', title: 'Secure Communication', description: 'Your conversations are private and secure with ChatZap.' }
  ];

  steps = [
    { title: 'Step 1: Sign Up', description: 'Create your free account to get started.' },
    { title: 'Step 2: Create a Chat', description: 'Start a new conversation with anyone.' },
    { title: 'Step 3: Invite Friends', description: 'Invite others to join your chat.' },
    { title: 'Step 4: Start Chatting', description: 'Enjoy seamless communication.' }
  ];

  testimonials = [
    { quote: 'ChatZap has transformed the way our team communicates.', author: 'Happy User' },
    { quote: 'A must-have tool for remote teams!', author: 'Satisfied Client' }
  ];

  pricingPlans = [
    { name: 'Free', price: '$0/month', description: 'Basic features for individuals', buttonText: 'Sign Up Free' },
    { name: 'Pro', price: '$9.99/month', description: 'Advanced features for professionals', buttonText: 'Get Pro' },
    { name: 'Enterprise', price: 'Contact us', description: 'Custom solutions for businesses', buttonText: 'Contact Sales' }
  ];
}
