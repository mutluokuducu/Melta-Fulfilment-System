package com.meltaorder.dto.response;

import static com.meltaorder.dto.response.ProductsDto.INTERNET;
import static com.meltaorder.dto.response.ProductsDto.MOBILE;
import static com.meltaorder.dto.response.ProductsDto.TELEPHONY;
import static com.meltaorder.dto.response.ProductsDto.TV;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.stream.Stream;

public enum PackageDto {

  INTERNET_250_MBPS(INTERNET,"250Mbps"),
  INTERNET_1_GBPS(INTERNET, "1Gbps"),

  TV_90(TV, "90 Channels"),
  TV_140(TV, "140 Channels"),

  TELEPHONY_FREE_ON_NET_CALLS(TELEPHONY, "Free On net Calls"),
  TELEPHONY_UNLIMITED_CALLS(TELEPHONY, "Unlimited Calls"),

  MOBILE_PREPAID(MOBILE, "Mobile Prepaid"),
  MOBILE_POST_PAID(MOBILE, "Mobile Post-paid");

  private ProductsDto productName;
  private String packageName;


  PackageDto(ProductsDto productName, String packageName) {
    this.packageName = packageName;
    this.productName = productName;
  }

  @JsonValue
  public String getPackageName() {
    return packageName;
  }

  @JsonValue
  public ProductsDto getProductName() {
    return productName;
  }

  public static PackageDto of(final ProductsDto productName) {
    return Stream.of(PackageDto.values())
        .filter(paymentType -> paymentType.getProductName().equals(productName))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }
}
