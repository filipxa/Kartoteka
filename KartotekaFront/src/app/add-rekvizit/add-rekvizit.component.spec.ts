import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddRekvizitComponent } from './add-rekvizit.component';

describe('AddRekvizitComponent', () => {
  let component: AddRekvizitComponent;
  let fixture: ComponentFixture<AddRekvizitComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddRekvizitComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddRekvizitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
