import { AdminComponent } from './components/admin/admin.component';
import { MovieComponent } from './components/movie/movie.component';
import { UserComponent } from './components/user/user.component';
import { SeenmovieComponent} from './components/seenmovie/seenmovie.component';
import { Component } from '@angular/core';
import{ ReviewstarComponent} from './components/reviewstar/reviewstar.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { CountComponent } from './components/count/count.component';
import { PlaymovieComponent } from './components/playmovie/playmovie.component';
import { RateComponent } from './components/rate/rate.component';
export const appRoutes=[
    {path: 'admin',component : AdminComponent},
    {path: 'movie',component : MovieComponent},
    {path: 'user',component : UserComponent},
    {path: 'seenmovie',component : SeenmovieComponent},
    {path: 'reviewstar',component : ReviewstarComponent},
    {path: 'welcomePage',component : WelcomeComponent},
    {path: 'countPage',component:CountComponent},
    {path: 'playmovie',component : PlaymovieComponent},
    {path: 'Review',component:RateComponent}

]