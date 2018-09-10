import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditProfileAdminFanComponent } from './edit-profile-admin-fan.component';

describe('EditProfileAdminFanComponent', () => {
  let component: EditProfileAdminFanComponent;
  let fixture: ComponentFixture<EditProfileAdminFanComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditProfileAdminFanComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditProfileAdminFanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
