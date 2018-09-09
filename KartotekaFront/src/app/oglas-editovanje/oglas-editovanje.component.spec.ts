import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OglasEditovanjeComponent } from './oglas-editovanje.component';

describe('OglasEditovanjeComponent', () => {
  let component: OglasEditovanjeComponent;
  let fixture: ComponentFixture<OglasEditovanjeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OglasEditovanjeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OglasEditovanjeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
