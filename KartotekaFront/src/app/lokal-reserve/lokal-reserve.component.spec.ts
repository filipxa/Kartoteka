import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LokalReserveComponent } from './lokal-reserve.component';

describe('LokalReserveComponent', () => {
  let component: LokalReserveComponent;
  let fixture: ComponentFixture<LokalReserveComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LokalReserveComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LokalReserveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
