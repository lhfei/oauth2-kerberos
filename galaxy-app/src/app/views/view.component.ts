import { Component } 	from '@angular/core';
import { Router } 	from '@angular/router';
import { ViewMenuService } 	from './view-menu.service';

@Component({
	selector: 'view-app',
	templateUrl: './view.component.html',
	styleUrls: ['./view.component.scss']
})
export class ViewComponent {
	case: any;

	constructor(public menuService: ViewMenuService){
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