package it.apulia.Es01;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class BookDTO {

    @Size(min = 3, max = 500, message = "Titolo troppo corto!")
    String titolo;
    String autore;
    @Size(min = 4, max = 4, message = "Anno troppo vecchio!")
    String annoPB;
    String link;

}
