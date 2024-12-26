package com.jpets.infrastructure.abstract_service;

import com.jpets.app.dtos.request.PetRequests;
import com.jpets.app.dtos.response.PetResponse;

public interface IPetService extends IDataService<PetRequests, PetResponse, String>
{

}
