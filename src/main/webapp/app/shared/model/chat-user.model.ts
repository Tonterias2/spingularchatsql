import { Moment } from 'moment';
import { IChatMessage } from 'app/shared/model/chat-message.model';
import { IChatRoomAllowedUser } from 'app/shared/model/chat-room-allowed-user.model';
import { IChatRoom } from 'app/shared/model/chat-room.model';

export interface IChatUser {
  id?: number;
  creationDate?: Moment;
  bannedUser?: boolean;
  userId?: number;
  chatMessages?: IChatMessage[];
  chatRoomAllowedUsers?: IChatRoomAllowedUser[];
  chatRooms?: IChatRoom[];
}

export class ChatUser implements IChatUser {
  constructor(
    public id?: number,
    public creationDate?: Moment,
    public bannedUser?: boolean,
    public userId?: number,
    public chatMessages?: IChatMessage[],
    public chatRoomAllowedUsers?: IChatRoomAllowedUser[],
    public chatRooms?: IChatRoom[]
  ) {
    this.bannedUser = this.bannedUser || false;
  }
}
