package br.ufg.es.iapl.feriados.exception

class ResourceNotFoundException extends RuntimeException {

    ResourceNotFoundException(final String msg) {
        super(msg);
    }

}
