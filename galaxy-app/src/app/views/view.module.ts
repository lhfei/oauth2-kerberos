import { NgModule } from '@angular/core';
import { CommonModule }   from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Http, HttpModule } from '@angular/http';
import {HttpClientModule, HttpClient} from '@angular/common/http';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';

import { ClarityModule } from 'clarity-angular';

import { ViewRoutingModule } from './view.routing';
import { ViewComponent } from './view.component';
import { CmpComponent } from './hdfs/cmp/cmp.component';
import { HdfsComponent } from './hdfs/hdfs.component';
import { HiveComponent } from './hive/hive.component';

// # View Cmps
import { HQueryComponent } from './hive/query/hquery.component'; 
import { DBSelectorComponent } from './dbselector/dbselector.component';
import { NgZorroAntdModule } from 'ng-zorro-antd';
import { AceEditorModule } from 'ng2-ace-editor';

// # Service Provider
import { ViewMenuService } from './view-menu.service';
import { HdfsService } from './hdfs/hdfs.service';
import { HiveService } from './hive/hive.service';

// AoT requires an exported function for factories
export function HttpLoaderFactory(http: HttpClient) {
    // for development
    // return new TranslateHttpLoader(http, '/start-angular/SB-Admin-BS4-Angular-4/master/dist/assets/i18n/', '.json');
    return new TranslateHttpLoader(http, '/assets/i18n/', '.json');
}
@NgModule({
    declarations: [
        ViewComponent,
        HdfsComponent,
        CmpComponent,
        HiveComponent,
        HQueryComponent,
        DBSelectorComponent
    ],
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        ViewRoutingModule,

        AceEditorModule,
        NgZorroAntdModule,
        ClarityModule
    ],
    providers: [HttpClient, ViewMenuService, HdfsService, HiveService],
        
    bootstrap: [ViewComponent]
})
export class ViewModule {
}
