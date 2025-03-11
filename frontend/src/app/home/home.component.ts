import { Component, inject } from "@angular/core";
      import { ResumeComponent } from "../resume/resume.component";
      import { Resume } from "../resume";
      import { NgForOf } from "@angular/common";
      import { ResumeService } from "../services/resume.service";

      @Component({
        selector: "app-home",
        standalone: true,
        imports: [
          ResumeComponent,
          NgForOf
        ],
        template: `
          <section>
            <form>
              <input type="text" placeholder="Filter by name..." #filter/>
              <button class="primary" type="button" (click)="filterResults(filter.value)">Search</button>
            </form>
          </section>
          <section class="results">
            <app-resume *ngFor="let resume of filteredResumeList" [resume]="resume"></app-resume>
          </section>
        `,
        styleUrl: "./home.component.css"
      })
      export class HomeComponent {
        resumeList: Resume[] = [];
        resumeService: ResumeService = inject(ResumeService);
        filteredResumeList: Resume[] = [];

        constructor() {
          this.resumeService.getAllResumes().then((resumesList: Resume[]) => {
            this.resumeList = resumesList;
            this.filteredResumeList = resumesList;
          }
          );
        }
        filterResults(text: string) {
          if (!text) this.filteredResumeList = this.resumeList;
          this.filteredResumeList = this.resumeList.filter(
            resume => resume?.name.toLowerCase().includes(text.toLowerCase())
          )
        }
      }
