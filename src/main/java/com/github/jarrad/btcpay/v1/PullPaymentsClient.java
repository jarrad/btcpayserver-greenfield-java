package com.github.jarrad.btcpay.v1;

import com.github.jarrad.btcpay.v1.PayoutsClient.Payout;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collection;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.jetbrains.annotations.Nullable;

public interface PullPaymentsClient {

  Collection<PullPayment> getPullPayments(final String storeId);

  PullPayment createPullPayments(final String storeId, final CreatePullPaymentCommand command);

  Payout createPayout(final String pullPaymentId, final CreatePullPaymentPayoutCommand command);

  @Value
  @Builder
  @Jacksonized
  class PullPayment {

    String id;

    String name;

    String description;

    String currency;

    BigDecimal amount;

    Long period;

    Long BOLT11Expiration;

    Boolean autoApproveClaims;

    Boolean archived;

    String viewLink;
  }

  @Value
  @Builder
  class CreatePullPaymentCommand {

    @Nullable
    String name;

    @Nullable
    String description;

    String currency;

    BigDecimal amount;

    @Nullable
    Long period;

    @Nullable
    Long BOLT11Expiration;

    @Nullable
    @Default
    boolean autoApproveClaims = false;

    @Nullable
    ZonedDateTime startsAt;

    @Nullable
    ZonedDateTime expiresAt;

    Collection<String> paymentMethods;
  }

  @Value
  @Builder
  @Jacksonized
  class CreatePullPaymentPayoutCommand {

    String destination;

    BigDecimal amount;

    @Default
    String paymentMethod = "BTC";

  }
}
