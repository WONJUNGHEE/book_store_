package com.example.cartservice.service;

import com.example.cartservice.client.UserServiceClient;
import com.example.cartservice.dto.CartDto;
import com.example.cartservice.jpa.CartEntity;
import com.example.cartservice.jpa.CartRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.Optional;
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
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CartEntity cartEntity = mapper.map(cartDto, CartEntity.class);

        cartRepository.save(cartEntity);
        CartDto returnValue = mapper.map(cartEntity, CartDto.class);

        return returnValue;
    }

    @Override
    public Iterable<CartEntity> getCartsByUserId(String userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public CartDto getCartsByProductName(CartDto cartDto) {
        Optional<CartEntity> cartEntity = cartRepository.findByUserIdAndProductName(cartDto.getUserId(),cartDto.getProductName());
        if (!cartEntity.isPresent()) {
            CartDto nullDto = new CartDto();
            nullDto.setFind(0);
            return nullDto;
        }
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CartDto resultDto = mapper.map(cartEntity.get(),CartDto.class);
        resultDto.setFind(1);
        return resultDto;
    }

    @Override
    public void updateCart(CartDto cartDto) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CartEntity cartEntity = mapper.map(cartDto, CartEntity.class);
        cartRepository.save(cartEntity);
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    }

    @Override
    public void deleteByUserId(String userId) {
    cartRepository.deleteByUserId(userId);
    }

    @Override
    public void deleteByProductName(String productName) {
        cartRepository.deleteByProductName(productName);
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    }
}
