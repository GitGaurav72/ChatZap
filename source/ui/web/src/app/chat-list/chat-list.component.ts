import { HttpClient, HttpHeaders, HttpClientModule } from '@angular/common/http';
import { Component, OnInit, Input } from '@angular/core';
import { UserModel, UserModelMsg } from '../models/user.model';
import { CommonModule } from '@angular/common';
import { LocalStorageService } from '../LocalStorageService';
import { Router } from '@angular/router';


@Component({
	selector: 'app-chat-list',
	standalone: true,
	imports: [CommonModule, HttpClientModule],
	templateUrl: './chat-list.component.html',
	styleUrls: ['./chat-list.component.css'],
	providers: [LocalStorageService]
})
export class ChatListComponent implements OnInit {
	@Input() id!: number;
	contacts: UserModelMsg[] = [];
	src : any = '../../assets/image1.png'

	constructor(private _httpClient: HttpClient,
	 private localStorageService: LocalStorageService, private router : Router) { }

	ngOnInit(): void {
		console.log('Received ID:', this.id);
		const token = this.localStorageService.get('token'); // Get the JWT token from localStorage or sessionStorage
		const headers = new HttpHeaders({
			"Content-Type": "application/JSON", 	
			'Authorization': `Bearer ${token}` // Include the token in the Authorization header
		});
		const url = `http://localhost:8080/chatzap/api/users/friends`;
		const params = {
			userId: this.id.toString(),
			sts: 'accepted'
		  };
		this._httpClient.get<UserModelMsg[]>(url, { headers, params })
			.subscribe(response => {
				this.contacts = response;
				this.contacts.forEach((value) => {
					value.lstMsgTm = this.dateTimeFormat(value.lstMsgTm);
				});	
				this.contacts.sort((a, b) => (a.lstMsgTm < b.lstMsgTm ? -1 : 1));			
			}
			, error => {
				console.error('Error fetching friends:', error);
			}
		);
	}


	onContactClick(contact : any, userId : Number) {
		const senderId = contact.id;
		// Navigate to a different route or perform any action
		console.log('Contact clicked:', contact);
		// this.router.navigate([`/window/${userId}/${senderId}`]);  // Correct route with two parameters
		this.router.navigate([`/window/${userId}/${senderId}`]).then(() => {
			// Full page refresh after navigation
			window.location.reload();
		  });
	}

	addFriends(userId: number){
		this.router.navigate([`user/friends`,userId]).then(() => {
		  });
	}

	 dateTimeFormat(timestamp : string) {
		const dateObject = new Date(Number(timestamp)); // Convert the string timestamp to a number
		const currentDate = new Date(); // Get the current date
	  
		// Check if the timestamp corresponds to today's date
		if (
		  dateObject.getDate() === currentDate.getDate() &&
		  dateObject.getMonth() === currentDate.getMonth() &&
		  dateObject.getFullYear() === currentDate.getFullYear()
		) {
		  // Return only the time in HH:mm format
		  return dateObject.toLocaleTimeString('en-US', {
			hour: '2-digit',
			minute: '2-digit',
			hour12: false,
		  });
		} else {
		  // Return only the date in dd/MM/yyyy format
		  return dateObject.toLocaleDateString('en-US', {
			day: '2-digit',
			month: '2-digit',
			year: 'numeric',
		  });
		}
	  }
	  
	  
}


