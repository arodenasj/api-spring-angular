import {Component, OnInit} from '@angular/core';
import {Resume} from '../../models/resume';
import {ResumeService} from '../../services/resume.service';
import {NgForOf} from '@angular/common';

@Component({
  selector: 'app-resume-list',
  standalone: true,
  templateUrl: './resume-list.component.html',
  styleUrls: ['./resume-list.component.css'],
  imports: [NgForOf]
})
export class ResumeListComponent implements OnInit {
  resumes?: Resume[];

  constructor(private resumesService: ResumeService) {
  }

  ngOnInit(): void {
    this.fetchAllResumes();
  }

  private fetchAllResumes(): void {
    this.resumesService.showAllResumes().subscribe({
      next: (value: Resume[]) => {
        this.resumes = value;
      },
      error: (error: any) => {
        console.log(error);
      }
    });
  }
}
