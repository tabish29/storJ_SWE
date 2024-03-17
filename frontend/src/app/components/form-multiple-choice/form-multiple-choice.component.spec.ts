import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormMultipleChoiceComponent } from './form-multiple-choice.component';

describe('FormMultipleChoiceComponent', () => {
  let component: FormMultipleChoiceComponent;
  let fixture: ComponentFixture<FormMultipleChoiceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FormMultipleChoiceComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FormMultipleChoiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
