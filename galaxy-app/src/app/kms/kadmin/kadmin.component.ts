import { Component, ViewChild } 	from '@angular/core';
import { Router } 	from '@angular/router';

// import { Stomp } from 'stompjs';
// import { SockJS } from 'sockjs-client';

import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

import 'brace/theme/github';
import 'brace/mode/sh';

@Component({
	selector: 'kadmin-app',
	templateUrl: './kadmin.component.html',
	styleUrls: ['./kadmin.component.scss']
})
export class KAdminComponent {
	@ViewChild('editor') editor;
	case: any;

	ws: any;
	command: string;
  	closed: boolean = true;
	greetings: string[] = [];
  	showConversation: boolean = false;
    emptyLine: string = "[root@openldap.jd.com]$ ";
	options: Object = {
		maxLines: 50,
		autoScrollEditorIntoView: true
	};

	constructor() {
		this.command = this.emptyLine;
	}

	onChange(code): void {
		//console.log(`${code}`)
	}

    ngAfterViewInit() {
    	var me = this;
        /*me.editor.getEditor().setOptions({
            enableBasicAutocompletion: true
        });*/

        me.editor.getEditor().commands.addCommand({
            name: "showOtherCompletions",
            bindKey: "Enter",
            exec: function (editor) {
            	me.getCommand();
            	me.sendCmd();
            	// editor.insert("\n" +me.emptyLine);
            }
        })
    };

    // Socket
    connect() {
		let socket = new SockJS("/krb5/api/v1/ws");
			this.ws = Stomp.over(socket);
			let that = this;
		this.ws.connect({}, function(frame) {
			that.ws.subscribe("/errors", function(message) {
				alert("Error " + message.body);
			});
			that.ws.subscribe("/topic/cmd", function(message) {
				console.log(message)
				that.showConsole(JSON.parse(message.body).out);
			});

			that.closed = false;
		}, function(error) {
			alert("STOMP error " + error);
		});
  	}

	disconnect() {
		if (this.ws != null) {
			this.ws.disconnect();
		}
		this.setConnected(false);
		console.log("Disconnected");
	}

	sendCmd() {
		if(this.command.length > 0){
			let data = JSON.stringify({
				'command' : this.command
			})
			this.ws.send("/ms/command", {}, data);
		}else {
			this.editor.getEditor().insert("\n" +this.emptyLine);
		}
	}

	showConsole(message) {
		this.showConversation = true;
		let ace = this.editor.getEditor();
		ace.insert("\n" +message);
		ace.insert("\n" +this.emptyLine);
		ace.scrollPageDown()
	}

	setConnected(connected) {
		this.closed = !connected;
		this.showConversation = connected;
		this.greetings = [];
	}

	getCommand() {
		var ace = this.editor.getEditor();
		
		let line = ace.getLastVisibleRow();
		let txt = ace.session.getLine(line);
		this.command = txt.replace(this.emptyLine, '');
		console.log(`Command is: [${this.command}]`)
	}
}