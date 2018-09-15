import { TestBed, inject } from '@angular/core/testing';

import { SkalaService } from './skala.service';

describe('SkalaService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SkalaService]
    });
  });

  it('should be created', inject([SkalaService], (service: SkalaService) => {
    expect(service).toBeTruthy();
  }));
});
