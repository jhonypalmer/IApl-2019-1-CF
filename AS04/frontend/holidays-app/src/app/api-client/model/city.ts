/**
 * Api Holiday Application Documentation
 * \"Spring Boot REST API for manage holidays\"
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
import { Country } from './country';
import { State } from './state';


export interface City { 
    country?: Country;
    id?: number;
    name?: string;
    state?: State;
}
