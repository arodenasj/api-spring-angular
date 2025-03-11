import {Component, inject, isDevMode} from '@angular/core';
import {ResumeComponent} from '../resume/resume.component';
import {Resume} from '../resume';
import {NgForOf} from '@angular/common';
import {ResumeService} from '../services/resume.service';

@Component({
  selector: 'app-home',
  imports: [
    ResumeComponent,
    NgForOf
  ],
  template: `
    <section>
      <form>
        <input type="text" placeholder="Filter by name..." />
        <button class="primary" type="button">Search</button>
      </form>
    </section>
    <section class="results"><app-resume *ngFor="let resume of resumeList" [resume]="resume"></app-resume></section>
  `,
  styleUrl: './home.component.css'
})
export class HomeComponent {
  resumeList: Resume[] = [];
  resumeService: ResumeService= inject(ResumeService);

  constructor() {
    this.resumeList = this.resumeService.getAllResumes();
  }
  }
