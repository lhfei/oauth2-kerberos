import { Injectable } from '@angular/core';
import { Headers, Http, Response } from '@angular/http';

import { Person } from './person.model';
import { AuthService } from '../auth.service'

@Injectable()
export class UserService extends AuthService {
	restUrl: string;
	
	constructor(private http: Http){
		super();
		this.restUrl = this.rootPath + 'persons'
	};

	read(): Promise<Person[]> {
		return new Promise((resolve, reject) => {
	      setTimeout(() => {
	        this.http.get(this.restUrl + '/')
	          .subscribe((data) => {
	          	resolve(data.json().body as Person[])
	          })
	      }, 25);
	    });
	}

	search(person: Person): Promise<Person[]> {
		var cn = (person.cn == null) ? '' : person.cn;
		var uid = (person.uid == null) ? '' : person.uid;
		return new Promise((resolve, reject) => {
	      setTimeout(() => {
	        this.http.get(this.restUrl + '/', {
	        	params: {
	        		cn: cn,
	        		uid: uid
	        	}
	        }).subscribe((data) => {
	          	resolve(data.json().body as Person[])
	          })
	      }, 25);
	    });
	}

	create(person: Person): Promise<Response> {
		console.log('create person ...');
		const url = `${this.restUrl}/`
		const headers = new Headers();
		headers.append('Content-type', 'application/json');
		return this.http.post(url, JSON.stringify(person), {headers: headers})
			.toPromise()
			.then(res => res)
			.catch(this.handleError)
	}

	update(person: Person): Promise<Response> {
		const url = `${this.restUrl}/${person.uid}`;
		const headers = new Headers();
		headers.append('Content-type', 'application/json');

		return this.http.put(url, JSON.stringify(person), {headers: headers})
			.toPromise()
			.then(res => res.json())
			.catch(this.handleError)
	}

	delete(person: Person): Promise<Response> {
		const url = `${this.restUrl}/${person.uid}`;
		const headers = new Headers();
		headers.append('Content-type', 'application/json');

		return this.http.delete(url, {headers: headers})
			.toPromise()
			.then(res => res)
			.catch(this.handleError)
	}
}