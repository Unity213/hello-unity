package magic.component;

import magic.bean.Pubg;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class GithubRepoPageProcessor implements PageProcessor {

    // 记录比赛场数
    private static int count = 0;
    // 设置站点
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100).setCharset("UTF-8")            ;
    // 战绩详情url
    private static String detailUrl = "https://dak.gg/profile/AixLeft/match/";
    // 杀人日志详情url
    private static String killLogUrl = "https://dak.gg/match/%s/kills";


    // 记录所有的数据 根据data-id(比赛编号)存储 key - id . value - 比赛数据
    static Map<String, Pubg> allData = new ConcurrentHashMap<>(512);
    // 查询角色 后续改成接口形式
    String user = "AixLeft";

    private static String nextUrlRegex = "(https://dak.gg/profile/AixLeft/pc-2018-07/steam/matches/\\d)";


    @Override
    public void process(Page page) {

        // 处理详细数据
         if (page.getUrl().regex(detailUrl+"\\d").match()) {
             // 构建htmlNode对象 提供查询api
             Html html = new Html(page.getJson().jsonPath("$.html").toString(), page.getUrl().toString());

             // 根据url 获取是哪场比赛的详细数据
             String data_id = page.getUrl().get().replaceAll(detailUrl,"");
             // 从内存map(alldata) 中获取pubg实体类
             Pubg pubg ;
             if(allData.containsKey(data_id)){
                 pubg = allData.get(data_id);
                 pubg.setMembers(new ArrayList<>());
             }else {
                 return;
             }

             // 查询成员信息  team-status
             List<Selectable> members = html.css("table.team-members tbody  tr").nodes();
             for (Selectable member : members) {
                 String nick = member.css(".nick a", "text").get();
                 String weapon = member.css(".weapon .name", "text").get();
                 String headshots = member.css(".headshots", "text").get();
                 String assists = member.css(".assists", "text").get();
                 String demage = member.css(".damage .value", "text").get();
                 String kills = member.css(".kills", "text").get();
                 String dbno = member.css(".dbno", "text").get();
                 String distance = member.css(".distance", "text").get();
                 String longest = member.css(".longest", "text").get();
                 String survive = member.css(".survive", "text").get();

                 Pubg.Player player = new Pubg.Player();
                 player.setName(nick);
                 player.setWeapon(weapon);
                 player.setHeadshot(headshots);
                 player.setAssists(assists);
                 player.setDemage(demage);
                 player.setKill(kills);
                 player.setHelp(dbno);
                 player.setDistance(distance);
                 player.setLongest(longest);
                 player.setAssists(survive);
                 pubg.getMembers().add(player);

             }


         } else if(page.getUrl().regex(nextUrlRegex).match()){
          // 处理下一页数据
            List<String> all = page.getHtml().links().regex(nextUrlRegex).all().stream().map(e -> e + "?hl=zh-CN").collect(Collectors.toList());
            // 添加 下一个url
//            page.addTargetRequests(all);

            // 抽取界面数据
            Selectable css = page.getHtml().css("div.matchHistory > div");

            // 遍历每一场数据
            List<Selectable> alldatas = css.nodes();
            for (Selectable onedata : alldatas) {

                // 比赛概览
                Selectable summary = onedata.css("div.summary");
                String modeName = summary.css("div.modeName", "text").toString();
                // 只爬取 custom和rank比赛
                if (!modeName.contains("Custom") && !modeName.contains("Ranked")) {
                    // 不符合条件则跳过 不被pipeline处理 todo 程序暂时未添加pipeline 后续可考虑分层
                    page.setSkip(true);
                    continue;
                }


                // 比赛编号
                String data_id = onedata.css(".item-v2", "data-id").get();
                //比赛版本
                String data_version = onedata.css(".item-v2", "data-version").get();
                // 排名 时间
                String result = summary.css("span.result", "text").get().trim() + summary.css(".max", "text").get();
                String age = summary.css("span.age", "title") + "("+summary.css("span.age", "text").get() + ")";
                //地图
                String map = onedata.css("dl.rating dd","text").get();

                // 创建比赛实体类
                Pubg pubg = new Pubg();
                pubg.setData_id(data_id);
                pubg.setType(modeName);
                pubg.setTime(age);
                pubg.setRanked(result);
                pubg.setMap(map);

                //存放到全局容器中
                allData.put(data_id,pubg);

                // 个人数据
//                List<Selectable> kinds = onedata.css("dl").nodes();
//                for (Selectable kind : kinds) {
//                    if (kind instanceof TextNode) continue;
//                    //
//                    String name = kind.css("dt", "text").get();
//                    String value = "";
//                    if ("Members".equals(name)) {
//                        name = "队友";
//                        List<String> members = kind.css("dd>ul>li>a", "text").all();
//                        members.remove(user);
//                        value = members.toString();
//                    } else {
//                        value = kind.css("dd", "text").get();
//                    }
//                    System.out.print(name + ":" + value);
//                }
//                System.out.println("=======" + count++);

                // ================ 详细数据加入爬取队列 =====================


                // 获取 html界面中传过来的 csrf_token , 每次post请求必须把 x-csrf-token加入到headers里面
                String csrf_token = page.getHtml().css("meta[name='csrf-token']", "content").toString();

                // cookie
                // 每次请求的cookie 为上次请求的响应cookie
                Map<String, List<String>> responseheaders = page.getHeaders();
                String cookie = "";
                for (String setCookies : responseheaders.get("Set-Cookie")) {
                    String s = setCookies.split(";")[0];
                    cookie += s+";";
                }
                // 这些参数暂时写死
                // todo 其实也可以通过set-cookie 来进行设置, 暂时先写死吧
                cookie += "__gads=ID=a47719a6f0e6776f:T=1591325743:S=ALNI_MYW5fNOrDkvdbk8gH82raNDd0RJxw;_ga=GA1.2.134853195.1591325743;_gid=GA1.2.864722252.1592182812;wcs_bt=2be7ca218d2cf0:1592204217;iCZiy4nCe1nmTlnp2S4NRs7aekQKwhzAeh14dr7y=ID=a47719a6f0e6776f:T=1591325743:S=ALNI_MYW5fNOrDkvdbk8gH82raNDd0RJxw;";


                // build request
                Request request = new Request();
                // set method
                request.setMethod(HttpConstant.Method.POST);
                // set url
                request.setUrl(detailUrl + data_id);

                request.addHeader("X-CSRF-TOKEN",csrf_token);
                // set params format
                request.addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8").addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36");

                // 设置headrs
                request.addHeader("Cookie",cookie);

                // 组装参数
                Map<String, Object> param = new HashMap<>();
                param.put("version", data_version);
                // 设置参数
                request.setRequestBody(HttpRequestBody.form(param, "UTF-8"));

                //加入到爬取队列
//                page.addTargetRequest(request);

                // ================ 杀人日志数据加入爬取队列 =====================


                // build request
                Request killRequest = new Request();
                // set method
                killRequest.setMethod(HttpConstant.Method.POST);
                // set url
                killRequest.setUrl(String.format(killLogUrl,data_id));

                killRequest.addHeader("X-CSRF-TOKEN",csrf_token);
                // set params format
                killRequest.addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8").addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36");

                // 设置headrs
                killRequest.addHeader("Cookie",cookie);

                // 组装参数
                Map<String, Object> param2 = new HashMap<>();
                param2.put("nick", user);
                // 设置参数
                killRequest.setRequestBody(HttpRequestBody.form(param2, "UTF-8"));

                //加入到爬取队列
                page.addTargetRequest(killRequest);
            }
        }else{
             // 杀人日志数据
             // 构建htmlNode对象 提供查询api
             Html html = new Html(page.getJson().jsonPath("$.html").toString(), page.getUrl().toString());
             List<Selectable> lis = html.css(".kill-logs-list ul li").nodes();
             System.out.println();
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