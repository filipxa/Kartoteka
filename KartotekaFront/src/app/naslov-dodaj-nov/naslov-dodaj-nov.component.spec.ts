import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NaslovDodajNovComponent } from './naslov-dodaj-nov.component';

describe('NaslovDodajNovComponent', () => {
  let component: NaslovDodajNovComponent;
  let fixture: ComponentFixture<NaslovDodajNovComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NaslovDodajNovComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NaslovDodajNovComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
