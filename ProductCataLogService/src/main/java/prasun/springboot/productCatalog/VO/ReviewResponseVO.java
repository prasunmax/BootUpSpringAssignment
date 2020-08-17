package prasun.springboot.productCatalog.VO;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import prasun.springboot.productCatalog.entity.Review;

@Data
@NoArgsConstructor
public class ReviewResponseVO {
	@JsonIgnore
	public ReviewResponseVO(List<Review> reviewList) {
		if (null == reviews)
			reviews = new ArrayList<>();
		reviewList.forEach(review -> reviews.add(new ReviewVO(review)));
	}

	private List<ReviewVO> reviews;
}
