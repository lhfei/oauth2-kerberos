import { Injectable } from '@angular/core';

@Injectable()
export class ClusterMenuService {
	iconMenu = {
        "title": "With icons",
        "items": [
            {
                "label": "集群实例",
                "icon": "music-note",
                "children": [
                    {
                        "label": "实例列表",
                        "icon": "bolt",
                        "url": "/cluster/instances"
                    },{
                        "label": "主机列表",
                        "icon": "bolt",
                        "url": "/cluster/hosts"
                    }
                ]
            }
        ]
    };
}