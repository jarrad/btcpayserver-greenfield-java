package com.github.jarrad.btcpay.v1;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Map;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

public interface PayoutsClient {

  Collection<Payout> getPayouts(final String storeId);

  Payout createPayout(final String storeId, final CreatePayoutCommand command);

  @Value
  @Builder
  @Jacksonized
  class Payout {

    String id;

    Long revision;

    String pullPaymentId;

    ZonedDateTime date;

    String destination;

    BigDecimal amount;

    @Default
    String paymentMethod = "BTC";

    @Default
    String cryptoCode = "BTC";

    BigDecimal paymentMethodAmount;

    String state;

    Map<String, Object> paymentProof;

    /**
     * Enum: "AwaitingApproval" "AwaitingPayment" "Cancelled" "Completed" "InProgress"
     */
    enum PayoutState {

      AwaitingApproval, AwaitingPayment, Cancelled, Completed, InProgress;

    }

  }

  @Value
  @Builder
  @Jacksonized
  class CreatePayoutCommand {

    String destination;

    BigDecimal amount;

    String paymentMethod;

    String pullPaymentId;

    @Default
    boolean approved = false;
  }

}
