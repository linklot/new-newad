package au.com.newad.web.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;

import lombok.Getter;

@Entity
@Getter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "summary")
    private String summary;

    @Column(name = "pic_url")
    private String picUrl;

    @Column(name = "top_in_category")
    private Boolean topInCategory;

    @Column(name = "top_in_category_order")
    private Integer topInCategoryOrder;

    @Column(name = "top_in_site")
    private Boolean topInSite;

    @Column(name = "top_in_site_order")
    private Integer topInSiteOrder;

    @Column(name = "time_published")
    private LocalDateTime timePublished;

    @Column(name = "click_number")
    private String clickNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_category_id")
    private Category category;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "article")
    private ArticleContent content;
}
