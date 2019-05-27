import { NgbDateAdapter } from '@ng-bootstrap/ng-bootstrap';
import { NgbDateMomentAdapter } from './util/datepicker-adapter';

import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import {
  SpingularchatsqlSharedLibsModule,
  SpingularchatsqlSharedCommonModule,
  JhiLoginModalComponent,
  HasAnyAuthorityDirective,
  ChatService
} from './';

@NgModule({
  imports: [SpingularchatsqlSharedLibsModule, SpingularchatsqlSharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  providers: [{ provide: NgbDateAdapter, useClass: NgbDateMomentAdapter }, ChatService],
  entryComponents: [JhiLoginModalComponent],
  exports: [SpingularchatsqlSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SpingularchatsqlSharedModule {
  static forRoot() {
    return {
      ngModule: SpingularchatsqlSharedModule
    };
  }
}
