package com.MueblesStgo.MueblesStgo.services;

import com.MueblesStgo.MueblesStgo.entities.*;
import com.MueblesStgo.MueblesStgo.repositories.SueldoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.*;

@Service
public class SueldoService {
    @Autowired
    SueldoRepository sueldoRepository;

    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    BonificacionService bonificacionService;

    @Autowired
    DescuentoService descuentoService;

    @Autowired
    AutorizacionService autorizacionService;

    @Autowired
    JustificativoService justificativoService;

    private String nombreArchivo = "DATA.txt"; // constante nombre del archivo recibido
    private String carpetaDestino="Marcas//"; // constante nombre de la carpeta contenedora de dicho archivo
    private ArrayList<ArchivoEntity> archivoEntityArrayList = new ArrayList<>();
    private int diasDelMes;
    private int largoArrayList = 0;
    private int mesEvaluado;
    private int anioEvaluado;

    /*
    El siguiente método retorna un arreglo el cual contiene TODOS los sueldos de la base de datos
     */
    public ArrayList<SueldoEntity> obtenerSueldo(){
        return (ArrayList<SueldoEntity>) sueldoRepository.findAll();
    }

    /*
    El siguiente método permite guardar un sueldo en la base de datos
     */
    public SueldoEntity guardarSueldo(SueldoEntity sueldo){
        return sueldoRepository.save(sueldo);
    }

    /////////////////////////////////////////////////////////////////////////////////////////



    /*
    El siguiente método permite determinar si, dado una fecha, el dia considerado corresponde a un
    dia de semana (laboral) o no (fin de semana)
     */
    public boolean esDiaDeSemana(int dia, int mes, int anio){
        DayOfWeek diaSemana = LocalDate.of(anio, mes, dia).getDayOfWeek();
        Locale spanish = new Locale("es", "ES");
        String diaSemanaStr = diaSemana.getDisplayName(TextStyle.FULL, Locale.forLanguageTag("es-ES"));
        if (diaSemanaStr.equals("sábado") || diaSemanaStr.equals("domingo")){
            return false;
        }
        else {
            return true;
        }
    }

    /*
    El siguiente método permite determinar si un año posee 365 días o 366 días (bisiesto cada cuatro años)
    de esta forma, los días del mes a excepción de febrero se mantienen constantes. Sin embargo, si el
    año "actual" es bisiesto, febrero tendrá 29 días, caso contrario serán 28 días
    */
    public boolean esBisiesto(float anio){
        anio = anio / 4;
        if ((anio % 1) == 0){
            return true;
        }
        else {
            return false;
        }
    }

    /*
    El siguiente método considera la obtención de los días de un mes en consideración con si el
    año ingresado es bisiesto o no
    */
    public int diasDelMes(int mes, int anio){
        if ((mes == 1) || (mes == 3) || (mes == 5) || (mes == 7) || (mes == 8) || (mes == 10) || (mes == 12)){
            return 31;
        }
        else if (mes == 2){
            if (esBisiesto(anio)){
                return 29;
            }
            else {
                return 28;
            }
        }
        else {
            return 30;
        }
    }

    public float bonificacionAniosServicio(float aniosServicio){
        float bonificacion;
        int i;
        ArrayList<BonificacionEntity> bonificacionEntityArrayList = bonificacionService.obtenerBonificacion();
        for (i = 0; i < bonificacionEntityArrayList.size(); i++){
            if(aniosServicio < bonificacionEntityArrayList.get(i).getAniosServicio()){
                if (i == 0){
                    return bonificacion = 0;
                }
                else {
                    return bonificacion = bonificacionEntityArrayList.get(i - 1).getBono();
                }
            }
        }
        return bonificacion = bonificacionEntityArrayList.get(i - 1).getBono();
    }

    public float aplicacionDescuentos(float sueldo, float porcentajeDescuento){
        float minimo = 0;
        float maximo = 100;
        if (porcentajeDescuento == minimo){
            return sueldo;
        }
        else if (porcentajeDescuento >= maximo){
            return 0;
        }
        return sueldo - (porcentajeDescuento * sueldo) / 100;
    }

