package xupt.chioar.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xupt.chioar.dao.ArticleDao;
import xupt.chioar.domain.Article;

@Controller
public class TestController {
    @Autowired
    private ArticleDao articleDao;

    @RequestMapping("/test")
    public String test(){
        return "hello";
    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    public Article get(@PathVariable("id") Long id){//问题点 注解使用问题
        System.err.println(id);
        Article article = articleDao.getArticleById(id);
        System.err.println(article.getContent());
        return article;
    }
}
