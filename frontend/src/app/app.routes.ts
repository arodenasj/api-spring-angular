import {Routes} from '@angular/router';
import {Resume} from './models/resume';
import {ResumeListComponent} from './components/resume-list/resume-list.component';

export const routes: Routes = [
  {path: '', redirectTo: 'resumes', pathMatch: 'full'},
  {path: 'resumes', component: Resume},
  {path: 'resumes', component: ResumeListComponent},
];
