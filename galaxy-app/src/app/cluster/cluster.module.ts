import { NgModule } from '@angular/core';
import { CommonModule }   from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Http, HttpModule } from '@angular/http';
import {HttpClientModule, HttpClient} from '@angular/common/http';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';

import { MatCardModule,  MatTabsModule, MatIconModule } from '@angular/material';
import { ClarityModule } from '@clr/angular';

import { ClusterRoutingModule } from './cluster.routing';
import { ClusterComponent } from './cluster.component';
import { CmpComponent } from './clusters/cmp/cmp.component';
import { ClustersComponent } from './clusters/clusters.component';
import { HostComponent } from './host/host.component';

// # Service Provider
import { ClusterMenuService } from './cluster-menu.service';
import { ClustersService } from './clusters/clusters.service';
import { HostService } from './host/host.service';

// AoT requires an exported function for factories
export function HttpLoaderFactory(http: HttpClient) {
    // for development
    // return new TranslateHttpLoader(http, '/start-angular/SB-Admin-BS4-Angular-4/master/dist/assets/i18n/', '.json');
    return new TranslateHttpLoader(http, '/assets/i18n/', '.json');
}
@NgModule({
    declarations: [
        ClusterComponent,
        ClustersComponent,
        CmpComponent,
        HostComponent
    ],
    imports: [
        CommonModule,
        FormsModule,
        ClusterRoutingModule,

        MatIconModule,
        MatCardModule,
        MatTabsModule,
        // MatButtonModule,
        // MatCheckboxModule,
        // NoopAnimationsModule,
        // BrowserAnimationsModule,
        ClarityModule
    ],
    providers: [HttpClient, ClusterMenuService, ClustersService, HostService],
        
    bootstrap: [ClusterComponent]
})
export class ClusterModule {
}
