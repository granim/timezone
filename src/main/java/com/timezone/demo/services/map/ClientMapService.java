package com.timezone.demo.services.map;

import com.timezone.demo.model.Client;
import com.timezone.demo.services.ClientService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.TimeZone;

@Service
@Profile({ "map"})
public class ClientMapService extends AbstractMapService<Client, Long> implements ClientService {
    @Override
    public Set<Client> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Client object) {
        super.delete(object);
    }

    @Override
    public Client save(Client object) {
        return super.save(object);
    }

    @Override
    public Client findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Client findByCompanyName(String companyName) {
        return this.findAll()
                .stream()
                .filter(client -> client.getCompanyName().equalsIgnoreCase(companyName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Client> findAllByCompanyNameLike(String companyName) {
        //TODO - impl
        return null;

    }

    @Override
    public Client findByTimeZone(TimeZone timeZone) {
        //TODO not right fix this
        Client client = new Client();
        client.getTimeZone();
        return client;
    }


}
