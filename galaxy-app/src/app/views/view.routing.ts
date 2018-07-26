import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ViewComponent } from './view.component'
import { CmpComponent } from './hdfs/cmp/cmp.component';
import { HdfsComponent } from './hdfs/hdfs.component';
import { HiveComponent } from './hive/hive.component';

const routes: Routes = [
  {
    path: '',
    component: ViewComponent,
    children: [
      {
        path: '',
        children: [
          {path: 'hdfs', component: HdfsComponent},
          {path: 'hive', component: HiveComponent},
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
export class ViewRoutingModule { }