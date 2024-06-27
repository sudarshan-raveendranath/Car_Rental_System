import { NgModule } from '@angular/core'
import {NzSpinModule} from 'ng-zorro-antd/spin';
import {NzButtonModule} from 'ng-zorro-antd/button';
import {NzFormModule} from 'ng-zorro-antd/form';
import {NzInputModule} from 'ng-zorro-antd/input';
import {NzLayoutModule} from 'ng-zorro-antd/layout';

@NgModule ({
    exports: [
        NzSpinModule,
        NzButtonModule,
        NzFormModule,
        NzInputModule,
        NzLayoutModule
    ]
})

export class NgZorroImportsModule {}