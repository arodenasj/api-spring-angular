import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Resume } from '../../models/resume';
import { ResumeService } from '../../services/resume.service';
import { HttpClientModule } from '@angular/common/http';
import { MatCardModule } from '@angular/material/card';
import { MatListModule } from '@angular/material/list';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@Component({
  selector: 'app-resume-list',
  standalone: true,
  templateUrl: './resume-list.component.html',
  styleUrls: ['./resume-list.component.css'],
  imports: [
    CommonModule,
    HttpClientModule,
    MatCardModule,
    MatListModule,
    MatProgressSpinnerModule
  ]
})
export class ResumeListComponent implements OnInit {
  resumes: Resume[] = [];

  constructor(private resumesService: ResumeService) {}

  ngOnInit(): void {
    this.fetchAllResumes();
  }

  private fetchAllResumes(): void {
    this.resumesService.showAllResumes().subscribe({
      next: (data: Resume[]) => {
        this.resumes = data;
      },
      error: (error: any) => {
        console.error('Error fetching resumes:', error);
      }
    });
  }
}
