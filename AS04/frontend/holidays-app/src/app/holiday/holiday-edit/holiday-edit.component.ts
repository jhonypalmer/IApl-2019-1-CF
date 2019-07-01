import {Component, OnInit} from '@angular/core';
import {HolidayControllerService, MonthDayHoliday} from "../../api-client";
import {MatSnackBar} from "@angular/material";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-holiday-edit',
  templateUrl: './holiday-edit.component.html',
  styleUrls: ['./holiday-edit.component.scss']
})
export class HolidayEditComponent implements OnInit {

  holiday: MonthDayHoliday = {};

  constructor(
    private readonly _holidayControllerService: HolidayControllerService,
    private readonly _matSnackBar: MatSnackBar,
    private readonly _route: ActivatedRoute,
    private readonly _router: Router,
  ) {
  }

  ngOnInit() {
    const id = +this._route.snapshot.paramMap.get("id");
    this.loadHoliday(id);
  }

  private async loadHoliday(id) {
    try {
      this.holiday = await this._holidayControllerService.getHolidayUsingGET(id).toPromise()
    } catch (e) {
      this._matSnackBar.open('Falha ao carregar feriado', null, {duration: 2000});
      console.warn(e);
    }
  }

  async submit() {
    try {
      await this._holidayControllerService.updateHolidayUsingPUT(this.holiday).toPromise();
      this._matSnackBar.open('Feriado alterado com sucesso', null, {duration: 2000});
      this._router.navigate(['/holiday', 'list']);
    } catch (e) {
      this._matSnackBar.open('Falha ao cadastrar feriado', null, {duration: 2000});
      console.warn(e);
    }
  }

}
