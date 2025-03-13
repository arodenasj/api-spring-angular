import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {ResumeService} from '../services/resume.service';
import {Resume} from '../resume';
import {MatProgressBarModule} from '@angular/material/progress-bar';

@Component({
  selector: 'app-resume-crud',
  standalone: true,
  imports: [CommonModule, FormsModule, MatProgressBarModule],
  template: `
    <div class="container">
      <div *ngIf="error" class="error-message">
        {{ error }}
      </div>

      <form (ngSubmit)="saveResume()" #resumeForm="ngForm">
        <div class="image-upload">
          <img *ngIf="imagePreview" [src]="imagePreview" class="image-preview" alt="Preview">
          <input
            type="file"
            (change)="onImageSelected($event)"
            accept="image/*">
        </div>

        <input [(ngModel)]="currentResume.name" name="name" placeholder="Name" required>
        <input [(ngModel)]="currentResume.email" name="email" placeholder="Email" required>
        <input [(ngModel)]="currentResume.phone" name="phone" placeholder="Phone">
        <input [(ngModel)]="currentResume.address" name="address" placeholder="Address">
        <input [(ngModel)]="currentResume.position" name="position" placeholder="Position">

        <div class="button-group">
          <button type="submit" class="primary" [disabled]="isLoading || !resumeForm.form.valid">
            {{ isEditing ? 'Update' : 'Create' }}
          </button>
          <button type="button" (click)="resetForm()" [disabled]="isLoading">Cancel</button>
        </div>
      </form>

      <div *ngIf="isLoading" class="progress-container">
        <mat-progress-bar mode="indeterminate"></mat-progress-bar>
      </div>

      <div class="results">
        <div *ngFor="let resume of resumes" class="resume-card">
          <div class="image-avatar">
            <img *ngIf="resume.imageUrl"
                 [src]="'http://localhost:8080' + resume.imageUrl"
                 [alt]="resume.name"
                 (error)="handleImageError($event)">
            <span *ngIf="!resume.imageUrl">{{ resume.name[0] }}</span>
          </div>
          <h3>{{ resume.name }}</h3>
          <p>{{ resume.email }}</p>
          <p>{{ resume.position }}</p>
          <div class="actions">
            <button (click)="editResume(resume)" [disabled]="isLoading">Edit</button>
            <button (click)="deleteResume(resume.id!)" [disabled]="isLoading">Delete</button>
          </div>
        </div>
      </div>
    </div>
  `,
  styleUrls: ['./resume-crud.component.css']
})
export class ResumeCrudComponent implements OnInit {
  resumes: Resume[] = [];
  currentResume: Resume = {} as Resume;
  isEditing = false;
  isLoading = false;
  error: string | null = null;
  imagePreview: string | undefined;

  constructor(private resumeService: ResumeService) {
  }

  ngOnInit(): void {
    this.loadResumes();
  }

  async loadResumes(): Promise<void> {
    try {
      this.isLoading = true;
      this.error = null;
      this.resumes = await this.resumeService.getAllResumes();
    } catch (e) {
      this.error = 'Error loading resumes';
      console.error(e);
    } finally {
      this.isLoading = false;
    }
  }

  async saveResume(): Promise<void> {
    try {
      this.isLoading = true;
      this.error = null;

      // Ensure imageUrl is included in the resume object
      if (this.currentResume.imageUrl) {
        this.currentResume = {
          ...this.currentResume,
          imageUrl: this.currentResume.imageUrl
        };
      }

      if (this.isEditing) {
        await this.resumeService.updateResume(this.currentResume.id!, this.currentResume);
      } else {
        await this.resumeService.createResume(this.currentResume);
      }
      this.resetForm();
      await this.loadResumes();
    } catch (e) {
      this.error = 'Error saving resume';
      console.error(e);
    } finally {
      this.isLoading = false;
    }
  }

  editResume(resume: Resume): void {
    this.currentResume = {...resume};
    this.isEditing = true;
  }

  async deleteResume(id: number): Promise<void> {
    try {
      this.isLoading = true;
      this.error = null;
      await this.resumeService.deleteResume(id);
      await this.loadResumes();
    } catch (e) {
      this.error = 'Error deleting resume';
      console.error(e);
    } finally {
      this.isLoading = false;
    }
  }

  resetForm(): void {
    this.currentResume = {} as Resume;
    this.isEditing = false;
    this.error = null;
  }

  async onImageSelected(event: Event): Promise<void> {
    const file = (event.target as HTMLInputElement).files?.[0];
    if (!file) return;

    // Create preview
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result as string;
    };
    reader.readAsDataURL(file);

    try {
      const formData = new FormData();
      formData.append('file', file);

      const response = await fetch(`${this.resumeService.url}/upload`, {
        method: 'POST',
        body: formData
      });

      if (!response.ok) throw new Error('Upload failed');

      const result = await response.json();
      // Update the current resume with the image URL
      this.currentResume = {
        ...this.currentResume,
        imageUrl: result.imageUrl
      };
    } catch (e) {
      this.error = 'Error uploading image';
      console.error(e);
    }
  }

  handleImageError(event: any): void {
    event.target.style.display = 'none';
    event.target.parentElement.querySelector('span').style.display = 'flex';
  }
}
