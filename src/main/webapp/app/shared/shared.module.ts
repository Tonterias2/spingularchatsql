import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { SpingularchatsqlSharedLibsModule, SpingularchatsqlSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [SpingularchatsqlSharedLibsModule, SpingularchatsqlSharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
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
