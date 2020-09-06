package prasun.springboot.productCatalog.VO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import prasun.springboot.productCatalog.entity.Review;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewVO {
	private int stars;
	private String author;
	private String body;
	private ProductVO product;
	private int id;
	
	@JsonIgnore
	public Review getFromVO() {
		Review review = new Review();
		review.set_id(this.getId());
		review.setAuthor(this.getAuthor());
		review.setBody(this.getBody());
		review.setStars(this.getStars());
		return review;
	}

	public ReviewVO(Review review) {
		this.stars = review.getStars();
		this.author = review.getAuthor();
		this.body = review.getBody();
		this.product = new ProductVO(review.getProduct());
		this.id = review.get_id();
	}
	
}
