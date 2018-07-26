import { Injectable } from '@angular/core';

@Injectable()
export class ViewMenuService {
	iconMenu = {
        "title": "With icons",
        "items": [
            {
                "label": "Views",
                "icon": "music-note",
                "children": [
                    {
                        "label": "HDFS View",
                        "icon": "bolt",
                        "url": "/views/hdfs"
                    },{
                        "label": "Hive2 View",
                        "icon": "bolt",
                        "url": "/views/hive"
                    }
                ]
            }
        ]
    };
}