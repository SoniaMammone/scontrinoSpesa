package com.exercise.java;

import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ListaSpesa {
    public static void main(String[] args) {

        /* AVANZATO: gestione della lista della spesa
L'utente deve inserire i prodotti che ha acquistato l'ultima volta che è andato
a fare la spesa (non si conosce a priori il numero di prodotti che ha acquistato).
L'utente terminerà l'inserimento dei prodotti, inserendo la stringa "FINITO". Se
vuole invece aggiungere un nuovo prodotto, deve scrivere "A".
I prodotti devono essere memorizzati all'interno di una mappa, la cui chiave è
il nome del prodotto e il cui valore è il prezzo d'acquisto.
OUTPUT: stampa ordinata esteticamente dell'intero scontrino
costo totale che ha pagato e il numero di prodotti*/

        Map<String, Double> listaSpesa = new TreeMap<>();
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        String input = " ";
        boolean finito = false;

        while (!finito) {
            System.out.print("Inserisci * A * per aggiungere un prodotto oppure * FINITO * per uscire : ");
            input = scanner.nextLine().toUpperCase();

            if (input.equals("FINITO")) {
                finito = true;
            }else if (input.equals("A")){

                generaScontrino(listaSpesa);

            } else {
                System.out.println("Scelta non valida!");
            }
        }

        stampaScontrino(listaSpesa);

    }
    public static Map<String, Double> generaScontrino (Map<String, Double> listaSpesa){

        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        double prezzoProdotto = 0.0;
        int quantita = 0;
        String nomeProdotto = " ";
        double sconto = 0.0;

        System.out.print("\nInserisci il nome del prodotto: ");
        nomeProdotto = scanner.nextLine().toUpperCase();
        //String garbage = scanner.nextLine();
        System.out.print("Inserisci le quantità: ");
        quantita = scanner.nextInt();
        String garbage = scanner.nextLine();
        System.out.print("Inserisci il prezzo del prodotto: ");
        prezzoProdotto = scanner.nextDouble();
        garbage = scanner.nextLine();
        System.out.print("Vuoi inserire uno sconto? Inserisci * SI * o * NO * ");
        String input = scanner.nextLine().toUpperCase();
        if (input.equals("SI")){
            System.out.print("Quanto è lo sconto che vuoi applicare in percentuale? ");
            sconto = scanner.nextDouble();
            sconto *= (prezzoProdotto/ 100.0);
            prezzoProdotto -= sconto;
        }
        System.out.println();

        if (quantita > 1){
            prezzoProdotto *= quantita;
        }

        listaSpesa.put(nomeProdotto, prezzoProdotto);

        return listaSpesa;
    }

    public static void stampaScontrino (Map<String, Double> listaSpesa){

        double prezzoTotale = 0.0;

        System.out.println();
        System.out.println("\033[0;92mSCONTRINO FISCALE\033[0m");
        System.out.printf("\033[0;31m%-20s \t%-10s\033[0m", "ARTICOLO", "PREZZO");
        for (String prodotto : listaSpesa.keySet()) {
            System.out.printf("\n%-20s \t€ %6.2f", prodotto, listaSpesa.get(prodotto));
            prezzoTotale += listaSpesa.get(prodotto);
        }

        System.out.println();
        System.out.printf("\n\033[0;31m%-20s \t%-10s" , "ARTICOLI TOTALI", "PREZZO TOTALE\033[0m");
        System.out.printf("\n%-20d \t€ %6.2f", listaSpesa.size(), prezzoTotale);
    }

}
