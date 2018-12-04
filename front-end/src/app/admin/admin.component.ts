import { Component, OnInit } from '@angular/core';
import * as M from 'materialize-css';
import { FormGroup, Validators, FormControl } from '@angular/forms';
@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {
  typeSelector: any;
  select: any;
  selectedValue: any;
  walletForm: FormGroup;
  walletAdminForm: FormGroup;
  urlRegx = '(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?';
  pagination: any;
  constructor() {
    document.addEventListener('DOMContentLoaded', () => {
      this.select = document.querySelector('select');
      this.typeSelector = M.FormSelect.init(this.select, {});
    });
  }

  ngOnInit() {
    this.walletForm = new FormGroup({
      walletId: new FormControl('', Validators.compose([Validators.pattern(this.urlRegx), Validators.required]))
    });
    this.walletAdminForm = new FormGroup({
      name: new FormControl('', Validators.compose([Validators.required]))
    });

    document.addEventListener('DOMContentLoaded', () => {
      const elems = document.querySelectorAll('.collapsible');
      M.Collapsible.init(elems, {});
    });
  }
  create() {
    console.log('Selected Value ', this.selectedValue);
  }

  onTypeChanged(event) {
    this.selectedValue = event;
    return this.selectedValue;
  }
}
