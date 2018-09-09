import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LokalProfileComponent } from './lokal-profile.component';

describe('LokalProfileComponent', () => {
  let component: LokalProfileComponent;
  let fixture: ComponentFixture<LokalProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LokalProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LokalProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
