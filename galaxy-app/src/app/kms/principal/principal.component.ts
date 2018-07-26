import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

import { PrincipalModel } from './principal.model';
import { PrincipalService } from './principal.service';

@Component({
	styleUrls: ['./principal.component.scss'],
	templateUrl: './principal.component.html'
})

export class PrincipalComponent {
	principal: PrincipalModel = new PrincipalModel();
    principals: any = [];
    showForm: boolean = false;
    singleSelected: PrincipalModel;
    showMsg: boolean = false;
    opsMsgType: string = 'alert-info';
    opsMessage: string = "";

	constructor(private service: PrincipalService) {
		this.read();
	}

	read(): void {
		this.service.read().then(data => {
			console.log(data);
			this.principals = data
		});
	};

	create(): void {
		console.log('create user ...')
		this.showForm = true;
	}

	onSubmit(principalForm: NgForm, principal: PrincipalModel): void {
		console.log(`${principal.principalName}`)
		this.service.create(principal).then(
			res => {
				var response = res.json();
				this.showMsg = true;
				if(!response.success){
					this.opsMsgType = 'alert-danger';
					this.opsMessage = response.message;
				}else {
					this.opsMsgType = 'alert-success';
					this.opsMessage = `Cluster [${principal.principalName}] create done.`
					
					// reset form
					principalForm.resetForm();

					// reload data
					this.read();

					// hidden modal
					this.showForm = false;
				}
			}
		);
	}

	update(): void{
		//this.service.update(user)

		this.principal = this.singleSelected;
		this.showForm = true;
	}

	delete(): void{
		let principal: PrincipalModel = this.singleSelected;
		console.log(`Delete user ${principal.principalName}`)

		this.service.delete(principal).then(
			res => {
				var response = res.json();
				this.showMsg = true;
				if(!response.success){
					this.opsMsgType = 'alert-danger';
					this.opsMessage = response.message;
				}else {
					// reload data
					this.read();
					this.opsMsgType = 'alert-success';
					this.opsMessage = `User [${principal.principalId}] removed.`
				}
			}
		);
	}

	search(): void {
		//console.log(`User cn: ${this.principal.uid}`)
		this.service.search(this.principal).then(data => {
			console.log(data);
			this.principals = data
		});
	}

	reset(principalForm: NgForm): void {
		console.log(`${principalForm}`)
		principalForm.resetForm();
		this.service.read();
	}

	ngOnInit(): void {
		console.log('init .')
	}
}

