package ru.geekbrains.products.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import ru.geekbrains.api.ApiCartItem;
import ru.geekbrains.api.ApiCartItemsView;
import ru.geekbrains.products.entity.Cart;
import ru.geekbrains.products.mapper.cart.CartMapper;
import ru.geekbrains.products.model.CartDto;
import ru.geekbrains.products.model.ProductDto;
import ru.geekbrains.products.repository.CartRepository;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CartService {
    private  final CartRepository cartRepository;
    private  final CartMapper cartMapper;

    public Page<Cart> findAll(int pageIndex, int pageSize){
        return cartRepository.findAll(PageRequest.of(pageIndex, pageSize));
    }

    public void deleteById(Long id){
        cartRepository.deleteById(id);
    }


    @Transactional
    public void addNewProductToCart(Long id, Double count){
        Optional<Cart> cartFind = cartRepository.findByUseridAndProductid(1L,id);
        if (cartFind.isEmpty()){
            cartRepository.insertCart(1L, id, count);
        }else{
            cartFind.get().setAmount(count);
        }


    }
    public Double sumItogCart(Long id){
        List<Cart> carts = cartRepository.findAllByUserId(1L);
        Double sum = Double.valueOf(0);
        if (!carts.isEmpty()) {
        for (int i=0; i<carts.size(); i++) {
            CartDto cartDto = cartMapper.mapToDto(carts.get(i));
            sum = sum + cartDto.getProductPrice() * cartDto.getAmount();
        }
        }
        return sum;
    }

    @Transactional
    public void incAmount(Long id){
        Optional<Cart> cart = cartRepository.findById(id);
        if (!cart.isEmpty()){
            cartRepository.updateAmount(cart.get().getAmount() + 1, cart.get().getId());
        }
    }

    @Transactional
    public void subAmount(Long id){
        Optional<Cart> cart = cartRepository.findById(id);
        if (!cart.isEmpty()){
            if (cart.get().getAmount() > 0) {
                cartRepository.updateAmount(cart.get().getAmount() - 1, cart.get().getId());
            }
        }
    }
}
