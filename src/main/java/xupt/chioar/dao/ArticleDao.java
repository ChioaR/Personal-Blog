package xupt.chioar.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import xupt.chioar.domain.Article;
import xupt.chioar.domain.Category;

import javax.ws.rs.Path;
import java.util.List;

@Repository
public interface ArticleDao {
    /**
     * 通过ID获取文章
     * @param id
     * @return Article
     */
    public Article getArticleById(@Param("id")Long id);

    /**
     *根据类别ID 获取前10篇文章
     * @return Article's List
     */
    public List<Article> getFirst10Article();

    /**
     * 根据类别ID获取相关文章
     * @param categoryId
     * @return Article's List
     */
    public List<Article> getArticlesByCategoryName(Long categoryId);

    /**
     * 获取类别
     * @return Article's List
     */
    public List<Category> getCategories();

    /**
     * 写博客
     * @param article
     */
    public void writeBlog(Article article);

    /**
     * 根据文章名字获取类别ID
     * @param name
     * @return
     */
    public Long getCategoryIdByName(String name);

    /**
     * 根据ID删除文章
     * @param id
     */
    public void deleteArticleById(Long id);

    /**
     * 修改发布博客
     * @param article
     */
    public void updateArticleById(Article article);

    /**
     *根据ID获取类别
     * @param id
     * @return
     */
    public Category getCategoryById(Long id);

    /**
     * 根据输入内容搜索文章
     */
    public List<Article> getArticleBySearch(@Param("content") String content);

}
