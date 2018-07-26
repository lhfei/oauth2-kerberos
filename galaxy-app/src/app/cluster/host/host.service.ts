import { Injectable } from '@angular/core';
import { Headers, Http, Response } from '@angular/http';

import { HostModel } from './host.model';
import { AuthService } from '../../auth/auth.service'

@Injectable()
export class HostService extends AuthService {
	restUrl: string;
	
	constructor(private http: Http){
		super();
		this.restUrl = this.rootPath + 'hosts'
	};

	read(): Promise<HostModel[]> {
		return new Promise((resolve, reject) => {
	      setTimeout(() => {
	        this.http.get(this.restUrl + '/')
	          .subscribe((data) => {
	          	resolve(data.json().body as HostModel[])
	          })
	      }, 25);
	    });
	}

	search(host: HostModel): Promise<HostModel[]> {
		var hostName = (host.hostName == null) ? '' : host.hostName;
		var clusterId = (host.clusterId == null) ? '' : host.clusterId;
		return new Promise((resolve, reject) => {
	      setTimeout(() => {
	        this.http.get(this.restUrl + '/', {
	        	params: {
	        		hostName: hostName,
	        		clusterId: clusterId
	        	}
	        }).subscribe((data) => {
	          	resolve(data.json().body as HostModel[])
	          })
	      }, 25);
	    });
	}

	create(host: HostModel): Promise<Response> {
		console.log('create host ...');
		const url = `${this.restUrl}/`
		const headers = new Headers();
		headers.append('Content-type', 'application/json');
		return this.http.post(url, JSON.stringify(host), {headers: headers})
			.toPromise()
			.then(res => res)
			.catch(this.handleError)
	}

	update(host: HostModel): Promise<Response> {
		const url = `${this.restUrl}/${host.hostId}`;
		const headers = new Headers();
		headers.append('Content-type', 'application/json');

		return this.http.put(url, JSON.stringify(host), {headers: headers})
			.toPromise()
			.then(res => res.json())
			.catch(this.handleError)
	}

	delete(host: HostModel): Promise<Response> {
		const url = `${this.restUrl}/${host.hostId}`;
		const headers = new Headers();
		headers.append('Content-type', 'application/json');

		return this.http.delete(url, {headers: headers})
			.toPromise()
			.then(res => res)
			.catch(this.handleError)
	}
}