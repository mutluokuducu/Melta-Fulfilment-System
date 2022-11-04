package com.meltaorder.dto.response;


import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class OrderResponseDto {

  @NotNull
  private PersonalDetailsDto personalDetailsDto;
  @NotNull
  private InstallationAddressDto installationAddressDto;
  @NotNull
  private List<PackagesDto> packagesDtoList;

}
