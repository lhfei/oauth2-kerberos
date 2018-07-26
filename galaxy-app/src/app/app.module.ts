import { BrowserModule } from '@angular/platform-browser';
import { CommonModule }   from '@angular/common';
import { NgModule } from '@angular/core';
import { APP_BASE_HREF } from '@angular/common';
import { environment } from '../environments/environment';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { ClarityModule } from 'clarity-angular';
import { AppComponent } from './app.component';
import { ROUTING } from "./app.routing";

// import { AppMaterialModule } from './app-material.module';

import { NoopAnimationsModule,BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { NgZorroAntdModule } from 'ng-zorro-antd';

import { SafePipe } from './safe.pipe.service';
import { WorkstationComponent } from './workstation/workstation.component';
import { HomeComponent } from "./home/home.component";
import { LoginComponent } from './login/login.component';
import { AboutComponent } from "./about/about.component";
import { AuthComponent } from "./auth/auth.component";
// import { ClusterMenuService } from './cluster/cluster-menu.service';
import { AuthMenuService } from './auth/auth-menu.service';

@NgModule({
    declarations: [
        AppComponent,
        AboutComponent,
        HomeComponent,
        LoginComponent,
        WorkstationComponent,
        SafePipe
    ],
    imports: [
        CommonModule,
        NoopAnimationsModule,
        BrowserAnimationsModule,
        BrowserModule,
        NgZorroAntdModule,
        FormsModule,
        HttpModule,
        ClarityModule,
        ROUTING
    ],
    providers: [AuthMenuService, {provide: APP_BASE_HREF, useValue: environment.baseHref }],
    bootstrap: [AppComponent]
})
export class AppModule {
}
