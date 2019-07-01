import {Component, Input, OnInit} from '@angular/core';
import {City, CityControllerService, Country, CountryControllerService, MonthDayHoliday, State, StateControllerService} from "../../api-client";
import {MatSnackBar} from "@angular/material";

@Component({
  selector: 'app-holiday-form',
  templateUrl: './holiday-form.component.html',
  styleUrls: ['./holiday-form.component.scss']
})
export class HolidayFormComponent implements OnInit {

  @Input()
  holiday: MonthDayHoliday;

  countries: Country[] = [];
  states: State[] = [];
  cities: City[] = [];

  constructor(
    private readonly _cityControllerService: CityControllerService,
    private readonly _stateControllerService: StateControllerService,
    private readonly _countryControllerService: CountryControllerService,
    private readonly _matSnackBar: MatSnackBar,
  ) {
  }

  async ngOnInit() {
    await this.loadCountries();
    if (this.holiday.country) {
      await this.loadStates(this.holiday.country);
    }
    if (this.holiday.state) {
      await this.loadCities(this.holiday.state);
    }
  }

  async loadCountries() {
    try {
      this.countries = await this._countryControllerService.getCountriesUsingGET().toPromise()
    } catch (e) {
      this._matSnackBar.open('Falha ao carregar paises', null, {duration: 2000})
    }
  }

  async loadStates(country: string) {
    this.cities = [];
    this.holiday.state = undefined;
    this.holiday.city = undefined;
    try {
      this.states = await this._stateControllerService.getStatesByCountryUsingGET(country).toPromise();
    } catch (e) {
      this._matSnackBar.open('Falha ao carregar estados', null, {duration: 2000})
    }
  }

  async loadCities(state: string) {
    this.holiday.city = undefined;
    try {
      this.cities = await this._cityControllerService.getCitiesByStateUsingGET(state).toPromise()
    } catch (e) {
      this._matSnackBar.open('Falha ao carregar cidades', null, {duration: 2000});
      console.error(e);
    }
  }

}
