/*
 * Copyright (c) 2016 VMware, Inc. All Rights Reserved.
 * This software is released under MIT license.
 * The full license information can be found in LICENSE in the root directory of this project.
 */
import { ModuleWithProviders } from '@angular/core/src/metadata/ng_module';
import { Routes, RouterModule } from '@angular/router';

import { WorkstationComponent } from './workstation/workstation.component';
import { AboutComponent } from './about/about.component';
import { HomeComponent } from './home/home.component';
import { ClusterComponent } from './cluster/cluster.component';
import { ViewComponent } from './views/view.component';
import { AuthComponent} from './auth/auth.component';
import { LoginComponent } from './login/login.component';

export const ROUTES: Routes = [
    {path: '', redirectTo: 'auth', pathMatch: 'full'},
    {path: 'home', component: HomeComponent},
    {path: 'workstation/:app', component: WorkstationComponent},
    {path: 'about', component: AboutComponent},
    {path: 'views', loadChildren: './views/view.module#ViewModule'},
    {path: 'cluster', loadChildren: './cluster/cluster.module#ClusterModule'},
    {path: 'auth', loadChildren: './auth/auth.module#AuthModule'},
    {path: 'kms', loadChildren: './kms/kms.module#KmsModule'},
    {path: 'login', component: LoginComponent} 
    //{path: 'auth', component: AuthComponent}
];

export const ROUTING: ModuleWithProviders = RouterModule.forRoot(ROUTES);
