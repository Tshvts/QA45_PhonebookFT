package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Builder

public class ContactDtoLombok
{
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private String description;
}
