import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormSingleChoiceComponent } from './form-single-choice.component';

describe('FormSingleChoiceComponent', () => {
  let component: FormSingleChoiceComponent;
  let fixture: ComponentFixture<FormSingleChoiceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FormSingleChoiceComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FormSingleChoiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
