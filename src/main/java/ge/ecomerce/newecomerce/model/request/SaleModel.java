package ge.ecomerce.newecomerce.model.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
public class SaleModel {
    @Future(message = "sale start date and time must be in the future min 1 minute")
    private LocalDateTime startDate;

    @Future(message = "sale end  date and time must be in the future")
    private LocalDateTime endDate;

    @Min(value = 1, message = "sale in percent min value must be 1")
    @Max(value = 100, message = "sale in percent max value must be 100")
    private Integer saleInPercent;

    @Min(value = 1, message = "sale in percent min value must be 1")
    private BigDecimal saleInNumber;

    private Set<Long> products;

}
