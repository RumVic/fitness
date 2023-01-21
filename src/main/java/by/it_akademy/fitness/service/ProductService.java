package by.it_akademy.fitness.service;

import by.it_akademy.fitness.exception.LockException;
import by.it_akademy.fitness.idto.InputProductDTO;
import by.it_akademy.fitness.builder.ProductBuilder;
import by.it_akademy.fitness.security.filter.JwtUtil;
import by.it_akademy.fitness.service.api.IAuditService;
import by.it_akademy.fitness.service.api.IProductService;
import by.it_akademy.fitness.service.api.IUserService;
import by.it_akademy.fitness.storage.api.IProductStorage;
import by.it_akademy.fitness.storage.entity.Product;
import by.it_akademy.fitness.storage.entity.User;
import by.it_akademy.fitness.util.EntityType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.OptimisticLockException;
import java.time.Clock;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final String CREATED = "Line in food journal was created";
    private final String UPDATED = "Line in food journal was updated";

    private final String EDITED = "Line already edited by somebody else";

    private final String LOCK = "Editing forbidden";

    @Autowired
    private final IProductStorage storage;
    @Autowired
    private final IUserService userService;
    @Autowired
    private final JwtUtil jwtUtil;
    @Autowired
    private final IAuditService auditService;


    @Override
    @Transactional
    public Product create(InputProductDTO idto, String header) {

        validate(idto);
        String login = userService.extractCurrentToken(header);
        String mail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.loadCurrentUserByLogin(mail);


        Product product = storage.save(ProductBuilder
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

        auditService.create(
                user,
                EntityType.PRODUCT,
                CREATED,
                product.getId().toString()
        );

        return product;
    }

    public Product read(UUID uuid) {
        return storage.findById(uuid).orElseThrow();
    }

    @Override
    public List<Product> get() {
        return storage.findAll();
    }

    @Override
    @Transactional
    public Product update(UUID id, Long dtUpdate, InputProductDTO idto, String header) throws LockException {

        validate(idto);
        Product readed = storage.findById(id).orElseThrow();

        String mail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.loadCurrentUserByLogin(mail);

        if (!readed.getCreatedByRole().equals(user.getLogin())) {
            if (!user.getRole().equals("ROLE_ADMIN")) {
                throw new LockException(LOCK);
            }
        }

        String login = userService.extractCurrentToken(header);

       /* if (readed == null) {
            throw new IllegalArgumentException("Product wasn't found");
        }*/


        if (!readed.getDtUpdate().equals(dtUpdate)) {
            throw new OptimisticLockException(EDITED);
        }

        Product productUpdate = storage.save(ProductBuilder
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
                .setCreatedByRole(login)
                .build());


        auditService.create(user, EntityType.PRODUCT, UPDATED, productUpdate.getId().toString());


        return productUpdate;
    }

    @Override
    public void delete(Product product) {
    }

    public void validate(InputProductDTO idto) {
        if (idto == null) {
            throw new IllegalStateException("You didn't pass the product");
        }
    }

}
