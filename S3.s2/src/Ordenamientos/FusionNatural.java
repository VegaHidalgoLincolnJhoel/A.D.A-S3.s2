package Ordenamientos;

import java.util.ArrayList;
import java.util.List;

public class FusionNatural {

    public static void main(String[] args) {
        // Arreglo inicial
        int[] listado = {34, 7, 23, 32, 5, 62, 32, 7, 23, 5, 62, 34};
        
        // Ordenar el arreglo
        int[] listadoOrdenado = ordenamientoExternoFusionNatural(listado);
        
        // Imprimir el resultado
        for (int num : listadoOrdenado) {
            System.out.print(num + " ");
        }
    }

    public static int[] ordenamientoExternoFusionNatural(int[] listado) {
        // Dividir en secuencias naturales
        List<List<Integer>> secuencias = dividirEnSecuenciasNaturales(listado);
        
        // Mezclar las secuencias
        List<Integer> listadoOrdenado = mezclar(secuencias);
        
        // Convertir la lista ordenada a un arreglo
        return listadoOrdenado.stream().mapToInt(i -> i).toArray();
    }

    public static List<List<Integer>> dividirEnSecuenciasNaturales(int[] listado) {
        List<List<Integer>> secuencias = new ArrayList<>();
        List<Integer> secuenciaActual = new ArrayList<>();
        
        // Agregar el primer elemento a la secuencia actual
        secuenciaActual.add(listado[0]);
        
        for (int i = 1; i < listado.length; i++) {
            if (listado[i] >= listado[i - 1]) {
                // Si el elemento actual es mayor o igual al anterior, continúa la secuencia
                secuenciaActual.add(listado[i]);
            } else {
                // Si no, termina la secuencia actual y comienza una nueva
                secuencias.add(new ArrayList<>(secuenciaActual));
                secuenciaActual.clear();
                secuenciaActual.add(listado[i]);
            }
        }
        // Agregar la última secuencia
        secuencias.add(secuenciaActual);
        
        return secuencias;
    }

    public static List<Integer> mezclar(List<List<Integer>> secuencias) {
        while (secuencias.size() > 1) {
            List<List<Integer>> nuevasSecuencias = new ArrayList<>();
            
            for (int i = 0; i < secuencias.size(); i += 2) {
                if (i + 1 < secuencias.size()) {
                    // Mezclar dos secuencias adyacentes
                    nuevasSecuencias.add(mezclarDosSecuencias(secuencias.get(i), secuencias.get(i + 1)));
                } else {
                    // Agregar la última secuencia si no tiene pareja
                    nuevasSecuencias.add(secuencias.get(i));
                }
            }
            secuencias = nuevasSecuencias;
        }
        
        return secuencias.get(0);
    }

    public static List<Integer> mezclarDosSecuencias(List<Integer> secuencia1, List<Integer> secuencia2) {
        List<Integer> resultado = new ArrayList<>();
        int i = 0, j = 0;
        
        // Mezclar las dos secuencias en orden
        while (i < secuencia1.size() && j < secuencia2.size()) {
            if (secuencia1.get(i) <= secuencia2.get(j)) {
                resultado.add(secuencia1.get(i));
                i++;
            } else {
                resultado.add(secuencia2.get(j));
                j++;
            }
        }
        
        // Agregar los elementos restantes de la primera secuencia
        while (i < secuencia1.size()) {
            resultado.add(secuencia1.get(i));
            i++;
        }
        
        // Agregar los elementos restantes de la segunda secuencia
        while (j < secuencia2.size()) {
            resultado.add(secuencia2.get(j));
            j++;
        }
        
        return resultado;
    }
}