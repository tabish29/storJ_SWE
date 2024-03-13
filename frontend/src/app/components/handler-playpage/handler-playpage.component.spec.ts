import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HandlerPlaypageComponent } from './handler-playpage.component';

describe('HandlerPlaypageComponent', () => {
  let component: HandlerPlaypageComponent;
  let fixture: ComponentFixture<HandlerPlaypageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HandlerPlaypageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HandlerPlaypageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