    public String calculoPlanillas(String ruta, String nombreArchivo){
        File archivo = new File(ruta + nombreArchivo);
        try {
            Scanner escaner = new Scanner(archivo);
            while (escaner.hasNextLine()){
                String linea = escaner.nextLine();
                String[] parte = linea.split(";");
                String fechaTmp = parte[0].replace("/", "-");
                LocalDate fecha = LocalDate.parse(fechaTmp);
                String horaTmp = parte[1];
                LocalTime hora = LocalTime.parse(horaTmp);
                String rut = parte[2];
                diasDelMes = diasDelMes(fecha.getMonthValue(), fecha.getYear());
                mesEvaluado = fecha.getMonthValue();
                anioEvaluado = fecha.getYear();
                EmpleadoEntity empleado = new EmpleadoEntity();
                ArchivoEntity marca = new ArchivoEntity(fecha, hora, rut, empleado);
                archivoEntityArrayList.add(marca);
                largoArrayList = largoArrayList + 1;
            }
            ArrayList<EmpleadoEntity> empleados = empleadoService.obtenerEmpleados();
            for (int j = 0; j < empleados.size(); j++){
                String rutEmpleado = empleados.get(j).getRut();
                String nombreApellido = empleados.get(j).getNombre().concat(" " + empleados.get(j).getApellido());
                char categoria = empleados.get(j).getCategoria().getCategoria();
                float aniosServicio = anioEvaluado - empleados.get(j).getFechaIngresoEmpresa().getYear();
                float sueldoFijoMensual = empleados.get(j).getCategoria().getSueldoFijoMensual();
                float montoBonificacionAniosServicio = bonificacionAniosServicio(anioEvaluado - empleados.get(j).getFechaIngresoEmpresa().getYear());

                float montoPorHora = empleados.get(j).getCategoria().getMontoPorHora();

                float sueldoBruto = sueldoFijoMensual;
                float cotizacionPrevisional = descuentoService.obtenerCotizaciones()[0];
                float cotizacionSalud = descuentoService.obtenerCotizaciones()[1];
                float montoSueldoFinal = 0;


                float descuentos = 0;
                float horasExtra = 0;
                float pagoHorasExtra = 0;
                float pagoAniosServicio = 0;


                for (int i = 1; i <= diasDelMes; i++){

                    if(esDiaDeSemana(i, mesEvaluado, anioEvaluado)){
                        System.out.println("Dia " + i);
                        LocalDate fechaEvaluada = LocalDate.of(anioEvaluado, mesEvaluado, i);

                        LocalTime horaInicio = archivoEntityArrayList.get(0).getHoraIngresoSalida();
                        LocalTime horaSalida;
                        LocalTime jornada;
                        int finTurno = 0; // su maximo es 2, la entrada (1) y salida (2)


                        for (int k = 0; k < archivoEntityArrayList.size(); k++){



                            if(archivoEntityArrayList.get(k).getRutEmpleado().equals(empleados.get(j).getRut())
                            && archivoEntityArrayList.get(k).getFecha().equals(fechaEvaluada)){



                                finTurno = finTurno + 1;

                                if(finTurno == 1) {
                                    horaInicio = archivoEntityArrayList.get(k).getHoraIngresoSalida();
                                }
                                else if (finTurno == 2){ // Se completa el horario



                                    horaSalida = archivoEntityArrayList.get(k).getHoraIngresoSalida();


                                    horaSalida = horaSalida.minusHours(horaInicio.getHour());
                                    horaSalida= horaSalida.minusMinutes(horaInicio.getMinute());


                                    LocalTime tiempoFaltanteTrabajo = descuentoService.tiempoNoTrabajo(horaSalida);


                                    // Referente a descuentos
                                    if(descuentoService.descuento(tiempoFaltanteTrabajo).get(1) != 1.0){ // No puede justificar su ausencia
                                        descuentos = descuentos + descuentoService.descuento(tiempoFaltanteTrabajo).get(0);
                                    }
                                    else { // Puede justificar su ausencia

                                        if(!justificativoService.estaJustificado(fechaEvaluada, rutEmpleado)){ // No está justificado
                                            descuentos = descuentos + descuentoService.descuento(tiempoFaltanteTrabajo).get(0);
                                        }

                                    }

                                    // Referente a bonificaciones por concepto de horas extras
                                    if(autorizacionService.tieneAutorizacion(fechaEvaluada, rutEmpleado)){
                                        horasExtra = horasExtra + autorizacionService.horasExtra(fechaEvaluada, rutEmpleado);

                                    }







                                }


                            }

                        }




                    }
                }

                pagoHorasExtra = pagoHorasExtra + categoriaService.pagoHorasExtra(horasExtra, categoria);
                pagoAniosServicio = bonificacionService.sueldoBonificacionPorcentual(sueldoFijoMensual, montoBonificacionAniosServicio);
                sueldoBruto = sueldoBruto + pagoAniosServicio + pagoHorasExtra;



                System.out.println("--------------------------------");
                System.out.println("Rut: " + empleados.get(j).getRut());
                System.out.println("Nombre empleado: " + nombreApellido);
                System.out.println("Categoria: " + categoria);
                System.out.println("Anios de servicio empresa: " + aniosServicio);
                System.out.println("Sueldo fijo mensual: " + sueldoFijoMensual);
                System.out.println("Monto pago bonificacion anios servicio: " + pagoAniosServicio);
                System.out.println("Monto pago horas extra: " + pagoHorasExtra);
                System.out.println("Monto descuento: " + descuentos + "%");

                descuentos = descuentos + cotizacionPrevisional + cotizacionSalud;
                System.out.println("----------- POST DESCUENTO: " + descuentos);
                montoSueldoFinal = aplicacionDescuentos(sueldoBruto, descuentos);

                System.out.println("Sueldo bruto: " + sueldoBruto);
                System.out.println("Cotizacion previsional: " + cotizacionPrevisional + "%");
                System.out.println("Cotizacion salud: " + cotizacionSalud + "%");
                System.out.println("Monto sueldo final: " + montoSueldoFinal);
                System.out.println("--------------------------------");



                SueldoEntity sueldo = new SueldoEntity(rutEmpleado, nombreApellido, categoria, aniosServicio, sueldoFijoMensual, pagoAniosServicio, pagoHorasExtra, descuentos, sueldoBruto, cotizacionPrevisional, cotizacionSalud, montoSueldoFinal);
                guardarSueldo(sueldo);
            }





        }
        catch (FileNotFoundException error){
            error.printStackTrace();
        }
        return "El calculo se ha realizado existosamente";

    }

}
