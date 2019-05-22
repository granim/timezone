package com.timezone.demo.services;

import com.timezone.demo.model.BaseClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public interface BaseClientService  extends CrudService<BaseClient, Long>{


}
