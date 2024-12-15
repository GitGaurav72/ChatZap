import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ChatListComponent } from '../chat-list/chat-list.component';
import { ChatWindowComponent } from '../chat-window/chat-window.component';
import { SidebarComponent } from '../sidebar/sidebar.component';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-window',
  standalone: true,
  imports: [ChatListComponent, ChatWindowComponent],
  templateUrl: './window.component.html',
  styleUrl: './window.component.css'
})
export class WindowComponent implements OnInit {



  id!: number;  // Using '!' to assert that the variable will be initialized later
  senderId!: number;
  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {

    this.route.paramMap.subscribe(params => {

    this.id =+params.get('id')!;  // Extract ID from route
  // Extract senderId from route (if present), otherwise set it to this.id
  const extractedSenderId =+params.get('senderId')!;
  this.senderId = extractedSenderId ? +extractedSenderId : this.id;

  console.log('Extracted ID:', this.id);
  console.log('Sender ID:', this.senderId);
});
  }
}
