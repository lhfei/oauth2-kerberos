import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

import { HdfsModel } from './hdfs.model';
import { HdfsService } from './hdfs.service';

@Component({
	styleUrls: ['./hdfs.component.scss'],
	templateUrl: './hdfs.component.html'
})

export class HdfsComponent {
	cluster: HdfsModel = new HdfsModel();
    hdfs: any = [];
    showForm: boolean = false;
    singleSelected: HdfsModel;
    showMsg: boolean = false;
    opsMsgType: string = 'alert-info';
    opsMessage: string = "";

	constructor(private service: HdfsService) {
		this.read();
		this.cluster.securityType = "KERBEROS";
	}

	read(): void {
		this.service.read().then(data => {
			console.log(data);
			this.hdfs = data
		});
	};

	create(): void {
		console.log('create user ...')
		this.showForm = true;
	}

	onSubmit(clusterForm: NgForm, cluster: HdfsModel): void {
		console.log(`${cluster.clusterName}`)
		this.service.create(cluster).then(
			res => {
				var response = res.json();
				this.showMsg = true;
				if(!response.success){
					this.opsMsgType = 'alert-danger';
					this.opsMessage = response.message;
				}else {
					this.opsMsgType = 'alert-success';
					this.opsMessage = `Cluster [${cluster.clusterName}] create done.`
					
					// reset form
					clusterForm.resetForm();

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

		this.cluster = this.singleSelected;
		this.showForm = true;
	}

	delete(): void{
		let cluster: HdfsModel = this.singleSelected;
		console.log(`Delete user ${cluster.clusterName}`)

		this.service.delete(cluster).then(
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
					this.opsMessage = `User [${cluster.clusterId}] removed.`
				}
			}
		);
	}

	search(): void {
		//console.log(`User cn: ${this.cluster.uid}`)
		this.service.search(this.cluster).then(data => {
			console.log(data);
			this.hdfs = data
		});
	}

	reset(clusterForm: NgForm): void {
		console.log(`${clusterForm}`)
		clusterForm.resetForm();
		this.service.read();
	}

	ngOnInit(): void {
		console.log('init .')
	}
}

