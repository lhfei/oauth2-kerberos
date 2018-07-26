import { Injectable } from '@angular/core';
import { Headers, Http, Response } from '@angular/http';

import { Group } from './group.model';
import { AuthService } from '../auth.service';

@Injectable()
export class GroupService extends AuthService {
	restUrl: String;

	constructor(private http: Http) {
		super();
		this.restUrl = this.rootPath + 'groups';
	}

	read(): Promise<Group[]> {
		return new Promise((resolve, reject) => {
	      setTimeout(() => {
	        this.http.get(this.restUrl + '/')
	          .subscribe((data) => {
	          	resolve(data.json().body as Group[])
	          })
	      }, 25);
	    });
	}

	search(group: Group): Promise<Group[]> {
		var cn = (group.cn == null) ? '' : group.cn;
		var gid = (group.gidNumber == null) ? '' : group.gidNumber;
		return new Promise((resolve, reject) => {
	      setTimeout(() => {
	        this.http.get(this.restUrl + '/', {
	        	params: {
	        		cn: cn,
	        		gid: gid
	        	}
	        }).subscribe((data) => {
	          	resolve(data.json().body as Group[])
	          })
	      }, 25);
	    });
	}

	create(group: Group): Promise<Response> {
		console.log('create group ...');
		const url = `${this.restUrl}/`
		const headers = new Headers();
		headers.append('Content-type', 'application/json');
		return this.http.post(url, JSON.stringify(group), {headers: headers})
			.toPromise()
			.then(res => res)
			.catch(this.handleError)
	}

	update(group: Group): Promise<Response> {
		const url = `${this.restUrl}/${group.gidNumber}`;
		const headers = new Headers();
		headers.append('Content-type', 'application/json');

		return this.http.put(url, JSON.stringify(group), {headers: headers})
			.toPromise()
			.then(res => res.json())
			.catch(this.handleError)
	}

	delete(group: Group): Promise<Response> {
		const url = `${this.restUrl}/${group.cn}`;
		const headers = new Headers();
		headers.append('Content-type', 'application/json');

		return this.http.delete(url, {headers: headers})
			.toPromise()
			.then(res => res)
			.catch(this.handleError)
	}	
}