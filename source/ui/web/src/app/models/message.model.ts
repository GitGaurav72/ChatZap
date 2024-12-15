export interface Message {
  messageId: number;
  content: string;
  timestamp: string;
  read: boolean;
  senderId: number;
  receiverId: number;
}
