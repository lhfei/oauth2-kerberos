import { Injectable } from '@angular/core';

@Injectable()
export class AppModel {
  constructor(
  	public id: number, 
  	public appName: string, 
  	public description: string, 
  	public appKey: string, 
  	public createTime: String) { }
}
