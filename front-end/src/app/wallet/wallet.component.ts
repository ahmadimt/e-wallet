import { Component, OnInit } from '@angular/core';
import { WalletService } from '../wallet.service';

@Component({
  selector: 'app-wallet',
  templateUrl: './wallet.component.html',
  styleUrls: ['./wallet.component.scss']
})
export class WalletComponent implements OnInit {
  wallets: any[];
  error: any;

  constructor(private walletService: WalletService) { }

  ngOnInit() {
    this.getWalletList();
  }
  getWalletList() {
    this.walletService.getAllWallets().subscribe(res => this.wallets = res, err => this.error = err, () => {
      console.log(this.wallets);
    });
  }

}
