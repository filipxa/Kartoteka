import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LokalAddComponent } from './lokal-add.component';

describe('LokalAddComponent', () => {
  let component: LokalAddComponent;
  let fixture: ComponentFixture<LokalAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LokalAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LokalAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
