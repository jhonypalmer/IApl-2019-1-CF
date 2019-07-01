import {Component, OnInit} from '@angular/core';
import {HolidayControllerService, MonthDayHoliday} from "../../api-client";
import {MatSnackBar} from "@angular/material";
import {Router} from "@angular/router";

@Component({
  selector: 'app-holiday-create',
  templateUrl: './holiday-create.component.html',
  styleUrls: ['./holiday-create.component.scss']
})
export class HolidayCreateComponent implements OnInit {

  holiday: MonthDayHoliday = {};

  constructor(
    private readonly _holidayControllerService: HolidayControllerService,
    private readonly _matSnackBar: MatSnackBar,
    private readonly _router: Router,
  ) {
  }

  ngOnInit() {
  }

  async submit() {
    try {
      await this._holidayControllerService.saveHolidayUsingPOST(this.holiday).toPromise();
      this._matSnackBar.open('Feriado cadastrado com sucesso', null, {duration: 2000});
      this._router.navigate(['/holiday', 'list']);
    } catch (e) {
      this._matSnackBar.open('Falha ao cadastrar feriado', null, {duration: 2000});
      console.warn(e);
    }
  }

}
