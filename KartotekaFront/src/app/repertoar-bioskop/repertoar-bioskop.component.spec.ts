import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RepertoarBioskopComponent } from './repertoar-bioskop.component';

describe('RepertoarBioskopComponent', () => {
  let component: RepertoarBioskopComponent;
  let fixture: ComponentFixture<RepertoarBioskopComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RepertoarBioskopComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RepertoarBioskopComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
