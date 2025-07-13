package net.engineeringdigest.journalApp.cache;

import net.engineeringdigest.journalApp.entity.ConfigJournalAppEntity;
import net.engineeringdigest.journalApp.repository.ConfigJournalAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
    public enum keys{
        WEATHER_API;
    }
    public Map<String,String> appCacheMap;
    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;
    @PostConstruct
    public void init(){
        appCacheMap = new HashMap<>(); //everytime init() is called after starting of app, our appCacheMap gets empty and start again.
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
        for (ConfigJournalAppEntity configJournalAppEntity : all){
            appCacheMap.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
        }
    }
}
