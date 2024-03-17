import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormStoryObjectComponent } from './form-story-object.component';

describe('FormStoryObjectComponent', () => {
  let component: FormStoryObjectComponent;
  let fixture: ComponentFixture<FormStoryObjectComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FormStoryObjectComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FormStoryObjectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
