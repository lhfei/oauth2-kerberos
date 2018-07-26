import { 
	Component, 
	Input, 
	OnInit, 
	ViewChild, 
	ComponentFactoryResolver, 
	OnDestroy } from '@angular/core';
import {
	ReactiveFormsModule,
	FormControl,
	FormBuilder,
	FormGroup,
	Validators
} from '@angular/forms';

import { DatabaseModel } from '../../dbselector/database.module';

@Component({
	selector: 'hquery-view',
	styleUrls: ['./hquery.component.scss'],
	templateUrl: './hquery.component.html'
})
export class HQueryComponent implements OnInit, OnDestroy {
	@Input() databases: Array<DatabaseModel>;
	
	validateForm: FormGroup;
	searchOptions;
  	selectedMultipleOption;
  	sheetIdx:number = 1;
	
	tabs = [{
		name   : 'Sheet 1',
  		content: ''
	}];

	// ACE Editor
	@ViewChild('editor') editor;
	options: Object = {
		maxLines: 50,
		autoScrollEditorIntoView: true
	};
	dbChecked:boolean = true;
	script:string = 'use default;';

	constructor(private fb: FormBuilder) {
		console.log("DB=" +this.databases)
	}

	ngOnInit() {
		console.log('Hive view init ...')
		/*this.selectedMultipleOption = [ 'default', 'lhfei' ];
		    setTimeout(_ => {
		      this.searchOptions = [
		        { value: 'default', label: 'default'},
		        { value: 'lhfei', label: 'lhfei' }
		      ];
		    }, 300);
		    setTimeout(_ => {
		      this.selectedMultipleOption = [ 'default' ];
		    }, 1000);*/
	}

	ngOnDestroy() {
		console.log('Hive view destroied ...')
	}

	closeTab(tab) {
    	this.tabs.splice(this.tabs.indexOf(tab), 1);
  	}

  	newTab() {
  		this.sheetIdx ++;
  		let sheetName = this.getSheetName();
	    this.tabs.push({
	      name: sheetName,
	      content: ''
	    });
  	}

  	getSheetName(): string {
  		return 'Sheet ' +this.sheetIdx;
  	}

	_submitForm() {
		for (const i in this.validateForm.controls) {
			this.validateForm.controls[ i ].markAsDirty();
		}
	}

	// script onchanged handler
	onChange(code): void {
		console.log(`${code}`)
	}
}