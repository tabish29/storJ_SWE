import { TestBed } from '@angular/core/testing';

import { StoryObjectService } from './story-object.service';

describe('StoryObjectService', () => {
  let service: StoryObjectService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StoryObjectService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
