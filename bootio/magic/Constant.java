package magic;

import magic.bean.Pubg;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Constant {


    /**
     * 传送数据管道
     */
    public static BlockingQueue<Pubg> pubgsQueue = new LinkedBlockingQueue<>();
}
