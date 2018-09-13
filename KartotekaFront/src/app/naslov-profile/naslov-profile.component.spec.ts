import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NaslovProfileComponent } from './naslov-profile.component';

describe('NaslovProfileComponent', () => {
  let component: NaslovProfileComponent;
  let fixture: ComponentFixture<NaslovProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NaslovProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NaslovProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
