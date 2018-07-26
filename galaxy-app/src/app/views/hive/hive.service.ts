import { Injectable } from '@angular/core';
import { Headers, Http, Response } from '@angular/http';

import { HiveModel } from './hive.model';
import { AuthService } from '../../auth/auth.service'

@Injectable()
export class HiveService extends AuthService {
	restUrl: string;
	
	constructor(private http: Http){
		super();
		this.restUrl = this.rootPath + 'hives'
	};

	read(): Promise<HiveModel[]> {
		return new Promise((resolve, reject) => {
	      setTimeout(() => {
	        this.http.get(this.restUrl + '/')
	          .subscribe((data) => {
	          	resolve(data.json().body as HiveModel[])
	          })
	      }, 25);
	    });
	}

	search(hive: HiveModel): Promise<HiveModel[]> {
		var hiveName = (hive.hiveName == null) ? '' : hive.hiveName;
		var clusterId = (hive.clusterId == null) ? '' : hive.clusterId;
		return new Promise((resolve, reject) => {
	      setTimeout(() => {
	        this.http.get(this.restUrl + '/', {
	        	params: {
	        		hiveName: hiveName,
	        		clusterId: clusterId
	        	}
	        }).subscribe((data) => {
	          	resolve(data.json().body as HiveModel[])
	          })
	      }, 25);
	    });
	}

	create(hive: HiveModel): Promise<Response> {
		console.log('create hive ...');
		const url = `${this.restUrl}/`
		const headers = new Headers();
		headers.append('Content-type', 'application/json');
		return this.http.post(url, JSON.stringify(hive), {headers: headers})
			.toPromise()
			.then(res => res)
			.catch(this.handleError)
	}

	update(hive: HiveModel): Promise<Response> {
		const url = `${this.restUrl}/${hive.hiveId}`;
		const headers = new Headers();
		headers.append('Content-type', 'application/json');

		return this.http.put(url, JSON.stringify(hive), {headers: headers})
			.toPromise()
			.then(res => res.json())
			.catch(this.handleError)
	}

	delete(hive: HiveModel): Promise<Response> {
		const url = `${this.restUrl}/${hive.hiveId}`;
		const headers = new Headers();
		headers.append('Content-type', 'application/json');

		return this.http.delete(url, {headers: headers})
			.toPromise()
			.then(res => res)
			.catch(this.handleError)
	}
}