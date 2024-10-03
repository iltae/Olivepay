package kr.co.olivepay.payment.openapi.dto.req.abstracts;

import com.fasterxml.jackson.annotation.JsonProperty;

import kr.co.olivepay.payment.openapi.dto.req.FintechHeaderReq;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class FintechRequest {

    @JsonProperty("Header")
    private FintechHeaderReq Header;

}
