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
        if (tipoTarifa == COMERCIAL){
            tarifaAAplicar = "COMERCIAL";
        }
        else if(tipoTarifa == REGULAR && (entrada < 600 || entrada > 830 ||
            salida < 1500 || salida > 1800) ){
            tarifaAAplicar = "REGULAR";
        }
        else{
            tarifaAAplicar = "REGULAR Y TEMPRANA";
        }
        cliente ++;
        switch(tipoTarifa){
            case REGULAR:
            regular ++;
            if(entrada >= 600 && entrada <= 830 && salida >= 1500 && salida <= 1800) {
                importe = 15;
            }
            else{
                if (entrada - 1100 > 0 && salida - 1100 > 0){
                    importe = PRECIO_BASE_REGULAR + 
                    ((salida - entrada) / 100) * 2 * PRECIO_MEDIA_REGULAR_HASTA11;
                    if(salida - (salida / 100 * 100) > 30){
                        importe += PRECIO_MEDIA_REGULAR_HASTA11;
                    }
                }
                else if (entrada - 1100 < 0 && salida - 1100 < 0){
                    importe = PRECIO_BASE_REGULAR +
                    (salida - entrada) / 100 * PRECIO_MEDIA_REGULAR_DESPUES11;
                    if(salida - (salida / 100 * 100) > 0){
                        importe += PRECIO_MEDIA_REGULAR_DESPUES11;
                    }
                }
                else{
                    importe = PRECIO_BASE_REGULAR + 
                    ((1100 - entrada) / 100) * 2 * PRECIO_MEDIA_REGULAR_HASTA11 + 
                    ((salida - 1100) / 100) * 2 * PRECIO_MEDIA_REGULAR_DESPUES11;
                    if(entrada - (entrada / 100 * 100) > 0){
                        importe += PRECIO_MEDIA_REGULAR_HASTA11;
                    }
                    if(salida - (salida / 100 * 100) > 0){
                        importe += PRECIO_MEDIA_REGULAR_DESPUES11;
                    }
                }
            }
            break;
            default:
            comercial ++;
            importe = PRECIO_PRIMERAS3_COMERCIAL;
            if (salida - entrada > 3){
                importe += (salida - entrada - 300) / 100 *
                2 * PRECIO_MEDIA_COMERCIAL;
                if((salida - entrada) -((salida - entrada) / 100 * 100) > 0){
                    importe += PRECIO_MEDIA_COMERCIAL;
                }
            }

            if (comercial == 1) {
                importeMaximoComercial = importe;
                clientesMaximoComercial = cliente;
            }
            else{
                if (importeMaximoComercial < importe){
                    importeMaximoComercial = importe;
                    clientesMaximoComercial = cliente;
                }
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
        System.out.println("*************Parking de Pedro***************" + 
            "\nCliente nº: " + cliente +
            "\nHora entrada: " + (entrada / 100) + ":" + (entrada - (entrada / 100 * 100))  + 
            "\nHora salida: " + (salida / 100) + ":" + (salida - (salida / 100 * 100))  + 
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
        System.out.println("Importe total entre todos los clientes: " + importeTotal +
            "\nNº clientes tarifa regular: " + regular +
            "\nNº clientes tarifa comercial: " + comercial +
            "\nCliente tarifa COMERCIAL con factura máxima fue el "+ cliente + 
            "\n y pagó" + importeMaximoComercial + "€");
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
            MayorNumeroClientes = "Indeterminado";
        }
        return MayorNumeroClientes;
    }
}
