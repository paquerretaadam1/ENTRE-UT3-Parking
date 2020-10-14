/**
 * La clase representa a un parking de una ciudad europea
 * que dispone de dos tarifas de aparcamiento para los clientes
 * que lo usen: la tarifa regular (que incluye una tarifa plana para
 * entradas "tempranas") y la tarifa comercial para clientes que trabajan
 * cerca del parking, aparcan un nº elevado de horas y se benefician de esta 
 * tarifa más económica
 * (leer enunciado)
 * @Autor Pedro José Aquerreta Ávila
 */
public class Parking
{
    private final char REGULAR = 'R';
    private final char COMERCIAL = 'C';

    private final double PRECIO_BASE_REGULAR = 2;
    private final double PRECIO_MEDIA_REGULAR_HASTA11 = 3;
    private final double PRECIO_MEDIA_REGULAR_DESPUES11 = 5;

    private final double HORA_INICIO_ENTRADA_TEMPRANA = 6 * 60;
    private final double HORA_FIN_ENTRADA_TEMPRANA = 8 * 60 + 30;
    private final double HORA_INICIO_SALIDA_TEMPRANA = 15 * 60;
    private final double HORA_FIN_SALIDA_TEMPRANA  = 18 * 60;
    private final double PRECIO_TARIFA_PLANA_REGULAR  = 15;

    private final double PRECIO_PRIMERAS3_COMERCIAL = 5;
    private final double PRECIO_MEDIA_COMERCIAL = 3;

    private String nombre;
    private int cliente;
    private double importeTotal;
    private int regular;
    private int comercial;
    private int clientesLunes;
    private int clientesSabado;
    private int clientesDomingo;
    private int clientesMaximoComercial;
    private double importeMaximoComercial;
    /**
     * Inicializa el parking con el nombre indicada por el parámetro.
     * El resto de atributos se inicializan a 0 
     */
    public Parking(String queNombre) {
        nombre = queNombre;
        cliente = 0;
        importeTotal = 0;
        regular = 0;
        comercial = 0;
        clientesLunes = 0;
        clientesSabado = 0;
        clientesDomingo = 0;
        clientesMaximoComercial = 0;
        importeMaximoComercial = 0;

    }

    /**
     * accesor para el nombre del parking
     *  
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * mutador para el nombre del parking
     *  
     */
    public void setNombre(String queNombre) {
        nombre = queNombre;
    }

