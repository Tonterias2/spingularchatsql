import { Moment } from 'moment';
import { IChatMessage } from 'app/shared/model/chat-message.model';
import { IChatRoomAllowedUser } from 'app/shared/model/chat-room-allowed-user.model';
import { IChatUser } from 'app/shared/model/chat-user.model';

export interface IChatRoom {
  id?: number;
  creationDate?: Moment;
  roomName?: string;
  roomDescription?: string;
  privateRoom?: boolean;
  chatMessages?: IChatMessage[];
  chatRoomAllowedUsers?: IChatRoomAllowedUser[];
  chatUsers?: IChatUser[];
}

export class ChatRoom implements IChatRoom {
  constructor(
    public id?: number,
    public creationDate?: Moment,
    public roomName?: string,
    public roomDescription?: string,
    public privateRoom?: boolean,
    public chatMessages?: IChatMessage[],
    public chatRoomAllowedUsers?: IChatRoomAllowedUser[],
    public chatUsers?: IChatUser[]
  ) {
    this.privateRoom = this.privateRoom || false;
  }
}
