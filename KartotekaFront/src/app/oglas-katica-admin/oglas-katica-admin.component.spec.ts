import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OglasKaticaAdminComponent } from './oglas-katica-admin.component';

describe('OglasKaticaAdminComponent', () => {
  let component: OglasKaticaAdminComponent;
  let fixture: ComponentFixture<OglasKaticaAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OglasKaticaAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OglasKaticaAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
