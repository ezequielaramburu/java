/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persona;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Gabriel
 */
public class MiCalendario extends GregorianCalendar {

    public MiCalendario() {
    }

    public MiCalendario(int dia, int mes, int anio) 
            throws MiCalendarioException {
        super(anio, mes-1, dia);
        
        // Apagamos la permisividad
        this.setLenient(false);
        
        // validar
        try {
            get(SECOND);
        } catch (IllegalArgumentException e) {
            throw new MiCalendarioException(
                    "Error en la fecha ==> "+e.getMessage());
        }
    }

    private MiCalendario(long time) {
        setTimeInMillis(time);
    }

    public int getDia() {
        
        return this.get(Calendar.DAY_OF_MONTH);
    }
    public int getMes() {
        
        return this.get(Calendar.MONTH)+1;
    }
    public int getAnio() {
        
        return this.get(Calendar.YEAR);
    }
    
    @Override
    public String toString() {
        
        return String.format("%02d/%02d/%04d", getDia(), getMes(), getAnio());
    }

    public boolean esMenorQue(MiCalendario fecha) {
        if (getAnio() < fecha.getAnio()) {
            return true;
        }
        if (getAnio() == fecha.getAnio()) {
            if (getMes()<fecha.getMes()) {
                return true;
            }
            if (getMes() == fecha.getMes()) {
                if (getDia()<fecha.getDia()) {
                    return true;
                }
            }
        }
        
        return false;
    }

    public static MiCalendario convert2MiCalendario(Date date) {
        
        return new MiCalendario(date.getTime());
    }
    
    public static Date convert2SqlDate(MiCalendario cal) {
        
        return new Date(cal.getTimeInMillis());
    }

    
}
