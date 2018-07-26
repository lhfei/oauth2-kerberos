import { Injectable } from '@angular/core';
import { Headers, Http, Response } from '@angular/http';

import { PrincipalModel } from './principal.model';
import { AuthService } from '../../auth/auth.service'

@Injectable()
export class PrincipalService extends AuthService {
	restUrl: string;
	
	constructor(private http: Http){
		super();
		this.restUrl = this.rootPath + 'principals'
	};

	read(): Promise<PrincipalModel[]> {
		return new Promise((resolve, reject) => {
	      setTimeout(() => {
	        this.http.get(this.restUrl + '/')
	          .subscribe((data) => {
	          	resolve(data.json().body as PrincipalModel[])
	          })
	      }, 25);
	    });
	}

	search(principal: PrincipalModel): Promise<PrincipalModel[]> {
		var principalName = (principal.principalName == null) ? '' : principal.principalName;
		var principalId = (principal.principalId == null) ? '' : principal.principalId;
		return new Promise((resolve, reject) => {
	      setTimeout(() => {
	        this.http.get(this.restUrl + '/', {
	        	params: {
	        		principalName: principalName,
	        		principalId: principalId
	        	}
	        }).subscribe((data) => {
	          	resolve(data.json().body as PrincipalModel[])
	          })
	      }, 25);
	    });
	}

	create(principal: PrincipalModel): Promise<Response> {
		console.log('create principal ...');
		const url = `${this.restUrl}/`
		const headers = new Headers();
		headers.append('Content-type', 'application/json');
		return this.http.post(url, JSON.stringify(principal), {headers: headers})
			.toPromise()
			.then(res => res)
			.catch(this.handleError)
	}

	update(principal: PrincipalModel): Promise<Response> {
		const url = `${this.restUrl}/${principal.principalId}`;
		const headers = new Headers();
		headers.append('Content-type', 'application/json');

		return this.http.put(url, JSON.stringify(principal), {headers: headers})
			.toPromise()
			.then(res => res.json())
			.catch(this.handleError)
	}

	delete(principal: PrincipalModel): Promise<Response> {
		const url = `${this.restUrl}/${principal.principalId}`;
		const headers = new Headers();
		headers.append('Content-type', 'application/json');

		return this.http.delete(url, {headers: headers})
			.toPromise()
			.then(res => res)
			.catch(this.handleError)
	}
}