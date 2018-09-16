import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IzvedbaPravljenjeNoveComponent } from './izvedba-pravljenje-nove.component';

describe('IzvedbaPravljenjeNoveComponent', () => {
  let component: IzvedbaPravljenjeNoveComponent;
  let fixture: ComponentFixture<IzvedbaPravljenjeNoveComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IzvedbaPravljenjeNoveComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IzvedbaPravljenjeNoveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
