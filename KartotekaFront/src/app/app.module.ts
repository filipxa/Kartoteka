import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router'
import { MatFormFieldModule } from '@angular/material/form-field';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MyNavComponent } from './my-nav/my-nav.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule, MatCheckboxModule, MatButtonModule, MatSidenavModule, MatIconModule, MatListModule, MatSnackBarModule, MatCardModule, MatStepperModule, MatSelectModule } from '@angular/material';
import { LoginSmallComponent } from './login-small/login-small.component';
import { MatInputModule } from '@angular/material/input';
import { RegisterPageComponent } from './register-page/register-page.component';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { UserProfileCardComponent } from './user-profile-card/user-profile-card.component';
import { TicketsPageComponent } from './tickets-page/tickets-page.component';
import { LokalSearchComponent } from './lokal-search/lokal-search.component';
import { LokalReserveComponent } from './lokal-reserve/lokal-reserve.component';
import { HomePageComponent } from './home-page/home-page.component';


const appRoutes: Routes = [

  { path: 'register', component: RegisterPageComponent },
  { path: '', component: HomePageComponent },
  { path: 'tickets', component: TicketsPageComponent },
  { path: 'lokal/reserve/:id', component: LokalReserveComponent },
  { path: 'profile', component: UserProfileCardComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    MyNavComponent,
    LoginSmallComponent,
    RegisterPageComponent,
    UserProfileCardComponent,
    TicketsPageComponent,
    LokalSearchComponent,
    LokalReserveComponent,
    HomePageComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    LayoutModule,
    RouterModule.forRoot(appRoutes),
    MatToolbarModule,
    MatStepperModule,
    MatButtonModule,
    MatSelectModule,
    MatSidenavModule,
    MatIconModule,
    MatCheckboxModule,
    MatListModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    FormsModule,
    ReactiveFormsModule
  ],
  exports: [RouterModule, MatSnackBarModule, HttpClientModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
