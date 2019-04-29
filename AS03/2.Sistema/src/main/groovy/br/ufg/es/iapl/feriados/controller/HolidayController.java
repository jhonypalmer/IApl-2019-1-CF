package br.ufg.es.iapl.feriados.controller;

import br.ufg.es.iapl.feriados.dto.HolidaysDTO;
import br.ufg.es.iapl.feriados.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("holiday")
public class HolidayController {

    private static final String APPLICATION_FIXED_POSITION = "application/fixedPosition";

    @Autowired
    private HolidayService holidayService;

    @GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, APPLICATION_FIXED_POSITION})
    public HolidaysDTO getHolidays() {
        return holidayService.findAllHolidays();
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, APPLICATION_FIXED_POSITION})
    public HolidaysDTO getHoliday(@PathVariable Long id) {
        return holidayService.findHolidayById(id);
    }

    @PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, APPLICATION_FIXED_POSITION})
    public ResponseEntity<HolidaysDTO> saveHolidays(@RequestBody HolidaysDTO holidaysDTO) {
        holidayService.saveHolidays(holidaysDTO);
        return ResponseEntity.ok(holidaysDTO);
    }
}
