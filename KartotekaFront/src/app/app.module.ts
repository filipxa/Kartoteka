import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router'
import { MatFormFieldModule } from '@angular/material/form-field';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MyNavComponent } from './my-nav/my-nav.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule, MatCheckboxModule, MatButtonModule, MatSidenavModule, MatIconModule, MatListModule, MatSnackBarModule, MatCardModule, MatStepperModule, MatSelectModule,MatRadioModule } from '@angular/material';
import {MatTabsModule} from '@angular/material/tabs';
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
import { FanzoneComponent } from './fanzone/fanzone.component';
import { RekvizitKarticaComponent } from './rekvizit-kartica/rekvizit-kartica.component';
import { AddRekvizitComponent } from './add-rekvizit/add-rekvizit.component';
import { RekvizitKarticaAdminComponent } from './rekvizit-kartica-admin/rekvizit-kartica-admin.component';
import { RekvizitListaAdminComponent } from './rekvizit-lista-admin/rekvizit-lista-admin.component';
import { RekvizitEditAdminComponent } from './rekvizit-edit-admin/rekvizit-edit-admin.component';
import { LokalListComponent } from './lokal-list/lokal-list.component';
import { HomePageVisitorComponent } from './home-page-visitor/home-page-visitor.component';
import { TheatreListComponent } from './theatre-list/theatre-list.component';
import { LokalProfileComponent } from './lokal-profile/lokal-profile.component';
import { RepertoarBioskopComponent } from './repertoar-bioskop/repertoar-bioskop.component';
import { LokalEditProfileComponent } from './lokal-edit-profile/lokal-edit-profile.component';
import { MatDialogModule } from '@angular/material/dialog';
import { EditProfileAdminFanComponent } from './edit-profile-admin-fan/edit-profile-admin-fan.component';
import { OglasAddComponent } from './oglas-add/oglas-add.component';
import { OglasKaticaAdminComponent } from './oglas-katica-admin/oglas-katica-admin.component';
import { OglasKarticaComponent } from './oglas-kartica/oglas-kartica.component';
import { OglasKarticaEditComponent } from './oglas-kartica-edit/oglas-kartica-edit.component';
import { OglasEditovanjeComponent } from './oglas-editovanje/oglas-editovanje.component';
import { PonudaKarticaComponent } from './ponuda-kartica/ponuda-kartica.component';
import 'rxjs';
import { TicketConfirmComponent } from './ticket-confirm/ticket-confirm.component';
import { NaslovProfileComponent } from './naslov-profile/naslov-profile.component';
import { SalaEditComponent } from './sala-edit/sala-edit.component';
import { RootAdminAddComponent } from './root-admin-add/root-admin-add.component';
import { FirstloginComponent } from './firstlogin/firstlogin.component';
import { LokalAddComponent } from './lokal-add/lokal-add.component';
import { SkalaAddRootComponent } from './skala-add-root/skala-add-root.component';
import { IzvedbaPravljenjeNoveComponent } from './izvedba-pravljenje-nove/izvedba-pravljenje-nove.component';
import { NaslovDodajNovComponent } from './naslov-dodaj-nov/naslov-dodaj-nov.component';

const appRoutes: Routes = [

  { path: 'register', component: RegisterPageComponent },
  { path: '', component: HomePageComponent },
  { path: 'tickets', component: TicketsPageComponent },
  { path: 'fanzone', component: FanzoneComponent },
  { path: 'lokal/reserve/:id', component: LokalReserveComponent },
  { path: 'profile', component: UserProfileCardComponent },
  { path: 'addRekvizit', component: AddRekvizitComponent },
  { path: 'rekvizitAllEdit', component: RekvizitListaAdminComponent },
  { path: 'rekvizitEdit', component: RekvizitEditAdminComponent },
  { path: 'editAdminFan', component: EditProfileAdminFanComponent },
  { path: 'addOglas', component: OglasAddComponent},
  { path: 'theatres', component: TheatreListComponent},
  { path: 'ogalsEdit/:id', component: OglasEditovanjeComponent},
  { path: 'lokal/:type', component: LokalListComponent},
  { path: 'lokal/profil/:id', component: LokalProfileComponent},
  { path: 'addadmin', component: RootAdminAddComponent },
  { path: 'firstlogin', component: FirstloginComponent },
  { path: 'lokalAdd', component: LokalAddComponent },
  { path: 'naslovProfil/:idNaslova/:idLokala', component: NaslovProfileComponent},
  { path: 'lokal/novaIzvedba/:idLokala', component: IzvedbaPravljenjeNoveComponent}
  { path: 'skalaEdit', component: SkalaAddRootComponent },
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
    FanzoneComponent,
    RekvizitKarticaComponent,
    AddRekvizitComponent,
    RekvizitKarticaAdminComponent,
    RekvizitListaAdminComponent,
    RekvizitEditAdminComponent,
    LokalListComponent,
    HomePageVisitorComponent,
    TheatreListComponent,
    EditProfileAdminFanComponent,
    OglasAddComponent,
    OglasKaticaAdminComponent,
    OglasKarticaComponent,
    OglasKarticaEditComponent,
    OglasEditovanjeComponent,
    TheatreListComponent,
    LokalProfileComponent,
    RepertoarBioskopComponent,
    LokalEditProfileComponent,
    TicketConfirmComponent,
    NaslovProfileComponent,
    SalaEditComponent,
    PonudaKarticaComponent,
    TicketConfirmComponent,
    RootAdminAddComponent,
    FirstloginComponent,
    SkalaAddRootComponent
    LokalAddComponent,
    IzvedbaPravljenjeNoveComponent,
    NaslovDodajNovComponent
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
    MatDialogModule,
    MatSidenavModule,
    MatIconModule,
    MatCheckboxModule,
    MatListModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    FormsModule,
    ReactiveFormsModule,
    MatTabsModule,
    MatRadioModule
  ],
  exports: [RouterModule, MatSnackBarModule, HttpClientModule],
  providers: [ LokalEditProfileComponent, SalaEditComponent, NaslovDodajNovComponent ],
  entryComponents: [
    LokalEditProfileComponent,
    SalaEditComponent,
    NaslovDodajNovComponent
    
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
