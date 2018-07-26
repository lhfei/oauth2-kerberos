import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import {DomSanitizer} from '@angular/platform-browser';

@Component({
	styleUrls: ['./cmp.component.scss'],
	templateUrl: './cmp.component.html'
})

export class CmpComponent {
	cluster: any = {
		id: -1,
		name: '/'
	}

	constructor(private sanitizer: DomSanitizer,
    	private route: ActivatedRoute,
		private router: Router) {
		
	}

	ngOnInit(){
		this.route.queryParams.subscribe(params => {
			let id:number = params['id']
			let name:string = params['name'];
			console.log(`Cluster ID = ${id}, Name: ${name}`)

			this.cluster.id = id;
			this.cluster.name = name;

		})	
	}
}