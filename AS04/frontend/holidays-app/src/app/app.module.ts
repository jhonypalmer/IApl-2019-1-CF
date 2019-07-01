import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {
  MatButtonModule,
  MatCardModule,
  MatFormFieldModule,
  MatIconModule, MatInputModule,
  MatListModule,
  MatPaginatorModule,
  MatProgressBarModule,
  MatSidenavModule,
  MatSortModule,
  MatTableModule,
  MatToolbarModule,
  MatTooltipModule
} from "@angular/material";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {HolidayListComponent} from './holiday/holiday-list/holiday-list.component';
import {HolidayCreateComponent} from './holiday/holiday-create/holiday-create.component';
import {HttpClientModule} from "@angular/common/http";
import {ApiModule, Configuration} from "./api-client";
import {AppliedHolidayListComponent} from './applied-holiday/applied-holiday-list/applied-holiday-list.component';
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    HolidayListComponent,
    HolidayCreateComponent,
    AppliedHolidayListComponent
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

    ApiModule.forRoot(() => {
      return new Configuration();
    })

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
