package com.github.jarrad.btcpay.v1;

import com.github.jarrad.btcpay.v1.PayoutsClient.Payout;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.util.Collection;

public interface FeignPullPaymentsClient extends PullPaymentsClient {

  @Headers("Content-Type: application/json")
  @RequestLine("GET /api/v1/stores/{storeId}/pull-payments")
  @Override
  Collection<PullPayment> getPullPayments(@Param("storeId") final String storeId);

  @Headers("Content-Type: application/json")
  @RequestLine("POST /api/v1/stores/{storeId}/pull-payments")
  @Override
  PullPayment createPullPayments(@Param("storeId") final String storeId,
      final CreatePullPaymentCommand command);

  @Headers("Content-Type: application/json")
  @RequestLine("POST /api/v1/pull-payments/{pullPaymentId}/payouts")
  @Override
  Payout createPayout(@Param("pullPaymentId") final String pullPaymentId,
      final CreatePullPaymentPayoutCommand command);
}
