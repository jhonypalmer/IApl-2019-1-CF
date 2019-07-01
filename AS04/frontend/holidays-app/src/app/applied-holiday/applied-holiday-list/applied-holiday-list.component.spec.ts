import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {AppliedHolidayListComponent} from './applied-holiday-list.component';

describe('AppliedHolidayListComponent', () => {
  let component: AppliedHolidayListComponent;
  let fixture: ComponentFixture<AppliedHolidayListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [AppliedHolidayListComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppliedHolidayListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
