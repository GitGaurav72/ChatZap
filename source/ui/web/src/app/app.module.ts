import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule, DatePipe } from '@angular/common';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { LocalStorageService } from './LocalStorageService';
import { APP_BASE_HREF } from '@angular/common';

// Import your components here
import { AppComponent } from './app.component'; // Example component

@NgModule({
    declarations: [
         // Add your components here
    ],
    imports: [
        CommonModule,
        FormsModule,
        HttpClientModule,
        BrowserModule,
        RouterModule,
        MatToolbarModule,
        MatIconModule,
        HttpClientModule
         // Angular Material icon module
    ],
    providers: [
        { provide: LocationStrategy, useClass: HashLocationStrategy },
        { provide: APP_BASE_HREF, useValue: '/chat_web/' },
        DatePipe,
        LocalStorageService
    ],
        
    bootstrap: [] // Bootstrap the root component
})
export class AppModule { }
