package com.github.jarrad.btcpay.v1;

import static java.util.Objects.requireNonNull;

import feign.RequestInterceptor;
import feign.RequestTemplate;

class AuthorizationRequestInterceptor implements RequestInterceptor {

  private final String apiKey;

  private final String headerValue;

  AuthorizationRequestInterceptor(final String apiKey) {
    this.apiKey = requireNonNull(apiKey);
    this.headerValue = String.format("token %s", this.apiKey);
  }

  @Override
  public void apply(final RequestTemplate template) {
    template.header("Authorization", headerValue);
  }
}
