import {Component} from '@angular/core';
    import {HomeComponent} from "./home/home.component";
    import {RouterLink, RouterOutlet} from '@angular/router';

    @Component({
      selector: 'app-root',
      standalone: true,
      imports: [
        RouterLink,
        RouterOutlet,
        HomeComponent
      ],
      template: `
        <main>
          <a [routerLink]="['/']">
            <header class="app-header">
              <img class="app-logo" src="/assets/logo.png" aria-hidden="true">
            </header>
          </a>
          <section class="content">
            <router-outlet></router-outlet>
          </section>
        </main>
      `,
      styleUrl: './app.component.css'
    })
    export class AppComponent {
      title = 'frontend';
    }
