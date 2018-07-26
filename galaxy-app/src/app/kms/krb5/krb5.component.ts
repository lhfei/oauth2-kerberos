import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

import { Krb5Model } from './krb5.model';
import { Krb5Service } from './krb5.service';

@Component({
	styleUrls: ['./krb5.component.scss'],
	templateUrl: './krb5.component.html'
})

export class Krb5Component {
	krb5: Krb5Model = new Krb5Model();
    krb5s: any = [];
    showForm: boolean = false;
    singleSelected: Krb5Model;
    showMsg: boolean = false;
    opsMsgType: string = 'alert-info';
    opsMessage: string = "";

	constructor(private service: Krb5Service) {
		this.read();
	}

	read(): void {
		this.service.read().then(data => {
			console.log(data);
			this.krb5s = data
		});
	};

	create(): void {
		console.log('create user ...')
		this.showForm = true;
	}

	onSubmit(krb5Form: NgForm, krb5: Krb5Model): void {
		console.log(`${krb5.realm}`)
		this.service.create(krb5).then(
			res => {
				var response = res.json();
				this.showMsg = true;
				if(!response.success){
					this.opsMsgType = 'alert-danger';
					this.opsMessage = response.message;
				}else {
					this.opsMsgType = 'alert-success';
					this.opsMessage = `Cluster [${krb5.realm}] create done.`
					
					// reset form
					krb5Form.resetForm();

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

		this.krb5 = this.singleSelected;
		this.showForm = true;
	}

	delete(): void{
		let krb5: Krb5Model = this.singleSelected;
		console.log(`Delete user ${krb5.realm}`)

		this.service.delete(krb5).then(
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
					this.opsMessage = `User [${krb5.krb5Id}] removed.`
				}
			}
		);
	}

	search(): void {
		//console.log(`User cn: ${this.krb5.uid}`)
		this.service.search(this.krb5).then(data => {
			console.log(data);
			this.krb5s = data
		});
	}

	reset(krb5Form: NgForm): void {
		console.log(`${krb5Form}`)
		krb5Form.resetForm();
		this.service.read();
	}

	ngOnInit(): void {
		console.log('init .')
	}
}

