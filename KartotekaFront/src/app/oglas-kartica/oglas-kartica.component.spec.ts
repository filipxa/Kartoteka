import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OglasKarticaComponent } from './oglas-kartica.component';

describe('OglasKarticaComponent', () => {
  let component: OglasKarticaComponent;
  let fixture: ComponentFixture<OglasKarticaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OglasKarticaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OglasKarticaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
