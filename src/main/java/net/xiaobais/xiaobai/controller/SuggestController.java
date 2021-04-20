package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.model.Blog;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.model.SuggestWithBLOBs;
import net.xiaobais.xiaobai.service.BlogService;
import net.xiaobais.xiaobai.service.PublicNodeService;
import net.xiaobais.xiaobai.service.SuggestService;
import net.xiaobais.xiaobai.service.UserService;
import net.xiaobais.xiaobai.vo.SimpleNodeVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/3/11 10:45
 * @Version 1.0
 */
@Api(tags = "suggestController")
@Controller
public class SuggestController {

    @Resource
    private PublicNodeService nodeService;
    @Resource
    private BlogService blogService;
    @Resource
    private SuggestService suggestService;
    @Resource
    private UserService userService;

    private static final int SIZE = 1000;

    private static final String ONE = "1";
    private static final String TWO = "2";
    private static final String THREE = "3";
    private static final String FOUR = "4";



    @ApiOperation("跳转添加建议节点页面")
    @GetMapping("/person/public/toAddSuggest/{nodeId}")
    public String toAddSuggest(@PathVariable Integer nodeId, Model model){

        Node node = nodeService.findNodeById(nodeId);
        Blog blog = blogService.findBlogById(node.getBlogId());

        model.addAttribute("nodeId", nodeId);
        model.addAttribute("html", blog.getBlogContent());
        if (blog.getBlogContent() != null && blog.getBlogContent().length() > SIZE) {
            model.addAttribute("flag", false);
        }
        else {
            model.addAttribute("flag", true);
        }
        model.addAttribute("mostCollect", nodeToSimpleNodeVo(nodeService.findNodeByTopCollect(5)));
        model.addAttribute("mostStar", nodeToSimpleNodeVo(nodeService.findNodeByTopStar(5)));

        return "addSuggest";
    }

    @ApiOperation("添加建议节点")
    @PostMapping("/person/public/addSuggest")
    @ResponseBody
    public Integer addSuggest(SuggestWithBLOBs suggestWb) throws Exception {
        return suggestService.addSuggest(suggestWb);
    }

    @ApiOperation("通过suggestId获取建议节点")
    @GetMapping("/person/public/getSuggest/{suggestId}")
    public String getSuggest(@PathVariable Integer suggestId, Model model){
        int count = 0;
        SuggestWithBLOBs suggest = suggestService.getSuggestBySuggestId(suggestId);
        if (suggest != null){
            String choice = suggest.getChoice();
            if (choice.contains(ONE)){
                count ++;
                model.addAttribute("one", true);
                model.addAttribute("question", suggest.getQuestion());
                model.addAttribute("suggest", suggest.getSuggest());
            }
            if (choice.contains(TWO)){
                count ++;
                model.addAttribute("two", true);
                model.addAttribute("extend", suggest.getExtend());
                model.addAttribute("extend_suggest", suggest.getExtendSuggest());
            }
            if (choice.contains(THREE)){
                count ++;
                model.addAttribute("three", true);
                String[] previousQuestion = suggest.getPreviousQuestion().split("#");
                List<Node> nodes = new LinkedList<>();
                Arrays.stream(previousQuestion).forEach(
                        id -> nodes.add(nodeService.findNodeById(Integer.parseInt(id)))
                );
                model.addAttribute("previous_question", nodeToSimpleNodeVo(nodes));
                model.addAttribute("previous_suggest", suggest.getPreviousSuggest());
            }
            if (choice.contains(FOUR)){
                count ++;
                model.addAttribute("four", true);
                String[] previousQuestion = suggest.getNextQuestion().split("#");
                List<Node> nodes = new LinkedList<>();
                Arrays.stream(previousQuestion).forEach(
                        id -> nodes.add(nodeService.findNodeById(Integer.parseInt(id)))
                );
                model.addAttribute("next_question", nodeToSimpleNodeVo(nodes));
                model.addAttribute("next_suggest", suggest.getNextSuggest());
            }
            model.addAttribute("username", userService.getUserById(suggest.getUserId()).getUsername());
            if (count <= 2){
                model.addAttribute("add", true);
            }
            return "publicSuggest";
        }
        return "/error/404";
    }

    @ApiOperation("获取建议节点信息")
    @GetMapping("/person/public/getSuggest")
    @ResponseBody
    public SuggestWithBLOBs getSuggestBySuggestId(@RequestParam Integer suggestId){
        return suggestService.getSuggestBySuggestId(suggestId);
    }

    private List<SimpleNodeVo> nodeToSimpleNodeVo(List<Node> nodes){
        List<SimpleNodeVo> simpleNodeVos = new ArrayList<>(nodes.size());
        nodes.forEach(node -> {
                    SimpleNodeVo vo = new SimpleNodeVo();
                    vo.setUrl("/public/node/" + node.getNodeId());
                    vo.setTitle(node.getNodeName());
                    simpleNodeVos.add(vo);
                }
        );
        return simpleNodeVos;
    }
}
