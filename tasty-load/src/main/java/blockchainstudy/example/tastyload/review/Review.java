package blockchainstudy.example.tastyload.review;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "review_id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long review_id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "created_time")
    private Date created_time;

    @Column(name = "likes")
    private Integer likes;

    @Column(name = "place_id")
    private Integer place_id;

    @Column(name = "user_id")
    private String user_id;

    @Column(name = "comment_id")
    private String comment_id;
}
