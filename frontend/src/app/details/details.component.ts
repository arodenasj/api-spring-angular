import { Component, inject } from "@angular/core";
   import { ActivatedRoute } from "@angular/router";
   import { ResumeService } from "../services/resume.service";
   import { Resume } from "../resume";

   @Component({
     selector: "app-details",
     standalone: true,
     imports: [],
     template: `
      <article>
        <h2>{{resume?.name}}</h2>
        <section class="resume-description">
          <p>{{resume?.email}}</p>
          <p>{{resume?.phone}}</p>
          <p>{{resume?.address}}</p>
        </section>
        <section class="resume-details">
          <h2 class="desciption">Summary</h2>
          <ul>
            <li>Positions: {{resume?.position}}</li>
          </ul>
        </section>
      </article>
     `,
     styleUrl: "./details.component.css"
   })
   export class DetailsComponent {
     route: ActivatedRoute = inject(ActivatedRoute);
     resumeService = inject(ResumeService);
     resume: Resume | undefined;

     constructor() {
       const resumeId = Number(this.route.snapshot.params["id"]);
this.resumeService.getResumeById(resumeId).then(resume => {
          this.resume = resume;
        }
        );
      }
    }
