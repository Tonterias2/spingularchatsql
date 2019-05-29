export interface IChatMessage {
  id?: number;
  messageSentAt?: string;
  message?: string;
  isDelivered?: boolean;
  isOffensive?: boolean;
  chatRoomId?: number;
  chatUserId?: number;
}

export class ChatMessage implements IChatMessage {
  constructor(
    public id?: number,
    public messageSentAt?: string,
    public message?: string,
    public isDelivered?: boolean,
    public isOffensive?: boolean,
    public chatRoomId?: number,
    public chatUserId?: number
  ) {
    this.isDelivered = this.isDelivered || false;
    this.isOffensive = this.isOffensive || false;
  }
}
