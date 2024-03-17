import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormStoryComponent } from './form-story.component';

describe('FormStoryComponent', () => {
  let component: FormStoryComponent;
  let fixture: ComponentFixture<FormStoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FormStoryComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FormStoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
