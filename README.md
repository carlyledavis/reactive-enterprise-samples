# Reactive Enterprise Samples

Within this document you will find code samples that demonstrate the content that was presented at the Agile Development Conference 2016 in Las Vegas.

![overview](https://raw.githubusercontent.com/carlyledavis/reactive-enterprise-samples/master/img/overview.png)

[![Build Status](https://snap-ci.com/carlyledavis/reactive-enterprise-samples/branch/master/build_image)](https://snap-ci.com/carlyledavis/reactive-enterprise-samples/branch/master)

## Overview

The example that is used examines the process of purchasing an airline ticket for travel.  In the sequential process, you will notice an execution flow like the following:

``` Java
PaymentConfirmation paymentConfirmation = payments.secureFunds(paymentInformation);
Reservation reservation = reservations.confirmItinerary(draft);
Flight flight = flightInventory.getFlight(draft.getFlightIdentifier());
reservation.setConfirmedSeat(flight.selectSeat(seatSelection));

PurchaseConfirmation purchaseConfirmation = new PurchaseConfirmation(paymentConfirmation, reservation);
messaging.sendEmail(purchaseConfirmation);
```

The above code works fine but creates a procedural process that does not reflect the syntax of the business.  

> Once funds have been secured, confirm the itinerary and confirm the seats.  After all of the attributes of the reservation have been satisfied send out an email to the customer.

This however moves away from the procedural implementation and starts to dive into a more reactive way of looking at this problem.

## How to run

The code that you are looking at has been primarily created to illustrate design patterns and approaches.  With that said, the easiest way to walk through the execution flow is to look at the two main objects.

* ProceduralProcessor.java
* ReactiveProcessor.java

As the name would suggest they implement the same problem in either a procedural or reactive way.

For a high level overview of how the system should function you can dive into some of the tests which are meant to help you wake through the code rather than prove its production readiness.

* ProceduralProcessorTest.java
* ReactiveProcessorTest.java
