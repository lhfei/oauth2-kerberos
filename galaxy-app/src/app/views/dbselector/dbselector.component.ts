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

import { DatabaseModel } from '../dbselector/database.module';

@Component({
	selector: 'dbselector-view',
	styleUrls: ['./dbselector.component.scss'],
	templateUrl: './dbselector.component.html'
})
export class DBSelectorComponent implements OnInit, OnDestroy {
	@Input() databases: Array<DatabaseModel>;
	
	validateForm: FormGroup;
	searchOptions;
  	selectedMultipleOption;
  	

	constructor(private fb: FormBuilder) {
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
	_submitForm() {
		for (const i in this.validateForm.controls) {
			this.validateForm.controls[ i ].markAsDirty();
		}
	}

}