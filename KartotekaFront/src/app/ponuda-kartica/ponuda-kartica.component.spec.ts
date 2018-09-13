import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PonudaKarticaComponent } from './ponuda-kartica.component';

describe('PonudaKarticaComponent', () => {
  let component: PonudaKarticaComponent;
  let fixture: ComponentFixture<PonudaKarticaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PonudaKarticaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PonudaKarticaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
