import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {AppliedHolidayControllerService, MonthDayHoliday} from "../../api-client";
import {MatPaginator, MatSort} from "@angular/material";
import {HttpClient} from "@angular/common/http";
import {merge, of as observableOf} from "rxjs";
import {catchError, map, startWith, switchMap} from "rxjs/operators";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-applied-holiday-list',
  templateUrl: './applied-holiday-list.component.html',
  styleUrls: ['./applied-holiday-list.component.scss']
})
export class AppliedHolidayListComponent implements AfterViewInit {
  displayedColumns: string[] = ['date', 'description'];
  data: MonthDayHoliday[] = [];

  resultsLength = 0;
  isLoadingResults = true;
  year = 2019;
  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: false}) sort: MatSort;
  @ViewChild('form', {static: false}) ngForm: NgForm;

  constructor(private _httpClient: HttpClient, private _appliedHolidayControllerService: AppliedHolidayControllerService) {
  }

  ngAfterViewInit() {
    this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);

    merge(this.sort.sortChange, this.paginator.page, this.ngForm.form.valueChanges)
      .pipe(
        startWith({}),
        switchMap((page) => {
          this.isLoadingResults = true;
          return this._appliedHolidayControllerService.listHolidaysUsingGET(this.year, this.paginator.pageIndex, this.paginator.pageSize);
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
}
