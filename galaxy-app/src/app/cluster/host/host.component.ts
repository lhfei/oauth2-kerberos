import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

import { HostModel } from './host.model';
import { HostService } from './host.service';

@Component({
	styleUrls: ['./host.component.scss'],
	templateUrl: './host.component.html'
})

export class HostComponent {
	host: HostModel = new HostModel();
    hosts: any = [];
    showForm: boolean = false;
    singleSelected: HostModel;
    showMsg: boolean = false;
    opsMsgType: string = 'alert-info';
    opsMessage: string = "";

	constructor(private service: HostService) {
		this.read();
	}

	read(): void {
		this.service.read().then(data => {
			console.log(data);
			this.hosts = data
		});
	};

	create(): void {
		console.log('create user ...')
		this.showForm = true;
	}

	onSubmit(hostForm: NgForm, host: HostModel): void {
		console.log(`${host.hostName}`)
		this.service.create(host).then(
			res => {
				var response = res.json();
				this.showMsg = true;
				if(!response.success){
					this.opsMsgType = 'alert-danger';
					this.opsMessage = response.message;
				}else {
					this.opsMsgType = 'alert-success';
					this.opsMessage = `Cluster [${host.hostName}] create done.`
					
					// reset form
					hostForm.resetForm();

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

		this.host = this.singleSelected;
		this.showForm = true;
	}

	delete(): void{
		let host: HostModel = this.singleSelected;
		console.log(`Delete user ${host.hostName}`)

		this.service.delete(host).then(
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
					this.opsMessage = `User [${host.hostId}] removed.`
				}
			}
		);
	}

	search(): void {
		//console.log(`User cn: ${this.host.uid}`)
		this.service.search(this.host).then(data => {
			console.log(data);
			this.hosts = data
		});
	}

	reset(hostForm: NgForm): void {
		console.log(`${hostForm}`)
		hostForm.resetForm();
		this.service.read();
	}

	ngOnInit(): void {
		console.log('init .')
	}
}

