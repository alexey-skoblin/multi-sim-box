package com.graduate.work.internal_company_database.service;

import com.graduate.work.model.entity.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

   Client create() {
       return Client.builder().build();
   }


}
