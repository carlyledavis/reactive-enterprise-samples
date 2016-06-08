# Reactive Enterprise Samples

Within this document you will find code samples that demonstrate the content that was presented at the Agile Development Conference 2016 in Las Vegas.

![overview](https://raw.githubusercontent.com/carlyledavis/reactive-enterprise-samples/master/img/overview.png)

[![Build Status](https://snap-ci.com/carlyledavis/reactive-enterprise-samples/branch/master/build_image)](https://snap-ci.com/carlyledavis/reactive-enterprise-samples/branch/master)

## Overview

The example that is used examines the process of purchasing a airline ticket for travel.  In the sequential process you will notice an execution flow like th following:

``` Java
PaymentConfirmation paymentConfirmation = payments.secureFunds(paymentInformation);
Reservation reservation = reservations.confirmItinerary(draft);
Flight flight = flightInventory.getFlight(draft.getFlightIdentifier());
reservation.setConfirmedSeat(flight.selectSeat(seatSelection));

PurchaseConfirmation purchaseConfirmation = new PurchaseConfirmation(paymentConfirmation, reservation);
messaging.sendEmail(purchaseConfirmation);
```

The above code works fine but creates a procedural process that does not reflect the syntax of the business.  
