import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { KmsComponent } from './kms.component'

import { PrincipalComponent } from './principal/principal.component';
import { Krb5Component } from './krb5/krb5.component';
import { KAdminComponent } from './kadmin/kadmin.component';

const routes: Routes = [
  {
    path: '',
    component: KmsComponent,
    children: [
      {
        path: '',
        children: [
          {path: 'principals', component: PrincipalComponent},
          {path: 'krb5s', component: Krb5Component},
          {path: 'kadmin', component: KAdminComponent},
		      {path: '**', redirectTo: 'not-found'}
        ]
      }
    ]
  }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class KmsRoutingModule { }