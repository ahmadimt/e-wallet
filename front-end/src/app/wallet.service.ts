import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class WalletService {

  baseUrl: string = environment.apiBaseUrl;
  constructor(private httpClient: HttpClient) { }

  getPassbookDetails(walletId: any): Observable<any> {
    return this.httpClient.get(this.baseUrl.concat('wallet/').concat(walletId).concat('/transaction'));
  }
  getWalletDetails(walletId: any): Observable<any> {
    return this.httpClient.get(this.baseUrl.concat('wallet/').concat(walletId));
  }

  getAllWallets(): Observable<any> {
    return this.httpClient.get(this.baseUrl.concat('admin/wallet/'));
  }
}
