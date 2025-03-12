import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReseumeCrudComponent } from './resume-crud.component';

describe('ReseumeCrudComponent', () => {
  let component: ReseumeCrudComponent;
  let fixture: ComponentFixture<ReseumeCrudComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReseumeCrudComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReseumeCrudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
