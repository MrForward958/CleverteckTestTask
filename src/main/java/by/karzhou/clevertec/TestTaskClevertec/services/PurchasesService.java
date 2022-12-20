package by.karzhou.clevertec.TestTaskClevertec.services;

import by.karzhou.clevertec.TestTaskClevertec.models.MarketCheck;
import by.karzhou.clevertec.TestTaskClevertec.models.Purchases;
import by.karzhou.clevertec.TestTaskClevertec.repositories.PurchasesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PurchasesService {
    private final PurchasesRepository purchasesRepository;

    public PurchasesService(PurchasesRepository purchasesRepository) {
        this.purchasesRepository = purchasesRepository;
    }


    @Transactional
    public void save(Purchases purchases, MarketCheck marketCheckId) {
        purchases.setMarketCheck(marketCheckId);
        purchasesRepository.save(purchases);
    }
}
