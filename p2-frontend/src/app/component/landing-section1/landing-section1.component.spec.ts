import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LandingSection1Component } from './landing-section1.component';

describe('LandingSection1Component', () => {
  let component: LandingSection1Component;
  let fixture: ComponentFixture<LandingSection1Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LandingSection1Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LandingSection1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
