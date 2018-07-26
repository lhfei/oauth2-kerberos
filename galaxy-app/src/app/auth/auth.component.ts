import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { AuthMenuService } from './auth-menu.service';

@Component({
    selector: 'auth-app',
    templateUrl: './auth.component.html',
    styleUrls: ['./auth.component.scss']
})
export class AuthComponent {
    /*constructor(private router: Router) {
    }*/

    case: any;

    constructor(public authMenuService: AuthMenuService) {
        this.case = this.authMenuService.iconMenu;
    }

    get collapse(): boolean {
        return this._collapse;
    }

    set collapse(value: boolean) {
        this._collapse = value;
    }

    private _collapse: boolean = false;
}
