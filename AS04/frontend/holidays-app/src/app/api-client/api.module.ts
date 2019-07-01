import { NgModule, ModuleWithProviders, SkipSelf, Optional } from '@angular/core';
import { Configuration } from './configuration';
import { HttpClient } from '@angular/common/http';


import { AppliedHolidayControllerService } from './api/appliedHolidayController.service';
import { BankControllerService } from './api/bankController.service';
import { CityControllerService } from './api/cityController.service';
import { CountryControllerService } from './api/countryController.service';
import { HolidayControllerService } from './api/holidayController.service';
import { StateControllerService } from './api/stateController.service';

@NgModule({
  imports:      [],
  declarations: [],
  exports:      [],
  providers: [
    AppliedHolidayControllerService,
    BankControllerService,
    CityControllerService,
    CountryControllerService,
    HolidayControllerService,
    StateControllerService ]
})
export class ApiModule {
    public static forRoot(configurationFactory: () => Configuration): ModuleWithProviders {
        return {
            ngModule: ApiModule,
            providers: [ { provide: Configuration, useFactory: configurationFactory } ]
        };
    }

    constructor( @Optional() @SkipSelf() parentModule: ApiModule,
                 @Optional() http: HttpClient) {
        if (parentModule) {
            throw new Error('ApiModule is already loaded. Import in your base AppModule only.');
        }
        if (!http) {
            throw new Error('You need to import the HttpClientModule in your AppModule! \n' +
            'See also https://github.com/angular/angular/issues/20575');
        }
    }
}
