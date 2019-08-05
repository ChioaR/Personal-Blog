package xupt.chioar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tautua.markdownpapers.Markdown;
import org.tautua.markdownpapers.parser.ParseException;
import xupt.chioar.domain.Article;
import xupt.chioar.domain.Category;
import xupt.chioar.domain.User;
import xupt.chioar.service.ArticleService;
import xupt.chioar.service.Userservice;

import javax.servlet.http.HttpServletRequest;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

@Controller
@CrossOrigin
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private Userservice userservice;

    /**
     * 首页（展示前10篇所有专栏的文章）
     */
    @RequestMapping("/")
    public String index(Model model){
        List<Article> articles = articleService.getFirst10Article();
//        for (Article article : articles) {
//            System.err.println(article.getCategory());//遍历查看相关类别 可以不用
//        }
        model.addAttribute("articles",articles);
        return "views/index";
    }

    /**
     * 各个专栏展示页
     */
    @RequestMapping("/column/{displayName}/{category}")
    public String column(@PathVariable("displayName")String displayName,@PathVariable("category")String category,Model model){
        model.addAttribute("articles",articleService.getArticlesByCategoryName(category));
        model.addAttribute("displayName",displayName);
        return "views/columnPage";
    }

    /**
     *详情页展示（根据ID查询相关文章 然后跳转）
     */
    @RequestMapping("/detail/{id}/{category}")
    public String detail(@PathVariable("id")Long id,Model model){
        Article article = articleService.getArticleById(id);
        //System.out.println(article.getContent());
        Markdown markdown = new Markdown();
        try {
            StringWriter out = new StringWriter();
            markdown.transform(new StringReader(article.getContent()), out);
            out.flush();
            article.setContent(out.toString());
            //System.out.println("------------------");
            //System.out.println(article.getContent());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        model.addAttribute("article", article);
        return "views/detail";
    }

    /**
     * 管理员首页面
     */
    @RequestMapping("/admin")
    public String admin(Model model){
        model.addAttribute("articles",articleService.getFirst10Article());
        return "admin/index";
    }

    /**
     * 登录页面
     */
    @RequestMapping("/login")
    public String login(){
        return "admin/login";
    }

    /**
     *执行登录
     */
    @RequestMapping(value ="/dologin",method = RequestMethod.POST)
    public String dologin(HttpServletRequest request, User user, Model model){
        if (userservice.login(user.getUsername(),user.getPassword())){
            request.getSession().setAttribute("user",user);
            model.addAttribute("user",user);
            return "redirect:/admin";
        }else {
            model.addAttribute("error","用户名或密码存在问题!!!");
            return "admin/login";
        }
    }

    /**
     * 检查登录
     */
    @RequestMapping(value ="/dologin" ,method = RequestMethod.GET)
    public String dologin(HttpServletRequest request, Model model){
        if (request.getSession().getAttribute("user") == null){
            return "admin/login";
        }
        return "redirect:/admin";
    }

    /**
     * 准备撰写博客页面相关元素
     */
    @RequestMapping("/write")
    public String write(Model model){
        List<Category> categories = articleService.getCategories();
        categories.remove(0);
        model.addAttribute("categories",categories);
        return "admin/write";
    }

    /**
     * 撰写博客（包含更新）
     */
    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String write(Article article){
        if (article.getId() == 0l){
            articleService.writeBlog(article);
        }else {
            articleService.updateBlog(article);
        }
        return "redirect:/admin";
    }

    /**
     * 删除
     */
    @RequestMapping("/admin/delete/{id}")
    public String delete(@PathVariable("id")Long id){
        articleService.deleteArticleById(id);
        return "redirect:/admin";
    }

    /**
     * 更新
     */
    @RequestMapping("/admin/update/{id}")
    public String update(@PathVariable("id")Long id, Model model){
        Article article = articleService.getArticleById(id);
        model.addAttribute("article",article);
        return "admin/write";
    }

    /**
     * 搜索
     */
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    @ResponseBody
    public List<Article> search(String content,Model model){
        //model.addAttribute(" articles",articleService.getArticleBySearch(content));
        List<Article> articles = articleService.getArticleBySearch(content);
        return articles;
    }

}
