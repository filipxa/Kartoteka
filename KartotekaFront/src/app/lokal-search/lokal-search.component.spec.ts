import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LokalSearchComponent } from './lokal-search.component';

describe('LokalSearchComponent', () => {
  let component: LokalSearchComponent;
  let fixture: ComponentFixture<LokalSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LokalSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LokalSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
