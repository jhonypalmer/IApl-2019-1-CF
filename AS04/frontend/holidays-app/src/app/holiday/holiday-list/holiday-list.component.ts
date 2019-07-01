import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {merge, of as observableOf} from 'rxjs';
import {catchError, map, startWith, switchMap} from 'rxjs/operators';
import {HolidayControllerService, MonthDayHoliday} from "../../api-client";
import {MatSnackBar} from "@angular/material";

@Component({
  selector: 'app-holiday-list',
  templateUrl: './holiday-list.component.html',
  styleUrls: ['./holiday-list.component.scss']
})
export class HolidayListComponent implements AfterViewInit {
  displayedColumns: string[] = ['date', 'description', 'actions'];
  data: MonthDayHoliday[] = [];

  resultsLength = 0;
  isLoadingResults = true;

  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: false}) sort: MatSort;

  constructor(
    private readonly _holidayControllerService: HolidayControllerService,
    private readonly _matSnackBar: MatSnackBar,
  ) {
  }

  ngAfterViewInit() {
    this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);

    merge(this.sort.sortChange, this.paginator.page)
      .pipe(
        startWith({}),
        switchMap((page) => {
          this.isLoadingResults = true;
          return this._holidayControllerService.listMonthDayHolidaysUsingGET(this.paginator.pageIndex, this.paginator.pageSize);
        }),
        map(data => {
          this.isLoadingResults = false;
          this.resultsLength = data.total;

          return data.holidays;
        }),
        catchError(() => {
          this.isLoadingResults = false;
          return observableOf([]);
        })
      ).subscribe(data => this.data = data);
  }

  async deleteHoliday(holiday: MonthDayHoliday) {
    try {
      await this._holidayControllerService.deleteHolidayUsingDELETE(holiday.id).toPromise();
      this._matSnackBar.open('Feriado removido com sucesso', 'Desfazer', {duration: 4000});
      this.paginator.page.emit({pageIndex: 0, pageSize: 20, length: this.paginator.length});
    } catch (e) {
      this._matSnackBar.open('Falha ao remover feriado', null, {duration: 2000});
      console.error(e);
    }
  }
}
