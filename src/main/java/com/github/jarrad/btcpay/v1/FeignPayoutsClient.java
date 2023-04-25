package com.github.jarrad.btcpay.v1;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.util.Collection;

public interface FeignPayoutsClient extends PayoutsClient {

  @Headers("Content-Type: application/json")
  @RequestLine("GET /api/v1/stores/{storeId}/payouts")
  @Override
  Collection<Payout> getPayouts(@Param("storeId")final String storeId);

  @Headers("Content-Type: application/json")
  @RequestLine("POST /api/v1/stores/{storeId}/payouts")
  @Override
  Payout createPayout(@Param("storeId")final String storeId, final CreatePayoutCommand command);
}
