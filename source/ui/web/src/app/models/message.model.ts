export interface Message {
  messageId: number;
  content: string;
  timestamp: string;
  read: boolean;
  sender: number;
  receiver: number;
}
