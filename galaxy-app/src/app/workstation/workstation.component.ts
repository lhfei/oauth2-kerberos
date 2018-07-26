import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import {DomSanitizer} from '@angular/platform-browser';

@Component({
	templateUrl: './workstation.component.html',
	styleUrls: ['./workstation.component.scss']
})

export class WorkstationComponent implements OnInit {
	private menu: any;
	private destination: string = 'https://a01-r03-i164-159-515w64k.jd.local/';
  	private _collapse: boolean = false;

    constructor(
    	private sanitizer: DomSanitizer,
    	private route: ActivatedRoute,
		private router: Router) {

    	this.sanitizer = sanitizer; 
    	this.menu = {
		    "title": "With icons",
		    "items": [
		        {
		            "label": "IDE",
		            "icon": "music-note",
		            "children": [
		                {
		                    "label": "Zeppelin",
		                    "icon": "bolt",
		                    "url": "http://a01-r03-i164-159-515w64k.jd.local/zeppelin/#/"
		                },{
		                    "label": "Ranger",
		                    "icon": "lightbulb",
		                    "url": "http://a01-r03-i164-159-515w64k.jd.local/"
		                }]
		        },{
		            "label": "SQL Lab",
		            "icon": "flame",
		            "children": [
		                {
		                    "label": "SQL 编辑",
		                    "icon": "lightbulb",
		                    "url": "http://192.168.177.77/superset/sqllab"
		                },{
		                    "label": "SQL 查询",
		                    "icon": "lightbulb",
		                    "url": "http://192.168.177.77/superset/sqllab#search"
		                }
		            ]
		        },{
		            "label": "Dashboard",
		            "icon": "flame",
		            "children": [
		                {
		                    "label": "SQL 编辑",
		                    "icon": "lightbulb",
		                    "url": "http://192.168.177.77/dashboardmodelview/list/"
		                }
		            ]
		        },{
		            "label": "Reporting",
		            "icon": "flame",
		            "children": [
		                {
		                    "label": "SQL 编辑",
		                    "icon": "lightbulb",
		                    "url": "http://192.168.177.77/slicemodelview/list/"
		                }
		            ]
		        },{
		            "label": "Data sources",
		            "icon": "flame",
		            "children": [
		                {
		                    "label": "Databases",
		                    "icon": "lightbulb",
		                    "url": "http://192.168.177.77/databaseview/list"
		                },{
		                    "label": "Upload CSV",
		                    "icon": "lightbulb",
		                    "url": "http://192.168.177.77/csvtodatabaseview/form"
		                },{
		                    "label": "Tables",
		                    "icon": "lightbulb",
		                    "url": "http://192.168.177.77/tablemodelview/list"
		                }
		            ]
		        }
		    ]
		}  
    }

    transform() {
    	return this.sanitizer.bypassSecurityTrustUrl(this.destination);
  	}


    get collapse(): boolean {
        return this._collapse;
    }

    set collapse(value: boolean) {
        this._collapse = value;
    }

    forward(url: string):void {
		this.destination = url;
		this.transform();
    }

    ngOnInit(){

		this.route.params.subscribe(params => {
			let app:string = params['app']
			
		})	
      
	}
}
