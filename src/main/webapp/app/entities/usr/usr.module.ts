import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JHipsterTestSharedModule } from 'app/shared';
import {
    UsrComponent,
    UsrDetailComponent,
    UsrUpdateComponent,
    UsrDeletePopupComponent,
    UsrDeleteDialogComponent,
    usrRoute,
    usrPopupRoute
} from './';

const ENTITY_STATES = [...usrRoute, ...usrPopupRoute];

@NgModule({
    imports: [JHipsterTestSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [UsrComponent, UsrDetailComponent, UsrUpdateComponent, UsrDeleteDialogComponent, UsrDeletePopupComponent],
    entryComponents: [UsrComponent, UsrUpdateComponent, UsrDeleteDialogComponent, UsrDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JHipsterTestUsrModule {}
