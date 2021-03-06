import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SalaEditComponent } from './sala-edit.component';

describe('SalaEditComponent', () => {
  let component: SalaEditComponent;
  let fixture: ComponentFixture<SalaEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SalaEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SalaEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
