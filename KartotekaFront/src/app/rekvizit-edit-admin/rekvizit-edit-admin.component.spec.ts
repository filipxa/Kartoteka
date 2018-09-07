import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RekvizitEditAdminComponent } from './rekvizit-edit-admin.component';

describe('RekvizitEditAdminComponent', () => {
  let component: RekvizitEditAdminComponent;
  let fixture: ComponentFixture<RekvizitEditAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RekvizitEditAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RekvizitEditAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
