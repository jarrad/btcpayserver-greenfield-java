package com.github.jarrad.btcpay;

import com.github.jarrad.btcpay.v1.GreenFieldApi;
import com.github.jarrad.btcpay.v1.PaymentRequestClient.CreatePaymentRequestCommand;
import com.github.jarrad.btcpay.v1.PaymentRequestClient.PaymentRequest;
import com.github.jarrad.btcpay.v1.PullPaymentsClient.CreatePullPaymentCommand;
import com.github.jarrad.btcpay.v1.PullPaymentsClient.CreatePullPaymentPayoutCommand;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public class ScratchPad {

  public static void main(String[] args) {

    final String storeId = System.getenv().get("BTCPAY_STORE_ID");
    final String apiKey = System.getenv().get("BTCPAY_API_KEY");

    final GreenFieldApi api = GreenFieldApi.v1().url("http://localhost:8080").apiKey(apiKey)
        .connect();

    final Collection<PaymentRequest> paymentRequests = api.paymentRequests()
        .getPaymentRequests(storeId);

    final boolean createPullPayment = false;
    if (createPullPayment) {
      api.pullPayments().createPullPayments(storeId, CreatePullPaymentCommand.builder()
          .amount(new BigDecimal("0.00001"))
          .currency("BTC")
              .description("payment abc")
              .name("payment-abc")
              .paymentMethods(List.of("BTC"))
          .build());
    }

    final String pullPaymentId = "";
    final String destination = "";
    api.pullPayments().createPayout(pullPaymentId, CreatePullPaymentPayoutCommand.builder()
            .destination(destination)
            .paymentMethod("BTC")
            .amount(new BigDecimal("0.00001"))
        .build());

  }

}
