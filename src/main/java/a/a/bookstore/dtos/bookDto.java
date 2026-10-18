package a.a.bookstore.dtos;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class bookDto {
    
    @NotEmpty(message = "title can't be empty")
    private String title;
    private String author;
    private String publicationDate;
    private String description;
    private MultipartFile coverImage;
    private Double stock;
    private Integer price;
    private Integer categoryId;


}
