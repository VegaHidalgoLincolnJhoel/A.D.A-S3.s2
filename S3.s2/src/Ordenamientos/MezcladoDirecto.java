/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ordenamientos;
/**
 *
 * @author LAB-USR-LNORTE
 */


import java.io.*;
import java.util.*;

public class MezcladoDirecto {

    public static void main(String[] args) throws IOException {
        List<Integer> numbers = Arrays.asList(34, 7, 23, 32, 5, 62, 32, 7, 23, 5, 62, 34);
        int subFileSize = 4; // Tamaño del subarchivo en elementos 

        List<List<Integer>> subFiles = Dividir(numbers, subFileSize);
        List<List<Integer>> sortedSubFiles = Ordenar(subFiles);
        List<Integer> sortedNumbers = Mezclar(sortedSubFiles);
        
        System.out.println("Listado ordenado: " + sortedNumbers);
    }

    public static List<List<Integer>> Dividir(List<Integer> list, int subFileSize) {
        List<List<Integer>> subFiles = new ArrayList<>();
        int count = 0;
        List<Integer> subList = new ArrayList<>();
        
        for (Integer number : list) {
            subList.add(number);
            count++;
            if (count == subFileSize) {
                subFiles.add(new ArrayList<>(subList));
                subList.clear();
                count = 0;
            }
        }
        if (!subList.isEmpty()) {
            subFiles.add(new ArrayList<>(subList));
        }
        return subFiles;
    }

    public static List<List<Integer>> Ordenar(List<List<Integer>> subFiles) {
        List<List<Integer>> sortedSubFiles = new ArrayList<>();
        for (List<Integer> subFile : subFiles) {
            Collections.sort(subFile);
            sortedSubFiles.add(subFile);
        }
        return sortedSubFiles;
    }

    public static List<Integer> Mezclar(List<List<Integer>> sortedSubFiles) {
        PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.value));
        List<Integer> sortedList = new ArrayList<>();

        for (int i = 0; i < sortedSubFiles.size(); i++) {
            List<Integer> subFile = sortedSubFiles.get(i);
            if (!subFile.isEmpty()) {
                pq.add(new Element(subFile.remove(0), i));
            }
        }
        
        while (!pq.isEmpty()) {
            Element element = pq.poll();
            sortedList.add(element.value);
            List<Integer> subFile = sortedSubFiles.get(element.index);
            if (!subFile.isEmpty()) {
                pq.add(new Element(subFile.remove(0), element.index));
            }
        }
        return sortedList;
    }

    static class Element {

        int value;
        int index;

        Element(int value, int index) {
            this.value = value;
            this.index = index;
        }

    }
}