import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SpingularchatsqlSharedModule } from 'app/shared';
import { HOME_ROUTE, HomeComponent } from './';

import { MalihuScrollbarModule } from 'ngx-malihu-scrollbar';

@NgModule({
  imports: [SpingularchatsqlSharedModule, MalihuScrollbarModule.forRoot(), RouterModule.forChild([HOME_ROUTE])],
  declarations: [HomeComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SpingularchatsqlHomeModule {}
