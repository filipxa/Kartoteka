import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SkalaAddRootComponent } from './skala-add-root.component';

describe('SkalaAddRootComponent', () => {
  let component: SkalaAddRootComponent;
  let fixture: ComponentFixture<SkalaAddRootComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SkalaAddRootComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SkalaAddRootComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
