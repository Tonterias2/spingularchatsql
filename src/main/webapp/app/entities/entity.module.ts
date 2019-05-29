import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'chat-user',
        loadChildren: './chat-user/chat-user.module#SpingularchatsqlChatUserModule'
      },
      {
        path: 'chat-room',
        loadChildren: './chat-room/chat-room.module#SpingularchatsqlChatRoomModule'
      },
      {
        path: 'chat-room-allowed-user',
        loadChildren: './chat-room-allowed-user/chat-room-allowed-user.module#SpingularchatsqlChatRoomAllowedUserModule'
      },
      {
        path: 'chat-message',
        loadChildren: './chat-message/chat-message.module#SpingularchatsqlChatMessageModule'
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SpingularchatsqlEntityModule {}
