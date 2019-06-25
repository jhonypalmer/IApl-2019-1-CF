package br.ufg.es.iapl.feriados.exception

class ExceptionResponse {

    private Date timestamp
    private String message
    private String details

    ExceptionResponse(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp
        this.message = message
        this.details = details
    }

    Date getTimestamp() {
        return timestamp
    }

    String getMessage() {
        return message
    }

    String getDetails() {
        return details
    }

}
