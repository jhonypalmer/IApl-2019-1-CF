import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {
  MatButtonModule,
  MatCardModule,
  MatDividerModule,
  MatFormFieldModule,
  MatIconModule,
  MatInputModule,
  MatListModule,
  MatPaginatorModule,
  MatProgressBarModule,
  MatSelectModule,
  MatSidenavModule,
  MatSnackBarModule,
  MatSortModule,
  MatTableModule,
  MatToolbarModule,
  MatTooltipModule
} from "@angular/material";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {HolidayListComponent} from './holiday/holiday-list/holiday-list.component';
import {HolidayCreateComponent} from './holiday/holiday-create/holiday-create.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {ApiModule, Configuration} from "./api-client";
import {AppliedHolidayListComponent} from './applied-holiday/applied-holiday-list/applied-holiday-list.component';
import {FormsModule} from "@angular/forms";
import {AuthorizationHeaderInterceptor} from "./authorization-header.interceptor";
import {HolidayFormComponent} from './holiday/holiday-form/holiday-form.component';
import {HolidayEditComponent} from './holiday/holiday-edit/holiday-edit.component';

@NgModule({
  declarations: [
    AppComponent,
    HolidayListComponent,
    HolidayCreateComponent,
    AppliedHolidayListComponent,
    HolidayFormComponent,
    HolidayEditComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,

    BrowserAnimationsModule,

    MatSidenavModule,
    MatButtonModule,
    MatCardModule,
    MatToolbarModule,
    MatIconModule,
    MatListModule,
    MatTooltipModule,
    MatTableModule,
    MatProgressBarModule,
    MatPaginatorModule,
    MatSortModule,
    MatFormFieldModule,
    MatInputModule,
    MatDividerModule,
    MatSnackBarModule,
    MatSelectModule,

    ApiModule.forRoot(() => {
      return new Configuration({
        basePath: 'https://calendarioeventos.herokuapp.com',
        // basePath: 'http://localhost:9090',
      });
    })

  ],

  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthorizationHeaderInterceptor,
      multi: true,
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
