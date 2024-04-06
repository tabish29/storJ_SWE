import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { PaymentAccessGuard } from './payment-access-guard.guard';

describe('paymentAccessGuardGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => paymentAccessGuardGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
