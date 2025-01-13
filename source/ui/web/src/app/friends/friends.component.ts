import { NgModule } from '@angular/core';
import { HttpClient, HttpHeaders, HttpClientModule } from '@angular/common/http';
import { Component, OnInit, Input } from '@angular/core';
import { UserModel } from '../models/user.model';
import { CommonModule } from '@angular/common';
import { LocalStorageService } from '../LocalStorageService';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-friends',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './friends.component.html',
  styleUrl: './friends.component.css',
  providers:[LocalStorageService]
})
export class FriendsComponent {

    userId!: number;
    friends: UserModel[] = [];
    src : any = '../../assets/image1.png'
    pageSts: string = '';
  
    constructor(private _httpClient: HttpClient,
     private localStorageService: LocalStorageService,  private router : Router, private rout : ActivatedRoute) { }


     ngOnInit(): void {
      // Retrieve friendId from route parameters
      this.rout.params.subscribe(params => {
        this.pageSts='';
        this.userId = +params['userId']; // Convert to a number using '+'
        console.log('user ID:', this.userId);
        
        // Fetch user details
        this.fetchFriendsByUserId();
      });
    }
  
    fetchFriendsByUserId(): void {
      console.log('Received ID:', this.userId);
      const token = this.localStorageService.get('token'); // Get the JWT token from localStorage or sessionStorage
      const headers = new HttpHeaders({
        "Content-Type": "application/JSON", 	
        'Authorization': `Bearer ${token}` // Include the token in the Authorization header
      });
      const url = `http://localhost:8080/chatzap/api/users/${this.userId}/new-friends`;
      this._httpClient.get<UserModel[]>(url, { headers })
        .subscribe(response => {
          this.friends = response;
  
        }
       , error => {
          console.error('Error fetching friends:', error);
        }
      );
    }


  viewSentRequests() {
    console.log('Navigating to Sent Requests...');
    this.pageSts = 'reqSntPage';
    const token = this.localStorageService.get('token'); // Get the JWT token from localStorage or sessionStorage
		const headers = new HttpHeaders({
			"Content-Type": "application/JSON", 	
			'Authorization': `Bearer ${token}` // Include the token in the Authorization header
		});
		const url = `http://localhost:8080/chatzap/api/users/friends`;
		const params = {
			userId: this.userId.toString(),
			sts: 'requested'
		  };
		this._httpClient.get<UserModel[]>(url, { headers, params })
			.subscribe(response => {
				this.friends = response;

			}
			// , error => {
			// 	console.error('Error fetching friends:', error);
			// }
		);
  }

  viewReceivedRequests() {
    console.log('Navigating to Received Requests...');
    this.pageSts = 'reqRcvdPage';
    const token = this.localStorageService.get('token'); // Get the JWT token from localStorage or sessionStorage
		const headers = new HttpHeaders({
			"Content-Type": "application/JSON", 	
			'Authorization': `Bearer ${token}` // Include the token in the Authorization header
		});
		const url = `http://localhost:8080/chatzap/api/users/rcvreq`;
		const params = {
			userId: this.userId.toString(),
			sts: 'requested'
		  };
		this._httpClient.get<UserModel[]>(url, { headers, params })
			.subscribe(response => {
				this.friends = response;

			}
			// , error => {
			// 	console.error('Error fetching friends:', error);
			// }
		);
  }

  sendChatRequest(friendId: number) {
    console.log('Received ID:', friendId);
      const token = this.localStorageService.get('token'); // Get the JWT token from localStorage or sessionStorage
      const headers = new HttpHeaders({
        "Content-Type": "application/JSON", 	
        'Authorization': `Bearer ${token}` // Include the token in the Authorization header
      });
      const url = `http://localhost:8080/chatzap/api/users/${this.userId}/sendReq/${friendId}`;
      this._httpClient.post<string>(url, null, { headers })
      .subscribe(response => {
        console.log(`Chat request sent: ${response}`);
      }, error => {
        console.error('Error sending chat request:', error);
      });

    console.log(`Chat request sent to friend with ID: ${friendId}`);
    
  }

  acceptChatRequest(friendId: number) {
    console.log('Received ID:', friendId);
      const token = this.localStorageService.get('token'); // Get the JWT token from localStorage or sessionStorage
      const headers = new HttpHeaders({
        "Content-Type": "application/JSON", 	
        'Authorization': `Bearer ${token}` // Include the token in the Authorization header
      });
      const url = `http://localhost:8080/chatzap/api/users/${this.userId}/accpReq/${friendId}`;
      this._httpClient.post<string>(url, null, { headers })
      .subscribe(response => {
        console.log(`Chat request sent: ${response}`);
      }, error => {
        console.error('Error sending chat request:', error);
      });

    console.log(`Chat request sent to friend with ID: ${friendId}`);
    
  }

  showProfile(friendId: number) {
    this.router.navigate(['/friend-profile', friendId]);
    console.log(`Viewing profile of friend with ID: ${friendId}`);
    // Add profile view logic here
  }
}
