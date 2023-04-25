package com.github.jarrad.btcpay.v1;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.util.Collection;

public interface FeignPaymentRequestClient extends PaymentRequestClient {

  @Headers("Content-Type: application/json")
  @RequestLine("GET /api/v1/stores/{storeId}/payment-requests")
  @Override
  Collection<PaymentRequest> getPaymentRequests(@Param("storeId") final String storeId);

  @Headers("Content-Type: application/json")
  @RequestLine("POST /api/v1/stores/{storeId}/payment-requests")
  @Override
  PaymentRequest createPaymentRequest(@Param("storeId") final String storeId,
      final CreatePaymentRequestCommand command);
}
