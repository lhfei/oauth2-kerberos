import { NgModule } from '@angular/core';
import { CommonModule }   from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Http, HttpModule } from '@angular/http';
import {HttpClientModule, HttpClient} from '@angular/common/http';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { AceEditorModule } from 'ng2-ace-editor';

import { ClarityModule } from '@clr/angular';

import { KmsRoutingModule } from './kms.routing';
import { KmsComponent } from './kms.component';
import { PrincipalComponent } from './principal/principal.component';
import { Krb5Component } from './krb5/krb5.component';
import { KAdminComponent } from './kadmin/kadmin.component';


// # Service Provider
import { KmsMenuService } from './kms-menu.service';
import { PrincipalService } from './principal/principal.service';
import { Krb5Service } from './krb5/krb5.service';

// AoT requires an exported function for factories
export function HttpLoaderFactory(http: HttpClient) {
    // for development
    // return new TranslateHttpLoader(http, '/start-angular/SB-Admin-BS4-Angular-4/master/dist/assets/i18n/', '.json');
    return new TranslateHttpLoader(http, '/assets/i18n/', '.json');
}
@NgModule({
    declarations: [
        KmsComponent,
        PrincipalComponent,
        Krb5Component,
        KAdminComponent
    ],
    imports: [
        CommonModule,
        FormsModule,
        KmsRoutingModule,
        AceEditorModule,
        ClarityModule
    ],
    providers: [HttpClient, KmsMenuService, PrincipalService, Krb5Service],
        
    bootstrap: [KmsComponent]
})
export class KmsModule {
}
