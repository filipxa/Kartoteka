import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CinemaListItemComponent } from './cinema-list-item.component';

describe('CinemaListItemComponent', () => {
  let component: CinemaListItemComponent;
  let fixture: ComponentFixture<CinemaListItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CinemaListItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CinemaListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
