import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StoryObjectsComponent } from './story-objects.component';

describe('StoryObjectsComponent', () => {
  let component: StoryObjectsComponent;
  let fixture: ComponentFixture<StoryObjectsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [StoryObjectsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(StoryObjectsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
