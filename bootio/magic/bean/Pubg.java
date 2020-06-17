package magic.bean;

import lombok.Data;

import java.util.List;

@Data
public class Pubg {


    /**
     * 是否查询完毕
     */
    private boolean commit;

    //================集體
    /**
     * 比赛排名
     */
    private String ranked;

    /**
     * 比赛编号
     */
    private String data_id;

    /**
     *地图
     */
    private String map;
    /**
     * 比赛类型
     */
    private String type;
    /**
     *总击杀
     */
    private String total_kill;
    /**
     * 总伤害
     */
    private String total_demage;

    /**
     * 成员
     */
    private List<Player> members ;

    /**
     * 击倒记录
     */
    private String knockLog ;

    /**
     * 击杀记录
     */
    private String killLog ;

    /**
     * 游戏时间
     */
    private  String time ;


    //======================个人

    @Data
     public static class Player{

         private String name;
         private String assists;
         private String help;
         private String weapon;
         private String demage;
         private String distance;
         private String longest;
         private String alive;
         private String kill;
        private String headshot;




    }


}
