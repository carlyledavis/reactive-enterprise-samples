package config;

import dagger.Module;
import dagger.Provides;
import payments.PaymentProcessor;

@Module
class Config {

    @Provides
    PaymentProcessor paymentProcessor(){return null;}
}
