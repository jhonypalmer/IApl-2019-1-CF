import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {Account, BankControllerService} from "../../api-client";
import {MatPaginator, MatSnackBar, MatSort} from "@angular/material";
import {merge, of as observableOf} from "rxjs";
import {catchError, map, startWith, switchMap} from "rxjs/operators";

@Component({
  selector: 'app-account-list',
  templateUrl: './account-list.component.html',
  styleUrls: ['./account-list.component.scss']
})
export class AccountListComponent implements AfterViewInit {
  displayedColumns: string[] = ['agencia', 'numero', 'titular', 'saldo'];
  data: Account[] = [];

  resultsLength = 0;
  isLoadingResults = true;

  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: false}) sort: MatSort;

  constructor(
    private readonly _bankControllerService: BankControllerService,
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
          return this._bankControllerService.getAccountsUsingGET()
        }),
        map(data => {
          this.isLoadingResults = false;

          return data;
        }),
        catchError(() => {
          this.isLoadingResults = false;
          return observableOf([]);
        })
      ).subscribe(data => this.data = data);
  }

  async deleteAccount(account: Account) {
    try {
      //await this._bankControllerService.deleteAccountUsingDELETE(account.id).toPromise();
      this._matSnackBar.open('Conta removida com sucesso', 'Desfazer', {duration: 4000});
      this.paginator.page.emit({pageIndex: 0, pageSize: 20, length: this.paginator.length});
    } catch (e) {
      this._matSnackBar.open('Falha ao remover conta', null, {duration: 2000});
      console.error(e);
    }
  }
}
