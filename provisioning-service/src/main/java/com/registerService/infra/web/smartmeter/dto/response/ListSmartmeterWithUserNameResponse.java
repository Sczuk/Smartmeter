package com.registerService.infra.web.smartmeter.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListSmartmeterWithUserNameResponse {

    private List<ListSmartmetersResponse> smartmeters;

    private String name;

}
