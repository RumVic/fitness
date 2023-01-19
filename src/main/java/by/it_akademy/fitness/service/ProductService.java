package by.it_akademy.fitness.service;

import by.it_akademy.fitness.idto.InputProductDTO;
import by.it_akademy.fitness.builder.ProductBuilder;
import by.it_akademy.fitness.security.filter.JwtUtil;
import by.it_akademy.fitness.service.api.IProductService;
import by.it_akademy.fitness.service.api.IUserService;
import by.it_akademy.fitness.storage.api.IProductStorage;
import by.it_akademy.fitness.storage.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService implements IProductService {
    @Autowired
    private final IProductStorage storage;
    @Autowired
    private final IUserService userService;
    @Autowired
    private final JwtUtil jwtUtil;



    /*public ProductService(IProductStorage productStorage) {
        this.storage = productStorage;
    }*/

    @Override
    @Transactional
    public Product create(InputProductDTO idto,String header) {
        String login = extractToken(header);
        return storage.save(ProductBuilder
                .create()
                .setId(UUID.randomUUID())
                .setDtCreate(Clock.systemUTC().millis())
                .setDtUpdate(Clock.systemUTC().millis())
                .setTitle(idto.getTitle())
                .setWeight(idto.getWeight())
                .setCalories(idto.getCalories())
                .setProteins(idto.getProteins())
                .setFats(idto.getFats())
                .setCarbohydrates(idto.getCarbohydrates())
                .setCreatedByRole(login)
                .build());
    }

    public Product read (UUID uuid) {
        return storage.findById(uuid).orElseThrow();
    }

    @Override
    public List<Product> get() {
        return storage.findAll();
    }

    @Override
    @Transactional
    public Product update(UUID id, Long dtUpdate, InputProductDTO idto) {

    Product readed = storage.findById(id).orElseThrow();

        if (readed == null) {
            throw new IllegalArgumentException("Меню не найдено");
        }


        if (!readed.getDtUpdate().equals(dtUpdate)) {
            throw new IllegalArgumentException("К сожалению меню уже было отредактировано кем-то другим");
        }

        Product productUpdate = ProductBuilder
                .create()
                .setId(readed.getId())
                .setDtCreate(readed.getDtCreate())
                .setDtUpdate(Clock.systemUTC().millis())
                .setTitle(idto.getTitle())
                .setWeight(idto.getWeight())
                .setCalories(idto.getCalories())
                .setProteins(idto.getProteins())
                .setFats(idto.getFats())
                .setCarbohydrates(idto.getCarbohydrates())
                .build();
        return storage.save(productUpdate);
    }

    @Override
    public void delete(Product product) {
    }
    public String extractToken(String authHeader) {

        String jwtToken = authHeader.substring(7);
        String email = jwtUtil.extractUsername(jwtToken);
        UserDetails currentUser = userService.loadUserByLogin(email);
        String currentLogin = currentUser.getUsername();
        return currentLogin;
    }
}
