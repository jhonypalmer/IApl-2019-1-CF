package br.ufg.es.iapl.feriados.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "holidays")
public class HolidaysDTO {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "holiday")
    List<HolidayDTO> holidays;

    public List<HolidayDTO> getHolidays() {
        return holidays;
    }

    public void setHolidays(List<HolidayDTO> holidays) {
        this.holidays = holidays;
    }
}
