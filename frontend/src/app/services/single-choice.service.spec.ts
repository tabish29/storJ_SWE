import { TestBed } from '@angular/core/testing';

import { SingleChoiceService } from './single-choice.service';

describe('SingleChoiceService', () => {
  let service: SingleChoiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SingleChoiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
