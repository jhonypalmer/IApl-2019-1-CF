import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {MatButtonModule, MatIconModule, MatListModule, MatSidenavModule, MatToolbarModule, MatTooltipModule} from "@angular/material";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { HolidayListComponent } from './holiday/holiday-list/holiday-list.component';
import { HolidayCreateComponent } from './holiday/holiday-create/holiday-create.component';

@NgModule({
  declarations: [
    AppComponent,
    HolidayListComponent,
    HolidayCreateComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,

    BrowserAnimationsModule,

    MatSidenavModule,
    MatButtonModule,
    MatToolbarModule,
    MatIconModule,
    MatListModule,
    MatTooltipModule,

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
