import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-sidebar',
  standalone:true,
  imports:[CommonModule],
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css'],
})
export class SidebarComponent {
  menuItems = [
    { icon: 'fa fa-comments', active: true },
    { icon: 'fa fa-users', active: true },
    { icon: 'fa fa-cog', active: true }
  ];
}
