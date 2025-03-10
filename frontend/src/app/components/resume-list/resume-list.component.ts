import { Component, OnInit } from '@angular/core';
      import { Resume } from '../../models/resume';
      import { ResumeService } from '../../services/resume.service';
      import { MatCardModule } from '@angular/material/card';
      import { MatListModule } from '@angular/material/list';
      import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
      import { HttpErrorResponse } from '@angular/common/http';

      @Component({
        selector: 'app-resume-list',
        standalone: true,
        templateUrl: './resume-list.component.html',
        styleUrl: './resume-list.component.css',
        imports: [
          MatCardModule,
          MatListModule,
          MatProgressSpinnerModule
        ]
      })
     export class ResumeListComponent implements OnInit {
  resumes: Resume[] = [];
  isLoading = false;

  constructor(private resumesService: ResumeService) {}

  ngOnInit(): void {
    this.fetchAllResumes();
  }

  private fetchAllResumes(): void {
    this.isLoading = true;
    this.resumesService.showAllResumes().subscribe({
      next: (data: Resume[]) => {
        this.resumes = data;
        this.isLoading = false;
      },
      error: (error: HttpErrorResponse) => {
        console.error('Error fetching resumes:', error);
        this.isLoading = false;
      }
    });
  }
}
