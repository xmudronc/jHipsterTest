import { NgModule } from '@angular/core';

import { JHipsterTestSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [JHipsterTestSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [JHipsterTestSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class JHipsterTestSharedCommonModule {}
