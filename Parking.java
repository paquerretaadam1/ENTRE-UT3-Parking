/**
 * La clase representa a un parking de una ciudad europea
 * que dispone de dos tarifas de aparcamiento para los clientes
 * que lo usen: la tarifa regular (que incluye una tarifa plana para
 * entradas "tempranas") y la tarifa comercial para clientes que trabajan
 * cerca del parking, aparcan un n� elevado de horas y se benefician de esta 
 * tarifa m�s econ�mica
 * (leer enunciado)
 * @Autor Pedro Jos� Aquerreta �vila
 */
public class Parking
{
    private final char REGULAR = 'R';
    private final char COMERCIAL = 'C';

    private final double PRECIO_BASE_REGULAR = 2;
    private final double PRECIO_MEDIA_REGULAR_HASTA11 = 3;
    private final double PRECIO_MEDIA_REGULAR_DESOPUES11 = 5;

    private final double HORA_INICIO_ENTRADA_TEMPRANA = 6 * 60;
    private final double HORA_FIN_ENTRADA_TEMPRANA = 8 * 60 + 30;
    private final double HORA_INICIO_SALIDA_TEMPRANA = 15 * 60;
    private final double HORA_FIN_SALIDA_TEMPRANA  = 18 * 60;
    private final double PRECIO_TARIFA_PLANA_REGULAR  = 15;

    private final double PRECIO_PRIMERAS3_COMERCIAL = 5;
    private final double PRECIO_MEDIA_COMERCIAL = 3;

    private String nombre;
    private String cliente;
    private double importeTotal;
    private int regular;
    private int comercial;
    private int clientesLunes;
    private int clientesSabado;
    private int clientesDomingo;
    private int clientesMaximoComercial;
    private double importeMaximoComercial;
    /**
     * Inicializa el parking con el nombre indicada por el par�metro.
     * El resto de atributos se inicializan a 0 
     */
    public Parking(String queNombre) {
        nombre = queNombre;
        cliente = "";
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
     *  Recibe cuatro par�metros que supondremos correctos:
     *    tipoTarifa - un car�cter 'R' o 'C'
     *    entrada - hora de entrada al parking
     *    salida � hora de salida del parking
     *    dia � n� de d�a de la semana (un valor entre 1 y 7)
     *    
     *    A partir de estos par�metros el m�todo debe calcular el importe
     *    a pagar por el cliente y mostrarlo en pantalla 
     *    y  actualizar� adecuadamente el resto de atributos
     *    del parking para poder mostrar posteriormente (en otro m�todo) las estad�sticas
     *   
     *    Por simplicidad consideraremos que un cliente entra y sale en un mismo d�a
     *    
     *    (leer enunciado del ejercicio)
     */
    public void facturarCliente(char tipoTarifa, int entrada, int salida, int dia) {
        
       

        

    }

    /**
     * Muestra en pantalla las estad�sticcas sobre el parking  
     *   
     * (leer enunciado)
     *  
     */
    public void printEstad�sticas() {
         
    }

    /**
     *  Calcula y devuelve un String que representa el nombre del d�a
     *  en el que m�s clientes han utilizado el parking - "S�BADO"   "DOMINGO" o  "LUNES"
     */
    public void diaMayorNumeroClientes() {

        

    }

}
