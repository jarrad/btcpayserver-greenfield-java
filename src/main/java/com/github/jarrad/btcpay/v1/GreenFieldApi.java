package com.github.jarrad.btcpay.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.Feign;
import feign.Logger.Level;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import java.util.concurrent.atomic.AtomicReference;
import lombok.Builder;

@Builder(builderClassName = "ApiBuilder", builderMethodName = "v1", buildMethodName = "connect")
public class GreenFieldApi {

  private String url;

  private String apiKey;

  private final AtomicReference<FeignPaymentRequestClient> paymentRequestClient = new AtomicReference<>();

  private final AtomicReference<FeignPullPaymentsClient> pullPaymentClient = new AtomicReference<>();

  private final AtomicReference<FeignPayoutsClient> payoutsClient = new AtomicReference<>();

  private final AtomicReference<AuthorizationRequestInterceptor> requestInterceptor = new AtomicReference<>();

  public PaymentRequestClient paymentRequests() {
    return client(paymentRequestClient, FeignPaymentRequestClient.class);
  }

  public PullPaymentsClient pullPayments() {
    return client(pullPaymentClient, FeignPullPaymentsClient.class);
  }

  public PayoutsClient payouts() {
    return client(payoutsClient, FeignPayoutsClient.class);
  }

  private <T> T client(final AtomicReference<T> clientRef, final Class<T> clientType) {
    final T client = clientRef.get();
    if (client != null) {
      return client;
    }
    if (requestInterceptor.get() == null) {
      requestInterceptor.set(new AuthorizationRequestInterceptor(apiKey));
    }
    final T newClient = Feign.builder()
        .logLevel(Level.BASIC)
        .encoder(new JacksonEncoder(
            new ObjectMapper().findAndRegisterModules().registerModule(new JavaTimeModule())))
        .decoder(new JacksonDecoder(
            new ObjectMapper().findAndRegisterModules().registerModule(new JavaTimeModule())))
        .requestInterceptor(requestInterceptor.get())
        // FIXME: add interceptor for version header
        .target(clientType, url);
    clientRef.set(newClient);
    return newClient;
  }


}
