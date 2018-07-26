import { Component } 	from '@angular/core';
import { Router } 	from '@angular/router';
import { KmsMenuService } 	from './kms-menu.service';

@Component({
	selector: 'kms-app',
	templateUrl: './kms.component.html',
	styleUrls: ['./kms.component.scss']
})
export class KmsComponent {
	case: any;

	constructor(public menuService: KmsMenuService){
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