import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthComponent } from './auth.component';
import { UserComponent } from './user/user.component';
import { GroupComponent } from './group/group.component';
import { RoleComponent } from './role/role.component';
import { AppsComponent } from './apps/apps.component';
import { PermissionComponent } from './permission/permission.component';

const routes: Routes = [
  {
    path: '',
    component: AuthComponent,
    children: [
      {
        path: '',
        children: [
          {path: 'apps', component: AppsComponent},
          {path: 'roles', component: RoleComponent},
		      {path: 'users', component: UserComponent},
          {path: 'groups', component: GroupComponent},
          {path: 'permission/:roleType', component: PermissionComponent},
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
export class AuthRoutingModule { }
