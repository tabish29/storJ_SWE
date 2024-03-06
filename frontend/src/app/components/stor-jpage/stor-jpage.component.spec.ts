import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StorJPageComponent } from './stor-jpage.component';

describe('StorJPageComponent', () => {
  let component: StorJPageComponent;
  let fixture: ComponentFixture<StorJPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [StorJPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(StorJPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
