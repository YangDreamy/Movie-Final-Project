import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MovieComponent } from './components/movie/movie.component';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { appRoutes } from './app.routes';
import { AdminComponent } from './components/admin/admin.component';
import { UserComponent } from './components/user/user.component';
import { SeenmovieComponent } from './components/seenmovie/seenmovie.component';
import { ReviewstarComponent } from './components/reviewstar/reviewstar.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { CountComponent } from './components/count/count.component';
import { PlaymovieComponent } from './components/playmovie/playmovie.component';
import { RateComponent } from './components/rate/rate.component';
//import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
@NgModule({
  declarations: [
    AppComponent,
    MovieComponent,
    AdminComponent,
    UserComponent,
    SeenmovieComponent,
    ReviewstarComponent,
    WelcomeComponent,
    CountComponent,
    PlaymovieComponent,
    RateComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    //NgbModule
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
