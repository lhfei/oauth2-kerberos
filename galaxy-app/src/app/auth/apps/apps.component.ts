import { Component } from '@angular/core';

import { AppsService } from './apps.service';

@Component({
	styleUrls: ['./apps.component.scss'],
	templateUrl: './apps.component.html'
})

export class AppsComponent {
	apps: any = [];

	constructor(private service: AppsService) {
		service.read().then(data => {
			console.log(data);
			this.apps = data
		});
	}
}

