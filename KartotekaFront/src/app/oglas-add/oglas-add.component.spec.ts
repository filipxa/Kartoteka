import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OglasAddComponent } from './oglas-add.component';

describe('OglasAddComponent', () => {
  let component: OglasAddComponent;
  let fixture: ComponentFixture<OglasAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OglasAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OglasAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
