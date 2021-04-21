package net.xiaobais.xiaobai.utils;

/**
 * @Author xiaobai
 * @Date 2021/4/20 16:49
 * @Version 1.0
 */
public class HtmlUtils {

    public static final String A_PREFIX_URL = "<a class='ui header' href='/public/node/";
    public static final String A_NEXT = "'>";
    public static final String A_NEXT_URL = "</a>";
    public static final String A_PREFIX_ITERATOR = "<a class='ui header' href='/person/public/iterator/";
    public static final String A_SUGGEST_URL = "<a class='ui header' href='/person/public/getSuggest/";

    public static final String BLOG_BUTTON = "<button class='ui inverted teal button' onclick='toShowBlog(";
    public static final String MAP_BUTTON = "<button class='ui inverted green button' onclick='toShowMap(";
    public static final String BLOG_BUTTON_NEXT = ")'>博客</button>";
    public static final String MAP_BUTTON_NEXT = ")'>导图</button>";

    public static final String URL = "/";
    public static final String B = ",'";
    public static final String C = "'";
    public static final String AP_PREFIX_URL = "<a class='ui header' href='/private/node/";
    public static final String AP_IS_UPDATE = "?isUpdate=0";
    public static final String P_DELETE_BUTTON = "<button class='ui inverted red button' onclick=\"toDelete(";
    public static final String P_CHANGE_BUTTON = "<button class='ui inverted red button' onclick='toChangeBlog(";
    public static final String P_DELETE_BUTTON_NEXT = "')\">删除</button>";
    public static final String P_CHANGE_BUTTON_NEXT = ")'>修改</button>";
    public static final String LINE = "<br>";

    public static final String SUGGEST_SHOW_BUTTON = "<button class='ui inverted pink button' onclick='toShowSug(";
    public static final String SUGGEST_SHOW_BUTTON_NEXT = ")'>建议</button>";

    public static String publicHtmlToString(Integer nodeId, String nodeName){
        return  A_PREFIX_URL + nodeId + A_NEXT + nodeName + A_NEXT_URL + LINE
                + BLOG_BUTTON + nodeId + BLOG_BUTTON_NEXT
                + MAP_BUTTON + nodeId + MAP_BUTTON_NEXT;
    }

    public static String publicSimpleHtmlToString(Integer nodeId, String nodeName){
        return  A_PREFIX_URL + nodeId + A_NEXT + nodeName + A_NEXT_URL + LINE;
    }

    public static String publicIteratorToString(Integer nodeId, String nodeName){
        return BLOG_BUTTON + nodeId + BLOG_BUTTON_NEXT
                + MAP_BUTTON + nodeId + MAP_BUTTON_NEXT
                + A_PREFIX_ITERATOR + nodeId + A_NEXT + nodeName + A_NEXT_URL;
    }

    public static String publicSuggestToString(Integer suggestId, String username){
        return A_SUGGEST_URL + suggestId + A_NEXT + username + "的建议" + A_NEXT_URL + LINE
                + SUGGEST_SHOW_BUTTON + suggestId + SUGGEST_SHOW_BUTTON_NEXT;
    }

    public static String privateHtmlToString(Integer nodeId, Integer userId, String nodeName, String node, String flag){

        return  AP_PREFIX_URL + nodeId + URL + userId + AP_IS_UPDATE + A_NEXT + nodeName + A_NEXT_URL + LINE
                + BLOG_BUTTON + nodeId + BLOG_BUTTON_NEXT
                + MAP_BUTTON + nodeId + MAP_BUTTON_NEXT
                + P_CHANGE_BUTTON + nodeId + P_CHANGE_BUTTON_NEXT
                + P_DELETE_BUTTON + nodeId + B + node +  C + B + flag + P_DELETE_BUTTON_NEXT;
    }

    public static String privateSimpleHtmlToString(Integer nodeId, Integer userId, String nodeName){
        return AP_PREFIX_URL + nodeId + URL + userId + AP_IS_UPDATE + A_NEXT + nodeName + A_NEXT_URL;
    }
}
