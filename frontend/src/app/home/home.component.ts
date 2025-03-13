import {Component, inject} from "@angular/core";
import {ResumeComponent} from "../resume/resume.component";
import {Resume} from "../resume";
import {NgForOf, NgIf} from "@angular/common";
import {ResumeService} from "../services/resume.service";
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {RouterLink} from '@angular/router';

@Component({
  selector: "app-home",
  standalone: true,
  imports: [
    ResumeComponent,
    NgForOf,
    MatProgressBarModule,
    NgIf,
    RouterLink
  ],
  template: `
    <section>
      <form>
        <input type="text" placeholder="Filter by name..." #filter/>
        <button class="primary" type="button" (click)="filterResults(filter.value)">Search</button>
        <button class="primary" type="button" [routerLink]="['/manage']">Manage Resumes</button>
      </form>
    </section>
    <section class="results">
      <div *ngIf="isLoading" class="progress-container">
        <mat-progress-bar mode="indeterminate"></mat-progress-bar>
      </div>
      <ng-container *ngIf="!isLoading">
        <div *ngIf="filteredResumeList.length === 0" class="no-data">
          No hay currículums disponibles
        </div>
        <app-resume *ngFor="let resume of filteredResumeList" [resume]="resume"></app-resume>
      </ng-container>
    </section>
  `,
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  resumeList: Resume[] = [];
  resumeService: ResumeService = inject(ResumeService);
  filteredResumeList: Resume[] = [];
  isLoading = true;

  constructor() {
    this.isLoading = true;
    this.resumeService.getAllResumes()
      .then((resumesList: Resume[]) => {
        this.resumeList = resumesList;
        this.filteredResumeList = resumesList;
      })
      .catch(error => {
        console.error('Error loading resumes:', error);
        this.resumeList = [];
        this.filteredResumeList = [];
      })
      .finally(() => {
        this.isLoading = false;
      });
  }

  filterResults(text: string) {
    if (!text) this.filteredResumeList = this.resumeList;
    this.filteredResumeList = this.resumeList.filter(
      resume => resume?.name.toLowerCase().includes(text.toLowerCase())
    )
  }
}
