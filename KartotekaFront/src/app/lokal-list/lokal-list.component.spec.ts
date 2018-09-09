import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LokalListComponent } from './lokal-list.component';

describe('CinemaListComponent', () => {
  let component: LokalListComponent;
  let fixture: ComponentFixture<LokalListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LokalListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LokalListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
