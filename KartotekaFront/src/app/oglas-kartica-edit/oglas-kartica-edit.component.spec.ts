import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OglasKarticaEditComponent } from './oglas-kartica-edit.component';

describe('OglasKarticaEditComponent', () => {
  let component: OglasKarticaEditComponent;
  let fixture: ComponentFixture<OglasKarticaEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OglasKarticaEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OglasKarticaEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
