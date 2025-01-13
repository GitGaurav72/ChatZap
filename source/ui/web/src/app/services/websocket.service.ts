// // src/app/services/websocket.service.ts
// import { Injectable } from '@angular/core';
// import { Stomp } from '@stomp/stompjs';
// import * as SockJS from 'sockjs-client';
// import { Subject } from 'rxjs';

// @Injectable({
//   providedIn: 'root'
// })
// export class WebSocketService {
//   private stompClient: any;
//   private messageSubject = new Subject<any>();

//   connect() {
//     const SockJS = require('sockjs-client');
//     const socket = new SockJS('http://localhost:8080/ws');
//     this.stompClient = Stomp.over(socket);

//     this.stompClient.connect({}, (frame: any) => {
//       console.log('Connected: ' + frame);
//       this.stompClient.subscribe('/topic/messages', (message: any) => {
//         this.messageSubject.next(JSON.parse(message.body));
//       });
//     });
//   }

//   sendMessage(destination: string, message: any) {
//     this.stompClient.send(destination, {}, JSON.stringify(message));
//   }

//   onNewMessage() {
//     return this.messageSubject.asObservable();
//   }
// }



import { Injectable } from '@angular/core';
import { Client, IMessage } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {
  private stompClient: Client;

  constructor() {
    this.stompClient = new Client({
      brokerURL: 'ws://localhost:8080/chatzap/ws', // Replace with your WebSocket endpoint
      webSocketFactory: () => new SockJS('http://localhost:8080/chatzap/ws'),
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
      debug: (msg: string) => console.log(msg),
    });
  }

  connect(onMessage: (message: IMessage) => void) {
    this.stompClient.onConnect = () => {
      console.log('Connected to WebSocket');
      this.stompClient.subscribe('/user/queue/messages', onMessage);
    };

    this.stompClient.activate();
  }

  sendMessage(destination: string, body: any) {
    if (this.stompClient.connected) {
      this.stompClient.publish({ destination, body: JSON.stringify(body) });
    }
  }

  disconnect() {
    if (this.stompClient.connected) {
      this.stompClient.deactivate();
    }
  }
}
