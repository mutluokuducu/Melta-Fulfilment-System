package com.meltaorder.mapper;

import static com.meltaorder.dto.response.ApproveStatus.Pending;

import com.meltaorder.dto.response.OrderResponseDto;
import com.meltaorder.dto.response.PackagesDto;
import com.meltaorder.dto.response.SlotDto;
import com.meltaorder.repository.entity.InstallationAddress;
import com.meltaorder.repository.entity.Packages;
import com.meltaorder.repository.entity.PersonalDetails;
import com.meltaorder.repository.entity.Slot;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderMapper {
  public static PersonalDetails getPersonalDetails(OrderResponseDto orderResponseDto) {
    PersonalDetails personalDetails = PersonalDetails.builder()
        .fullName(orderResponseDto.getPersonalDetailsDto().getFullName())
        .birthDate(orderResponseDto.getPersonalDetailsDto().getBirthDate())
        .emailAddress(orderResponseDto.getPersonalDetailsDto().getEmailAddress())
        .phoneNumber(orderResponseDto.getPersonalDetailsDto().getPhoneNumber())
        .approveStatus(Pending)
        .localDateTime(LocalDateTime.now())
        .installationAddress(InstallationAddress.builder()
            .addressLine1(orderResponseDto.getInstallationAddressDto().getAddressLine1())
            .addressLine2(orderResponseDto.getInstallationAddressDto().getAddressLine2())
            .addressLine3(orderResponseDto.getInstallationAddressDto().getAddressLine3())
            .postCode(orderResponseDto.getInstallationAddressDto().getPostCode())
            .city(orderResponseDto.getInstallationAddressDto().getCity())
            .country(orderResponseDto.getInstallationAddressDto().getCountry())
            .slotList(getSlotLIst(orderResponseDto.getInstallationAddressDto().getSlotDtoList()))
            .build())
        .packagesList(getPackageList(orderResponseDto.getPackagesDtoList()))
        .build();
    personalDetails.getInstallationAddress().setPersonalDetails(personalDetails);

    personalDetails.getPackagesList().forEach(x -> x.setPersonalDetails(personalDetails));
    personalDetails.getInstallationAddress().getSlotList()
        .forEach(x -> x.setInstallationAddress(personalDetails.getInstallationAddress()));
    return personalDetails;
  }

  private List<Slot> getSlotLIst(List<SlotDto> slotDtoList) {
    List<Slot> slots = new ArrayList<>();
    slotDtoList.forEach(slotDto -> slots.add(Slot.builder()
        .installationDate(slotDto.getInstallationDate())
        .startTime(slotDto.getStartTime())
        .endTime(slotDto.getEndTime())
        .build()));
    return slots;
  }

  private List<Packages> getPackageList(
      List<PackagesDto> packagesDtoList) {
    List<Packages> packages = new ArrayList<>();
    packagesDtoList.forEach(packagesDto -> packages.add(Packages.builder()
        .packageName(packagesDto.getPackageName())
        .productsDtoName(packagesDto.getProductsDtoName())
        .localDateTime(LocalDateTime.now())
        .build()));
    return packages;
  }
}
