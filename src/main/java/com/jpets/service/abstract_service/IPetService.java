package com.jpets.service.abstract_service;

import com.jpets.controller.dtos.request.PetRequests;
import com.jpets.controller.dtos.response.PetResponse;

public interface IPetService extends IDataService<PetRequests, PetResponse, String>
{

}
