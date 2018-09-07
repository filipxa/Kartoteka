import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomePageVisitorComponent } from './home-page-visitor.component';

describe('HomePageVisitorComponent', () => {
  let component: HomePageVisitorComponent;
  let fixture: ComponentFixture<HomePageVisitorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HomePageVisitorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomePageVisitorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
