import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RootAdminAddComponent } from './root-admin-add.component';

describe('RootAdminAddComponent', () => {
  let component: RootAdminAddComponent;
  let fixture: ComponentFixture<RootAdminAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RootAdminAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RootAdminAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
