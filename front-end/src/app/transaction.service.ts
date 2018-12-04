import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TransactionTypeService {

  baseUrl: string = environment.apiBaseUrl;
  transactionType: any;
  error: any;
  constructor(private httpClient: HttpClient) { }

  getTransactionType(): Observable<any> {
    return this.httpClient.get(this.baseUrl.concat('transaction/type'));
  }
  doTransaction(walletId: any, amount: any, type: any): Observable<any> {
    return this.httpClient.post(this.baseUrl.concat('wallet/').concat(walletId).concat('/transaction'), { 'amount': amount, 'type': type });
  }

  cancelTransaction(walletId: any, transactionId: any): Observable<any> {
    return this.httpClient.delete(this.baseUrl.concat('wallet/').concat(walletId).concat('/transaction/').concat(transactionId));
  }
}
