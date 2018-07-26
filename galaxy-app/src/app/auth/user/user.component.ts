import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserService } from './user.service';

import { Person } from './person.model';

@Component({
    styleUrls: ['./user.component.scss'],
    templateUrl: './user.component.html'
})
export class UserComponent implements OnInit {
    person: Person = new Person();
    persons: any = [];
    showForm: boolean = false;
    singleSelected: Person;
    showMsg: boolean = false;
    opsMsgType: string = 'alert-info';
    opsMessage: string = "";

	constructor(private service: UserService) {
		this.read();
	}

	read(): void {
		this.service.read().then(data => {
			console.log(data);
			this.persons = data
		});
	};

	create(): void {
		console.log('create user ...')
		this.showForm = true;
	}

	onSubmit(personForm: NgForm, person: Person): void {
		console.log(`${person.cn}`)
		this.service.create(person).then(
			res => {
				var response = res.json();
				this.showMsg = true;
				if(!response.success){
					this.opsMsgType = 'alert-danger';
					this.opsMessage = response.message;
				}else {
					this.opsMsgType = 'alert-success';
					this.opsMessage = `User [${person.uid}] create done.`
					
					// reset form
					personForm.resetForm();

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

		this.person = this.singleSelected;
		this.showForm = true;
	}

	delete(): void{
		let person: Person = this.singleSelected;
		console.log(`Delete user ${person.cn}`)

		this.service.delete(person).then(
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
					this.opsMessage = `User [${person.uid}] removed.`
				}
			}
		);
	}

	search(): void {
		//console.log(`User cn: ${this.person.uid}`)
		this.service.search(this.person).then(data => {
			console.log(data);
			this.persons = data
		});
	}

	reset(personForm: NgForm): void {
		console.log(`${personForm}`)
		personForm.resetForm();
		this.service.read();
	}

	ngOnInit(): void {
		console.log('init .')
	}
}