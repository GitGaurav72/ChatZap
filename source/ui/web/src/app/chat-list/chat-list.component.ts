import { HttpClient, HttpHeaders, HttpClientModule } from '@angular/common/http';
import { Component, OnInit, Input } from '@angular/core';
import { UserModel } from '../models/user.model';
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
	contacts: UserModel[] = [];
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
		const url = `http://localhost:8080/chatzap/api/users/${this.id}/friends`;
		this._httpClient.get<UserModel[]>(url, { headers })
			.subscribe(response => {
				this.contacts = response;

			}
			// , error => {
			// 	console.error('Error fetching friends:', error);
			// }
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

	addFriends(){
		this.router.navigate([`user/friends`]).then(() => {
		  });
	}
	  
}


