import { Component } 	from '@angular/core';
import { Router } 	from '@angular/router';
import { ClusterMenuService } 	from './cluster-menu.service';

@Component({
	selector: 'cluster-app',
	templateUrl: './cluster.component.html',
	styleUrls: ['./cluster.component.scss']
})
export class ClusterComponent {
	case: any;

	constructor(public menuService: ClusterMenuService){
		this.case = menuService.iconMenu;
	}

	get collapse(): boolean {
		return this._collapse;
	}

	set collapse(isCollapse: boolean) {
		this._collapse = isCollapse;
	}

	private _collapse: boolean = false;
}