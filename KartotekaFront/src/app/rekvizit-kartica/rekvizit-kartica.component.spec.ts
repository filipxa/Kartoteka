import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RekvizitKarticaComponent } from './rekvizit-kartica.component';

describe('RekvizitKarticaComponent', () => {
  let component: RekvizitKarticaComponent;
  let fixture: ComponentFixture<RekvizitKarticaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RekvizitKarticaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RekvizitKarticaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
