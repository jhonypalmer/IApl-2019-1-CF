import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HolidayListComponent} from "./holiday/holiday-list/holiday-list.component";
import {HolidayCreateComponent} from "./holiday/holiday-create/holiday-create.component";

const routes: Routes = [
  {
    path: '',
    component: HolidayListComponent,
  },
  {
    path: 'holiday/create',
    component: HolidayCreateComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
