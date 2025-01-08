package tn.home.address.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.home.address.dto.AddressRequest;
import tn.home.address.dto.AddressResponse;
import tn.home.address.service.AddressService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    // Créer une nouvelle adresse
    @PostMapping
    public AddressResponse createAddress(@RequestBody AddressRequest addressRequest) {
        return addressService.createAddress(addressRequest);
    }

    // Récupérer toutes les adresses
    @GetMapping
    public List<AddressResponse> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    // Récupérer une adresse par ID
    @GetMapping("/{id}")
    public AddressResponse getAddressById(@PathVariable Long id) {
        return addressService.getAddressById(id);
    }

    // Mettre à jour une adresse
    @PutMapping("/{id}")
    public AddressResponse updateAddress(@PathVariable Long id, @RequestBody AddressRequest addressRequest) {
        return addressService.updateAddress(id, addressRequest);
    }

    // Supprimer une adresse
    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
    }
}
