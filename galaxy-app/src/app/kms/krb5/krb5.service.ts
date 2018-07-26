import { Injectable } from '@angular/core';
import { Headers, Http, Response } from '@angular/http';

import { Krb5Model } from './krb5.model';
import { AuthService } from '../../auth/auth.service'

@Injectable()
export class Krb5Service extends AuthService {
	restUrl: string;
	
	constructor(private http: Http){
		super();
		this.restUrl = this.rootPath + 'krb5s'
	};

	read(): Promise<Krb5Model[]> {
		return new Promise((resolve, reject) => {
	      setTimeout(() => {
	        this.http.get(this.restUrl + '/')
	          .subscribe((data) => {
	          	resolve(data.json().body as Krb5Model[])
	          })
	      }, 25);
	    });
	}

	search(krb5: Krb5Model): Promise<Krb5Model[]> {
		var realm = (krb5.realm == null) ? '' : krb5.realm;
		var krb5Id = (krb5.krb5Id == null) ? '' : krb5.krb5Id;
		return new Promise((resolve, reject) => {
	      setTimeout(() => {
	        this.http.get(this.restUrl + '/', {
	        	params: {
	        		realm: realm,
	        		krb5Id: krb5Id
	        	}
	        }).subscribe((data) => {
	          	resolve(data.json().body as Krb5Model[])
	          })
	      }, 25);
	    });
	}

	create(krb5: Krb5Model): Promise<Response> {
		console.log('create krb5 ...');
		const url = `${this.restUrl}/`
		const headers = new Headers();
		headers.append('Content-type', 'application/json');
		return this.http.post(url, JSON.stringify(krb5), {headers: headers})
			.toPromise()
			.then(res => res)
			.catch(this.handleError)
	}

	update(krb5: Krb5Model): Promise<Response> {
		const url = `${this.restUrl}/${krb5.krb5Id}`;
		const headers = new Headers();
		headers.append('Content-type', 'application/json');

		return this.http.put(url, JSON.stringify(krb5), {headers: headers})
			.toPromise()
			.then(res => res.json())
			.catch(this.handleError)
	}

	delete(krb5: Krb5Model): Promise<Response> {
		const url = `${this.restUrl}/${krb5.krb5Id}`;
		const headers = new Headers();
		headers.append('Content-type', 'application/json');

		return this.http.delete(url, {headers: headers})
			.toPromise()
			.then(res => res)
			.catch(this.handleError)
	}
}