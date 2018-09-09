import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LokalEditProfileComponent } from './lokal-edit-profile.component';

describe('LokalEditProfileComponent', () => {
  let component: LokalEditProfileComponent;
  let fixture: ComponentFixture<LokalEditProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LokalEditProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LokalEditProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
