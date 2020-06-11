package magic.component;

import magic.bean.Pubg;
import org.jsoup.nodes.TextNode;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GithubRepoPageProcessor implements PageProcessor {

    // 记录比赛场数
    private static int count = 0;
    // 设置站点
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
    // 战绩详情url
    private static String detailUrl = "https://dak.gg/profile/AixLeft/match/";
    // 杀人日志详情url

    // 记录所有的数据 根据data-id(比赛编号)存储 key - id . value - 比赛数据
    static Map<String, Pubg> allData = new HashMap<>(256);
    // 查询角色 后续改成接口形式
    String user = "AixLeft";



    @Override
    public void process(Page page) {

        if(page.getUrl().regex(detailUrl+"\\d").match()){

            // 获取个人详细数据
//            page.getJson().jsonPath("html").css();



        }else{
            {
                List<String> all = page.getHtml().links().regex("(https://dak.gg/profile/AixLeft/pc-2018-07/steam/matches/\\d)").all().stream().map(e->e+"?hl=zh-CN").collect(Collectors.toList());
                // 添加 下一个url
                page.addTargetRequests(all);

                // 抽取界面数据

                Selectable css = page.getHtml().css("div.matchHistory > div");

                List<Selectable> alldatas = css.nodes();
                for (Selectable onedata : alldatas) {
                    String data_id = onedata.css(".item-v2", "data-id").get();
                    String data_version = onedata.css(".item-v2", "data-version").get();

                    // 比赛概览
                    Selectable summary = onedata.css("div.summary");
                    String modeName = summary.css("div.modeName","text").toString();
                    // 只爬取 custom和rank比赛
                    if(!modeName.contains("Custom") && !modeName.contains("Ranked")){
                        page.setSkip(true);
                    }

                    String result = summary.css("span.result","text").get().trim() +summary.css(".max","text").get();
                    String age =summary.css("span.age","text").get() + "// "+ summary.css("span.age","title");
                    System.out.println("总结 : "+modeName + " / " + result + " / " + age);

                    // 个人数据
                    List<Selectable> kinds = onedata.css("dl").nodes();
                    for (Selectable kind : kinds) {
                        if(kind instanceof TextNode) continue;
                        //
                        String name = kind.css("dt", "text").get();
                        String value = "";
                        if("Members".equals(name)){
                            name = "队友";
                            List<String> members = kind.css("dd>ul>li>a", "text").all();
                            members.remove(user);
                            value = members.toString();
                        }else{
                            value  = kind.css("dd", "text").get();
                        }
                        System.out.print(name+":"+value);
                    }
                    System.out.println("======="+count++);

                    // 详细数据
                    Request request = new Request();
                    // 设置url
                    request.setUrl(detailUrl + data_id);
                    // 设置方法
                    request.setMethod(HttpConstant.Method.POST);
                    // 组装数据
                    Map<String,Object> param = new HashMap<>();
                    param.put("version",data_version);
                    request.setRequestBody(HttpRequestBody.form(param, "UTF-8"));

                    //加入到爬取队列
                    page.addTargetRequest(request);

                }
            }
        }


    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        System.out.println("开始");
        Spider.create(new GithubRepoPageProcessor())
                .setDownloader(new HttpClientDownloader())
                .addUrl("https://dak.gg/profile/AixLeft/pc-2018-07/steam/matches/1?hl=zh-CN")
                .run();
    }
}