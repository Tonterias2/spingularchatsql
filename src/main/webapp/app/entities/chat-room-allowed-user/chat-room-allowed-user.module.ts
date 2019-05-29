import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { SpingularchatsqlSharedModule } from 'app/shared';
import {
  ChatRoomAllowedUserComponent,
  ChatRoomAllowedUserDetailComponent,
  ChatRoomAllowedUserUpdateComponent,
  ChatRoomAllowedUserDeletePopupComponent,
  ChatRoomAllowedUserDeleteDialogComponent,
  chatRoomAllowedUserRoute,
  chatRoomAllowedUserPopupRoute
} from './';

const ENTITY_STATES = [...chatRoomAllowedUserRoute, ...chatRoomAllowedUserPopupRoute];

@NgModule({
  imports: [SpingularchatsqlSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ChatRoomAllowedUserComponent,
    ChatRoomAllowedUserDetailComponent,
    ChatRoomAllowedUserUpdateComponent,
    ChatRoomAllowedUserDeleteDialogComponent,
    ChatRoomAllowedUserDeletePopupComponent
  ],
  entryComponents: [
    ChatRoomAllowedUserComponent,
    ChatRoomAllowedUserUpdateComponent,
    ChatRoomAllowedUserDeleteDialogComponent,
    ChatRoomAllowedUserDeletePopupComponent
  ],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SpingularchatsqlChatRoomAllowedUserModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
