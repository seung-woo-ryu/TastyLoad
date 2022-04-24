package blockchainstudy.example.tastyload.review;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
public class ReviewController {
    ReviewService reviewService;

    // @PostMapping(value="/create")
    // public ResponseEntity createReview(@RequestBody Review){
    // return ResponseEntity.ok();
    // }
}
