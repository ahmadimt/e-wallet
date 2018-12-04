import { TestBed, inject } from '@angular/core/testing';

import { TransactionTypeService } from './transaction.service';

describe('TransactionTypeService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TransactionTypeService]
    });
  });

  it('should be created', inject([TransactionTypeService], (service: TransactionTypeService) => {
    expect(service).toBeTruthy();
  }));
});
