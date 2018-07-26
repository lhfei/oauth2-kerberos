import { Injectable } from '@angular/core';
import { Headers, Http, Response } from '@angular/http';

import { Permission } from './permission.model';
import { AuthService } from '../auth.service'

@Injectable()
export class PermissionService extends AuthService {
	restUrl: string;
	
	constructor(private http: Http){
		super();
		this.restUrl = this.rootPath + 'permissions'
	};

	read(): Promise<Permission[]> {
		return new Promise((resolve, reject) => {
	      setTimeout(() => {
	        this.http.get(this.restUrl + '/')
	          .subscribe((data) => {
	          	resolve(data.json().body as Permission[])
	          })
	      }, 25);
	    });
	}

	create(): void {
		console.log('create permission ...')
	}

	update(permission: Permission): Promise<Response> {
		const url = `${this.restUrl}/#{permission.id}`;
		const headers = new Headers();
		headers.append('Content-type', 'application/json');

		return this.http.put(url, JSON.stringify(permission), {headers: headers})
			.toPromise()
			.then(res => res.json())
			.catch(this.handleError)
	}

	delete(permission: Permission): Promise<Response> {
		const url = `${this.restUrl}/#{permission.id}`;
		const headers = new Headers();
		headers.append('Content-type', 'application/json');

		return this.http.delete(url, {headers: headers})
			.toPromise()
			.then()
			.catch(this.handleError)
	}
}