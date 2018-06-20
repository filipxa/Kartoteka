import { TestBed, inject } from '@angular/core/testing';

import { LokalService } from './lokal.service';

describe('LokalService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [LokalService]
    });
  });

  it('should be created', inject([LokalService], (service: LokalService) => {
    expect(service).toBeTruthy();
  }));
});
