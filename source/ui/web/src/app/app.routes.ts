import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ChatListComponent } from './chat-list/chat-list.component';
import { HomeComponent } from './home/home.component';
import { WindowComponent } from './window/window.component';
import { LoginComponent } from './login_module/login/login.component';
import { SignupComponent } from './login_module/signup/signup.component';
import { FriendsComponent } from './friends/friends.component';
import { FriendProfileComponent } from './friend-profile/friend-profile.component';

export const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' }, // Default route to 'home'
  { path: 'home', component: HomeComponent },
  { path: 'window/:id/:senderId', component: WindowComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'user/friends', component: FriendsComponent },
  { path: 'friend-profile/:id', component: FriendProfileComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule],
})
export class AppRoutingModule {}
