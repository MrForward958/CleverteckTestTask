package by.karzhou.clevertec.TestTaskClevertec.services;

import by.karzhou.clevertec.TestTaskClevertec.models.MarketCheck;
import by.karzhou.clevertec.TestTaskClevertec.models.Purchases;
import by.karzhou.clevertec.TestTaskClevertec.until.MarketCheckNotFoundException;
import by.karzhou.clevertec.TestTaskClevertec.repositories.MarketCheckRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MarketCheckService {

    private final MarketCheckRepository marketCheckRepository;

    public MarketCheckService(MarketCheckRepository marketCheckRepository) {
        this.marketCheckRepository = marketCheckRepository;
    }

    public List<MarketCheck> findAll(){
        return marketCheckRepository.findAll();
    }

    public MarketCheck findById(int id){
        Optional<MarketCheck> foundCheck = marketCheckRepository.findMarketCheckById(id);
        return foundCheck.orElseThrow(MarketCheckNotFoundException::new);
    }

    public double countSum(int id){
        double sum = 0;
        MarketCheck marketCheck = marketCheckRepository.findMarketCheckById(id).orElse(null);
        if(!marketCheck.equals(null)){
            for ( Purchases purchase : marketCheck.getPurchasesList()){
                sum += purchase.getAmount() * purchase.getProductId().getPrice();
            }
        }
        return sum;
    }

    @Transactional
    public void save(MarketCheck marketCheck){
        marketCheck.setMarketName("Clevertec");
        marketCheck.setCreateDate(new Date(System.currentTimeMillis()));
        marketCheck.setOpenStatus(true);
        marketCheckRepository.save(marketCheck);
    }

    @Transactional
    public void update(Purchases purchases, int id) {
        MarketCheck marketCheck = marketCheckRepository.findMarketCheckById(id).get();
        marketCheck.getPurchasesList().add(purchases);
        marketCheckRepository.save(marketCheck);
    }

    @Transactional
    public void closeCheck(int id){
        MarketCheck marketCheck = marketCheckRepository.findMarketCheckById(id).get();
        marketCheck.setOpenStatus(false);
        marketCheckRepository.save(marketCheck);
    }
}
