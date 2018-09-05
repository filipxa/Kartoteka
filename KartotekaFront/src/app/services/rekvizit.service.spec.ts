import { TestBed, inject } from '@angular/core/testing';

import { RekvizitService } from './rekvizit.service';

describe('RekvizitService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RekvizitService]
    });
  });

  it('should be created', inject([RekvizitService], (service: RekvizitService) => {
    expect(service).toBeTruthy();
  }));
});
