import {Component, inject} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {ResumeService} from "../services/resume.service";
import {Resume} from "../resume";
import {NgIf} from '@angular/common';

@Component({
  selector: "app-details",
  standalone: true,
  imports: [NgIf],
  template: `
    <article>
      <div class="image-avatar">
        <img *ngIf="resume?.imageUrl"
             [src]="getImageUrl(resume?.imageUrl)"
             [alt]="resume?.name || ''"
             (error)="handleImageError($event)">
        <span *ngIf="!resume?.imageUrl">{{getFirstCharacter()}}</span>
      </div>

      <h2>{{resume?.name}}</h2>

      <div class="resume-description">
        <p><strong>Position:</strong> {{resume?.position}}</p>
      </div>

      <div class="resume-details">
        <ul>
          <li><strong>Email:</strong> {{resume?.email}}</li>
          <li><strong>Phone:</strong> {{resume?.phone}}</li>
          <li><strong>Address:</strong> {{resume?.address}}</li>
        </ul>
      </div>
    </article>
  `,
  styleUrl: "./details.component.css"
})
export class DetailsComponent {
  route: ActivatedRoute = inject(ActivatedRoute);
  resumeService = inject(ResumeService);
  resume: Resume | undefined;

  getFirstCharacter(): string {
    return this.resume?.name?.charAt(0) || "";
  }

  getImageUrl(url: string | undefined): string {
    if (!url) return '';
    return `http://localhost:8080${url}`;
  }
  handleImageError(event: any): void {
    event.target.style.display = 'none';
    event.target.parentElement.querySelector('span').style.display = 'flex';
  }

  constructor() {
    const resumeId = Number(this.route.snapshot.params["id"]);
    this.resumeService.getResumeById(resumeId).then(resume => {
        this.resume = resume;
      }
    );
  }
}
