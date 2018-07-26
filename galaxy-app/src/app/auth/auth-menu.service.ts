import { Injectable } from '@angular/core';

@Injectable()
export class AuthMenuService {
    iconMenu = {
        "title": "With icons",
        "items": [
            {
                "label": "应用管理",
                "icon": "music-note",
                "children": [
                    {
                        "label": "应用列表",
                        "icon": "bolt",
                        "url": "/auth/apps"
                    }
                ]
            },
            {
                "label": "用户管理",
                "icon": "flame",
                "children": [
                    {
                        "label": "用户列表",
                        "icon": "lightbulb",
                        "url": "/auth/users/"
                    },{
                        "label": "组管理",
                        "icon": "lightbulb",
                        "url": "/auth/groups/"
                    }
                ]
            },
            {
                "label": "角色管理",
                "icon": "happy-face",
                "children": [
                    {
                        "label": "角色列表",
                        "icon": "bug",
                        "url": "/auth/roles/"
                    }
                ]
            },
            {
                "label": "权限中心",
                "icon": "thermometer",
                "children": [{
                    "label": "目录管理",
                    "icon": "bug",
                    "url": "/auth/permission/vender"
                },{
                    "label": "计算资源",
                    "icon": "bug",
                    "url": "/auth/roles/"
                },{
                    "label": "内部用户管理",
                    "icon": "bug",
                    "url": "/auth/permission/operator"
                }]
                
            }
        ]
    };

}
