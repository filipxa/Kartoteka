import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RekvizitKarticaAdminComponent } from './rekvizit-kartica-admin.component';

describe('RekvizitKarticaAdminComponent', () => {
  let component: RekvizitKarticaAdminComponent;
  let fixture: ComponentFixture<RekvizitKarticaAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RekvizitKarticaAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RekvizitKarticaAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
