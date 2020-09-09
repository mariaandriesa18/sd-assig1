package mappers;

import dto.RequestDto;
import entity.Request;
import utils.MyUtils;

public class RequestMapper {

    public static RequestDto convertToRequestDto(Request request){
       return new RequestDto(String.valueOf(request.getRequest_id()),
                request.getDocument().getType_of_doc(),
                request.getResidence().getResidence_name(),
                MyUtils.getPrettyDate(request.getDate()),
                String.valueOf(request.getMax_number()),
                request.getStatus());
    }
}
