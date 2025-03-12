import { Routes } from "@angular/router";
    import { HomeComponent } from "./home/home.component";
    import { DetailsComponent } from "./details/details.component";
    import { ResumeCrudComponent } from "./resume-crud/resume-crud.component";

    const routeConfig: Routes = [
        {
            path: '',
            component: HomeComponent,
            title: 'Home page'
        },
        {
            path: 'details/:id',
            component: DetailsComponent,
            title: 'Details page'
        },
        {
            path: 'manage',
            component: ResumeCrudComponent,
            title: 'Manage Resumes'
        }
    ];

    export default routeConfig;
