package by.karzhou.clevertec.TestTaskClevertec.services;

import by.karzhou.clevertec.TestTaskClevertec.models.MarketCheck;
import by.karzhou.clevertec.TestTaskClevertec.until.MarketCheckNotFoundException;
import by.karzhou.clevertec.TestTaskClevertec.repositories.MarketCheckRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