    /**
     *  Recibe cuatro parámetros que supondremos correctos:
     *    tipoTarifa - un carácter 'R' o 'C'
     *    entrada - hora de entrada al parking
     *    salida – hora de salida del parking
     *    dia – nº de día de la semana (un valor entre 1 y 7)
     *    
     *    A partir de estos parámetros el método debe calcular el importe
     *    a pagar por el cliente y mostrarlo en pantalla 
     *    y  actualizará adecuadamente el resto de atributos
     *    del parking para poder mostrar posteriormente (en otro método) las estadísticas
     *   
     *    Por simplicidad consideraremos que un cliente entra y sale en un mismo día
     *    
     *    (leer enunciado del ejercicio)
     */
    public void facturarCliente(char tipoTarifa, int entrada, int salida, int dia) {
        double importe = 0;
        String tarifaAAplicar;
        String entradaString;
        String salidaString;

        int horaEntrada = entrada / 100;
        int minutosEntrada = entrada % 100;
        int entradaEnMinutos = horaEntrada * 60 + minutosEntrada;                  

        int horaSalida = salida / 100;
        int minutosSalida = salida % 100;
        int salidaEnMinutos = horaSalida * 60 + minutosSalida;

        int tiempoEstacionado = salidaEnMinutos - entradaEnMinutos;                //Tiempo aparcado en minutos
        int periodosEstacionado = tiempoEstacionado / 30;                          //Divido entre 30 porque es el intervalo de tiempo por el que se cobra una vez la tarifa

        int tiempoEstacionadoAntes11 = 660 - entradaEnMinutos;                     //Tiempo que está aparcado hasta antes de las 11h                 
        int periodosEstacionadoAntes11 = tiempoEstacionadoAntes11 / 30;            //Nº de periodos antes de las 11h    
        int tiempoEstacionadoDespues11 = salidaEnMinutos - 660;                    //Tiempo que está aparcado hasta antes de las 11h
        int periodosEstacionadoDespues11 = tiempoEstacionadoDespues11 / 30;        //Nº de periodos antes de las 11h

        cliente ++;

        switch(tipoTarifa){
            case REGULAR:

            regular ++;         

            if(entradaEnMinutos >= HORA_INICIO_ENTRADA_TEMPRANA &&
            entradaEnMinutos <= HORA_FIN_ENTRADA_TEMPRANA
            && salidaEnMinutos >= HORA_INICIO_SALIDA_TEMPRANA &&
            salidaEnMinutos <= HORA_FIN_SALIDA_TEMPRANA) {
                tarifaAAplicar = "REGULAR Y TEMPRANA";
                importe = 15;
            }
            else{
                importe = PRECIO_BASE_REGULAR;
                tarifaAAplicar = "REGULAR";
                if (entradaEnMinutos < 660 && salidaEnMinutos < 660){
                    importe += periodosEstacionado * PRECIO_MEDIA_REGULAR_HASTA11;

                }
                else if (entradaEnMinutos >= 660){
                    importe += periodosEstacionado * PRECIO_MEDIA_REGULAR_DESPUES11;

                }
                else{
                    importe += periodosEstacionadoAntes11
                    * PRECIO_MEDIA_REGULAR_HASTA11 + 
                    periodosEstacionadoDespues11 * PRECIO_MEDIA_REGULAR_DESPUES11;

                }
            }
            break;
            default:
            tarifaAAplicar = "COMERCIAL";
            comercial ++;
            importe = PRECIO_PRIMERAS3_COMERCIAL;

            if (tiempoEstacionado > 3){
                importe +=  ((periodosEstacionado - 6) *
                    PRECIO_MEDIA_COMERCIAL);
            }

            if (comercial == 1) {
                importeMaximoComercial = importe;
                clientesMaximoComercial = cliente;
            }
            else if (importeMaximoComercial < importe){
                clientesMaximoComercial = cliente;
                importeMaximoComercial = importe;
            }
            break;
        }
        if (dia == 1){
            clientesLunes ++; 
        }
        else if(dia == 6){
            clientesSabado ++;
        }
        else if(dia == 7){
            clientesDomingo ++;
        }
        importeTotal += importe;
        if(minutosEntrada < 10){
            entradaString = "0" + minutosEntrada;
        }
        else{
            entradaString = "" + minutosEntrada;
        }
        if(minutosSalida < 10){
            salidaString = "0" + minutosSalida;
        }
        else{
            salidaString = "" + minutosSalida;
        }
        System.out.println("*************Parking de Pedro***************" + 
            "\nCliente nº: " + cliente +
            "\nHora entrada: " + horaEntrada + ":" + entradaString  + 
            "\nHora salida: " + horaSalida + ":" + salidaString  + 
            "\nTarifa a aplicar: " + tarifaAAplicar +
            "\nImporte a pagar: " + importe + "€" +
            "\n****************************");
    }

    /**
     * Muestra en pantalla las estadísticcas sobre el parking  
     *   
     * (leer enunciado)
     *  
     */
    public void printEstadísticas() {
        System.out.println("****************************************" + 
            "\nImporte total entre todos los clientes: " + importeTotal +
            "\nNº clientes tarifa regular: " + regular +
            "\nNº clientes tarifa comercial: " + comercial +
            "\nCliente tarifa COMERCIAL con factura máxima fue el "+ clientesMaximoComercial + 
            "\n y pagó " + importeMaximoComercial + "€" +
            "\n****************************************");
    }

    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que más clientes han utilizado el parking - "SÁBADO"   "DOMINGO" o  "LUNES"
     */
    public String diaMayorNumeroClientes() {
        String MayorNumeroClientes;
        if (clientesLunes > clientesSabado){
            if(clientesLunes > clientesDomingo){
                MayorNumeroClientes = "LUNES";
            }
            else{
                MayorNumeroClientes = "DOMINGO";
            }
        }
        else if (clientesSabado > clientesDomingo){
            MayorNumeroClientes = "SÁBADO";
        }
        else if (clientesDomingo > clientesDomingo) {
            MayorNumeroClientes = "DOMINGO";
        }
        else {
            MayorNumeroClientes = "COINCIDEN";
        }
        return MayorNumeroClientes;
    }
}
