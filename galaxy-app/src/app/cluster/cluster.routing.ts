import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ClusterComponent } from './cluster.component'
import { CmpComponent } from './clusters/cmp/cmp.component';
import { ClustersComponent } from './clusters/clusters.component';
import { HostComponent } from './host/host.component';

const routes: Routes = [
  {
    path: '',
    component: ClusterComponent,
    children: [
      {
        path: '',
        children: [
          {path: 'instances', component: ClustersComponent},
          {path: 'hosts', component: HostComponent},
          {path: 'cmp', component: CmpComponent},
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
export class ClusterRoutingModule { }