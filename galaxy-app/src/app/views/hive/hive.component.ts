import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

import { DatabaseModel } from '../dbselector/database.module';
import { HiveModel } from './hive.model';
import { HiveService } from './hive.service';

@Component({
	styleUrls: ['./hive.component.scss'],
	templateUrl: './hive.component.html'
})

export class HiveComponent implements OnInit {
	hive: HiveModel = new HiveModel();
    hives: any = [];
    showForm: boolean = false;
    singleSelected: HiveModel;

    // For Hive View
    databases = [
    	{ dbName: 'default', dbId: 1000},
    	{ dbName: 'lhfei', dbId: 1001 },
		{ dbName: 'cloud', dbId: 1002 }
	];

	constructor(private service: HiveService) {
		this.read();
	}

	ngOnInit(): void {
	}

	read(): void {
		this.service.read().then(data => {
			console.log(data);
			this.hives = data
		});
	};

	create(): void {
		console.log('create user ...')
		this.showForm = true;
	}

	onSubmit(hiveForm: NgForm, hive: HiveModel): void {
		console.log(`${hive.hiveName}`)
		this.service.create(hive).then(
			res => {
			}
		);
	}

	update(): void{
		//this.service.update(user)

		this.hive = this.singleSelected;
		this.showForm = true;
	}

	delete(): void{
		let hive: HiveModel = this.singleSelected;
		console.log(`Delete user ${hive.hiveName}`)

		this.service.delete(hive).then(
			res => {

			}
		);
	}

	search(): void {
		//console.log(`User cn: ${this.hive.uid}`)
		this.service.search(this.hive).then(data => {
			console.log(data);
			this.hives = data
		});
	}

	reset(hiveForm: NgForm): void {
		console.log(`${hiveForm}`)
		hiveForm.resetForm();
		this.service.read();
	}
}

