import { Component, OnInit } from '@angular/core';
import * as M from 'materialize-css';
import { TransactionTypeService } from '../transaction.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { WalletService } from '../wallet.service';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  showForm = false;
  showPassbook = false;
  isTransaction = true;
  select: any;
  typeSelector: any;
  transactionTypes: any[];
  error: any;
  transactionForm: FormGroup;
  data: any;
  walletId: any;
  selectedValue: any;
  numberRegx = /^\d+(?:\.\d{1,2})?$/;
  transactionDetails: any[];
  walletDetails: any;
  cancellationForm: FormGroup;
  cancellationDetails: any;
  constructor(private transactionService: TransactionTypeService, private route: ActivatedRoute,
    private walletService: WalletService) {
    route.url.subscribe(data => this.walletId = data[0].path);
  }

  ngOnInit() {
    document.addEventListener('DOMContentLoaded', () => {
      const elems = document.querySelectorAll('.tabs');
      M.Tabs.init(elems, {});
      this.select = document.querySelector('select');
      this.typeSelector = M.FormSelect.init(this.select, {});
    });
    /* this.transactionForm = new FormGroup({
      amount: new FormControl('', Validators.compose([Validators.required]))
    }); */
    this.transactionForm = new FormGroup({
      amount: new FormControl('', Validators.compose([Validators.pattern(this.numberRegx), Validators.required]))
    });

    this.cancellationForm = new FormGroup({
      transactionId: new FormControl('', Validators.compose([Validators.required]))
    });
    this.getWalletDetails();
    this.getPassbook();
  }

  onTypeChanged(event) {
    this.selectedValue = event;
    return this.selectedValue;
  }

  doTransaction() {
    console.log('Type of transaction', this.selectedValue);
    const amount = this.transactionForm.get('amount').value;
    console.log('Amount', amount);

    this.transactionService.doTransaction(this.walletId, amount, this.selectedValue)
      .subscribe(res => this.data = res, err => this.error = err, () => {
        console.log(this.data);
        this.getWalletDetails();
        this.getPassbook();
        alert('Transaction of type ' + this.selectedValue + ' for an amount ' + amount + ' is successful')
      });
  }
  getPassbook() {
    this.walletService.getPassbookDetails(this.walletId).subscribe(
      res => this.transactionDetails = res,
      err => this.error = err, () => {
        console.log(this.transactionDetails);
      });
  }

  getWalletDetails() {
    this.walletService.getWalletDetails(this.walletId).subscribe(
      res => this.walletDetails = res,
      err => this.error = err, () => {
        console.log(this.walletDetails);
      });
  }

  hidePassbook() {
    this.showPassbook = false;
    this.isTransaction = !this.isTransaction;

  }
  cancelTransaction() {
    const transactionId = this.cancellationForm.get('transactionId').value;
    this.transactionService.cancelTransaction(this.walletId, transactionId).subscribe(
      res => this.cancellationDetails = res,
      err => this.error = err, () => {
        alert('Transaction cancelled successfully');
        this.getPassbook();
        this.getWalletDetails();
        console.log('Cancellation details', this.cancellationDetails);
      });
  }
}
