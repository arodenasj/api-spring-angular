import {Component, Input} from '@angular/core';
import {Resume} from '../resume';
import {RouterModule} from '@angular/router';

@Component({
  selector: 'app-resume',
  imports: [RouterModule],
  template: `
    <section class="resumes">
      <h2 class="name">{{ resume.name }}</h2>
      <p class="position">{{ resume.position }}</p>
      <a [routerLink]="['/details', resume.id]">More details</a>
    </section>
  `,
  styleUrl: './resume.component.css'
})
export class ResumeComponent {
  @Input() resume!: Resume;

}
