import { Injectable } from '@angular/core';
import { Headers, Http, Response } from '@angular/http';

import { HdfsModel } from './hdfs.model';
import { AuthService } from '../../auth/auth.service'

@Injectable()
export class HdfsService extends AuthService {
	restUrl: string;
	
	constructor(private http: Http){
		super();
		this.restUrl = this.rootPath + 'hdfs'
	};

	read(): Promise<HdfsModel[]> {
		return new Promise((resolve, reject) => {
	      setTimeout(() => {
	        this.http.get(this.restUrl + '/')
	          .subscribe((data) => {
	          	resolve(data.json().body as HdfsModel[])
	          })
	      }, 25);
	    });
	}

	search(cluster: HdfsModel): Promise<HdfsModel[]> {
		var clusterName = (cluster.clusterName == null) ? '' : cluster.clusterName;
		var uid = (cluster.uid == null) ? '' : cluster.uid;
		return new Promise((resolve, reject) => {
	      setTimeout(() => {
	        this.http.get(this.restUrl + '/', {
	        	params: {
	        		clusterName: clusterName,
	        		uid: uid
	        	}
	        }).subscribe((data) => {
	          	resolve(data.json().body as HdfsModel[])
	          })
	      }, 25);
	    });
	}

	create(cluster: HdfsModel): Promise<Response> {
		console.log('create cluster ...');
		const url = `${this.restUrl}/`
		const headers = new Headers();
		headers.append('Content-type', 'application/json');
		return this.http.post(url, JSON.stringify(cluster), {headers: headers})
			.toPromise()
			.then(res => res)
			.catch(this.handleError)
	}

	update(cluster: HdfsModel): Promise<Response> {
		const url = `${this.restUrl}/${cluster.uid}`;
		const headers = new Headers();
		headers.append('Content-type', 'application/json');

		return this.http.put(url, JSON.stringify(cluster), {headers: headers})
			.toPromise()
			.then(res => res.json())
			.catch(this.handleError)
	}

	delete(cluster: HdfsModel): Promise<Response> {
		const url = `${this.restUrl}/${cluster.clusterId}`;
		const headers = new Headers();
		headers.append('Content-type', 'application/json');

		return this.http.delete(url, {headers: headers})
			.toPromise()
			.then(res => res)
			.catch(this.handleError)
	}
}