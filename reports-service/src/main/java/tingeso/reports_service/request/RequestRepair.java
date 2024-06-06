package tingeso.reports_service.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RequestRepair {

    private Long id;
    private String name;
    private int ammount;
    private Long engineId;

}
