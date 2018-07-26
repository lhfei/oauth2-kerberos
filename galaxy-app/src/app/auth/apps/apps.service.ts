import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

export class AppModel {
  constructor(
  	public id: number, 
  	public appName: string, 
  	public description: string, 
  	public appKey: string, 
  	public createTime: String) { }
}

@Injectable()
export class AppsService {
	restUrl: string = 'http://sas.jd.com/rbac-serve/'
	
	constructor(private http: Http){};

	read(): Promise<AppModel[]> {
		return new Promise((resolve, reject) => {
	      setTimeout(() => {
	        this.http.get(this.restUrl + "apps/")
	          .subscribe((data) => {
	          	resolve(data.json().body as AppModel[])
	          })
	      }, 25);
	    });
	}


	handleError(error: any): Promise<any> {
	  console.error('An error occurred', error); // for demo purposes only
	  return Promise.reject(error.message || error);
	}
}