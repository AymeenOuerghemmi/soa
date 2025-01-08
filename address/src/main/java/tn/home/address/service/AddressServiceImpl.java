package tn.home.address.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.home.address.dto.AddressRequest;
import tn.home.address.dto.AddressResponse;
import tn.home.address.entities.Address;
import tn.home.address.repositories.AddressRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<AddressResponse> getAllAddresses() {
        return addressRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AddressResponse createAddress(AddressRequest addressRequest) {
        Address address = new Address();
        address.setStreet(addressRequest.getStreet());
        address.setCity(addressRequest.getCity());
        Address savedAddress = addressRepository.save(address);
        return convertToResponse(savedAddress);
    }

    @Override
    public AddressResponse getAddressById(Long id) {
        return addressRepository.findById(id)
                .map(this::convertToResponse)
                .orElse(null);
    }

    @Override
    public AddressResponse updateAddress(Long id, AddressRequest addressRequest) {
        Optional<Address> existingAddress = addressRepository.findById(id);
        if (existingAddress.isPresent()) {
            Address address = existingAddress.get();
            address.setStreet(addressRequest.getStreet());
            address.setCity(addressRequest.getCity());
            Address updatedAddress = addressRepository.save(address);
            return convertToResponse(updatedAddress);
        }
        return null;
    }

    @Override
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

    private AddressResponse convertToResponse(Address address) {
        AddressResponse response = new AddressResponse();
        response.setId(address.getId());
        response.setStreet(address.getStreet());
        response.setCity(address.getCity());
        return response;
    }
}
