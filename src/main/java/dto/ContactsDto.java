package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Builder

public class ContactsDto
{
    private ContactDtoLombok[] contacts;
}
