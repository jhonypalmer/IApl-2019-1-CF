import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HolidayListComponent} from "./holiday/holiday-list/holiday-list.component";
import {HolidayCreateComponent} from "./holiday/holiday-create/holiday-create.component";
import {AppliedHolidayListComponent} from "./applied-holiday/applied-holiday-list/applied-holiday-list.component";

const routes: Routes = [
  {
    path: '',
    component: AppliedHolidayListComponent,
  },
  {
    path: 'holiday/list',
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
