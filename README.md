# BarOrder

[![Build Status](https://travis-ci.org/therickys93/barorder.svg?branch=master)](https://travis-ci.org/therickys93/barorder)

Server scritto in Java in grado di gestire gli ordini di un bar, ristorante, pizzeria...

## Installazione ( con Docker )

### Se Docker è già installato:

```
eval "$(curl -sL https://raw.githubusercontent.com/therickys93/barorder/master/install_only_barorder.sh)"
```

### Se Docker è da installare:

```
eval "$(curl -sL https://raw.githubusercontent.com/therickys93/barorder/master/install_docker_and_barorder.sh)"
```

## Installazione ( senza Docker )

BarOrder per funzionare richiede Java 8 e MySQL Server. Si può inoltre configurare un reverse proxy con nginx e utilizzare supervisor per riattivare il server in caso di errore o di spegnimento del computer. 

## Endpoints

* GET /v1/status --> mostra lo stato del sistema
* GET /v1/products --> mostra tutti i prodotti salvati
* POST /v1/insertOrder --> inserisce un nuovo ordine
* GET /v1/orders --> prende tutti gli ordini da effettuare
* POST /v1/completeOrder --> completa l'ordine
* GET /v1/payments --> prende tutti gli ordini da pagare
* POST /v1/payOrder --> paga l'ordine
* POST /v1/deleteProductAll --> rimuove tutti i prodotti
* POST /v1/deleteProduct/{product} --> rimuove il prodotto
* POST /v1/insertProduct/{product} --> aggiunge il prodotto

## Oggetti

* Product: semplice stringa di testo
* Order: oggetto JSON ```{"id":102, "table":20, "done":false, products:[{"name":"Cioccolata con panna", "quantity":2}, {"name":"Cappuccino", "quantity":2}]}```
