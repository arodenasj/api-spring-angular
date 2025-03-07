import {Component, OnInit} from '@angular/core';
import {Resume} from '../../models/resume';
import {ResumeService} from '../../services/resume.service';
import {NgForOf} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';

@Component({
  selector: 'app-resume-list',
  standalone: true,
  templateUrl: './resume-list.component.html',
  styleUrls: ['./resume-list.component.css'],
  imports: [NgForOf, HttpClientModule]
})
export class ResumeListComponent implements OnInit {
  resumes: Resume[] = []; // Initialize as empty array

  constructor(private resumesService: ResumeService) {
  }

  ngOnInit(): void {
    this.fetchAllResumes();
  }

  private fetchAllResumes(): void {
    this.resumesService.showAllResumes().subscribe({
      next: (data: Resume[]) => {
        this.resumes = data;
        console.log('Resumes loaded:', data); // Add logging
      },
      error: (error: any) => {
        console.error('Error fetching resumes:', error);
      }
    });
  }
}
