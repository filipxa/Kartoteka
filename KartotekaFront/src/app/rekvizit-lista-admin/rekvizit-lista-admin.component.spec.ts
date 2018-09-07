import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RekvizitListaAdminComponent } from './rekvizit-lista-admin.component';

describe('RekvizitListaAdminComponent', () => {
  let component: RekvizitListaAdminComponent;
  let fixture: ComponentFixture<RekvizitListaAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RekvizitListaAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RekvizitListaAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
