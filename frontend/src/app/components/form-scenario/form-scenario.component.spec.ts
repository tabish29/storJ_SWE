import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormScenarioComponent } from './form-scenario.component';

describe('FormScenarioComponent', () => {
  let component: FormScenarioComponent;
  let fixture: ComponentFixture<FormScenarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FormScenarioComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FormScenarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
