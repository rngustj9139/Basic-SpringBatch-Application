package koo.BasicSpringBatchApplication.core.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "plain_text")
@DynamicUpdate // 엔티티에서 어떤 칼럼만 변경되었을 경우 그것만 변경하는 쿼리가 나가도록 한다.
public class PlainText {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLAINTEXT_ID")
    private Long id;

    @Column(nullable = false)
    private String text;

}
