import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

import { Group } from './group.model';
import { GroupService } from './group.service';

@Component({
	styleUrls: ['./group.component.scss'],
	templateUrl: './group.component.html'
})
export class GroupComponent implements OnInit {
	group: Group = new Group();
    groups: any = [];
    showForm: boolean = false;
    singleSelected: Group;
    showMsg: boolean = false;
    opsMsgType: string = 'alert-info';
    opsMessage: string = "";

	constructor(private service: GroupService) {
		this.read();
	}

	read(): void {
		this.service.read().then(data => {
			console.log(data);
			this.groups = data
		});
	};

	create(): void {
		console.log('create user ...')
		this.showForm = true;
	}

	onSubmit(groupForm: NgForm, group: Group): void {
		console.log(`${group.cn}`)
		this.service.create(group).then(
			res => {
				var response = res.json();
				this.showMsg = true;
				if(!response.success){
					this.opsMsgType = 'alert-danger';
					this.opsMessage = response.message;
				}else {
					this.opsMsgType = 'alert-success';
					this.opsMessage = `User [${group.gidNumber}] create done.`
					
					// reset form
					groupForm.resetForm();

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

		this.group = this.singleSelected;
		this.showForm = true;
	}

	delete(): void{
		let group: Group = this.singleSelected;
		console.log(`Delete Group ${group.cn}`)

		this.service.delete(group).then(
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
					this.opsMessage = `Group [${group.cn}] removed.`
				}
			}
		);
	}

	search(): void {
		console.log(`Group cn: ${this.group.gidNumber}`)
		this.service.search(this.group).then(data => {
			console.log(data);
			this.groups = data
		});
	}

	reset(groupForm: NgForm): void {
		console.log(`${groupForm}`)
		groupForm.resetForm();
		this.service.read();
	}

	ngOnInit(): void {
		console.debug('GroupComponent init ...')
	}
}