import { Component, OnInit } from '@angular/core';
import { PermissionService } from './permission.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { Observable } from 'rxjs/Observable';

import { UserService } from '../user/user.service';

import { Person } from '../user/person.model';
import { Permission } from './permission.model';

@Component({
    styleUrls: ['./permission.component.scss'],
    templateUrl: './permission.component.html'
})
export class PermissionComponent implements OnInit {
	roleType: string ;
	permission$: Observable<Permission>;

    permission: Permission = new Permission();
    user: Person = new Person();
    users: any = [];
    showForm: boolean = false;
    selected: Person;

	constructor(private service: PermissionService, 
		private userService: UserService,
		private route: ActivatedRoute,
		private router: Router) {
	}

	create(): void {
		console.log('create permission ...')

		this.showForm = true;
	}

	onSubmit(permission: Permission): void {

	}

	update(): void{

	}

	delete(): void{

	}

	ngOnInit(){

		this.route.params.subscribe(params => {
			this.roleType = params['roleType']
			console.log(`${this.roleType}`)

			this.userService.read().then(data => {
				console.log(data);
				this.users = data
			})
		})	
      
	}
}