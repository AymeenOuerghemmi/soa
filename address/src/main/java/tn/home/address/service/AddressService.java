package tn.home.address.service;

import tn.home.address.dto.AddressRequest;
import tn.home.address.dto.AddressResponse;

import java.util.List;

public interface AddressService {
    List<AddressResponse> getAllAddresses();
    AddressResponse createAddress(AddressRequest addressRequest);
    AddressResponse getAddressById(Long id);
    AddressResponse updateAddress(Long id, AddressRequest addressRequest);
    void deleteAddress(Long id);
}
