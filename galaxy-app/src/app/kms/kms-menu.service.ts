import { Injectable } from '@angular/core';

@Injectable()
export class KmsMenuService {
	iconMenu = {
        "title": "With icons",
        "items": [
            {
                "label": "KERBEROS",
                "icon": "music-note",
                "children": [
                    {
                        "label": "KAdmin",
                        "icon": "bolt",
                        "url": "/kms/kadmin"
                    },{
                        "label": "Key Tables",
                        "icon": "bolt",
                        "url": "/kms/principals"
                    },{
                        "label": "KDS Server",
                        "icon": "bolt",
                        "url": "/kms/krb5s"
                    }
                ]
            }
        ]
    };
}