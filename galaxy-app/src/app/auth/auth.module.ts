import { NgModule } from '@angular/core';
import { CommonModule }   from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Http, HttpModule } from '@angular/http';
import {HttpClientModule, HttpClient} from '@angular/common/http';
//import { BrowserModule } from '@angular/platform-browser';
//import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';

import { ClarityModule } from '@clr/angular';

import { AuthMenuService } from './auth-menu.service';
import { AuthRoutingModule } from './auth.routing';
import { AuthComponent } from './auth.component';
import { UserComponent } from './user/user.component';
import { GroupComponent } from './group/group.component';
import { RoleComponent } from './role/role.component';
import { AppsComponent } from './apps/apps.component';
import { PermissionComponent } from './permission/permission.component';


// # Service Provider
import { AppsService } from './apps/apps.service';
import { UserService } from './user/user.service';
import { GroupService } from './group/group.service';
import { PermissionService } from './permission/permission.service';


// AoT requires an exported function for factories
export function HttpLoaderFactory(http: HttpClient) {
    // for development
    // return new TranslateHttpLoader(http, '/start-angular/SB-Admin-BS4-Angular-4/master/dist/assets/i18n/', '.json');
    return new TranslateHttpLoader(http, '/assets/i18n/', '.json');
}
@NgModule({
    declarations: [
        AuthComponent,
        UserComponent,
        GroupComponent,
        RoleComponent,
        AppsComponent,

        PermissionComponent
    ],
    imports: [
        //BrowserModule,
        //BrowserAnimationsModule,
        CommonModule,
        FormsModule,
        AuthRoutingModule,

        ClarityModule
    ],
    providers: [HttpClient, AuthMenuService, 
        AppsService, 
        UserService,
        GroupService,
        PermissionService],
        
    bootstrap: [AuthComponent]
})
export class AuthModule {
}
