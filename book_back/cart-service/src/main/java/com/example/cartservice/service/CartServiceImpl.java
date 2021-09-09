package com.example.cartservice.service;

import com.example.cartservice.dto.CartDto;
import com.example.cartservice.jpa.CartEntity;
import com.example.cartservice.jpa.CartRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import java.time.LocalDate;
=======
>>>>>>> 610f58ab9daf17f514848a28b1b6c817dde405eb
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {
    CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public CartDto createCart(CartDto cartDto) {
        cartDto.setCartId(UUID.randomUUID().toString());
        cartDto.setTotalPrice(cartDto.getQty() * cartDto.getUnitPrice());
<<<<<<< HEAD
=======

>>>>>>> 610f58ab9daf17f514848a28b1b6c817dde405eb
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CartEntity cartEntity = mapper.map(cartDto, CartEntity.class);

        cartRepository.save(cartEntity);

        CartDto returnValue = mapper.map(cartEntity, CartDto.class);

        return returnValue;
    }

<<<<<<< HEAD
    @Override
    public Iterable<CartEntity> getCartsByUserId(String userId) {
        return cartRepository.findByUserId(userId);
    }

=======
        @Override
    public CartDto getCartByOrderId(String cartId) {
        CartEntity cartEntity = cartRepository.findByCartId(cartId);
        CartDto cartDto = new ModelMapper().map(cartEntity, CartDto.class);

        return cartDto;
    }

    @Override
    public Iterable<CartEntity> getCartByUserId(String userId) {
        return null;
    }


>>>>>>> 610f58ab9daf17f514848a28b1b6c817dde405eb
}
