package es.ucm.as.negocio.utils;

/**
 * Created by Juan Lu on 31/03/2016.
 */

public enum Frecuencia {
    DIARIA,
    SEMANAL,
    MENSUAL;

    @Override
    public String toString() {
        switch (this) {
            case DIARIA:
                return "Diaria";
            case SEMANAL:
                return "Semanal";
            case MENSUAL:
                return "Mensual";
            default:
                throw new IllegalArgumentException();
        }
    }
}

