import {Component} from '@angular/core';
import {HomeComponent} from "./home/home.component";
import {RouterModule} from "@angular/router";
@Component({
  selector: 'app-root',
  template: '<main><header class="app-header"><img class="app-logo" src="/assets/logo.png" aria-hidden="true"></header><section class="content"><router-outlet></router-outlet></section> </main>',
  imports: [
    HomeComponent, RouterModule
  ],
  styleUrl: './app.component.css'
})
export class AppComponent {
    title = 'frontend';
}
