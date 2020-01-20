package com.dh.bootio.learning.classload;
class Ye {
    public String show(Sun obj) {
        return ("Ye and Sun");
    }

    public String show(Ye obj) {
        return ("Ye and Ye");
    }

}
//爸爸类
class Fu extends Ye {
    public String show(Fu obj) {
        return ("Fu and Fu");
    }

    public String show(Ye obj) {
        return ("Fu and Ye");
    }
}
//儿子类
class Zi extends Fu {

}
//孙子类
class Sun extends Fu {

}

public class PolymorphicTest {
    public static void main(String[] args) {
        Ye y = new Ye();
        Ye y2 = new Fu(); //向上
        Fu f = new Fu();
        Zi z = new Zi();
        Sun s = new Sun();


        System.out.println("第一题 " + y.show(f)); // ye  ye
        System.out.println("第二题 " + y.show(z)); // ye ye
        System.out.println("第三题 " + y.show(s)); // ye sun
        System.out.println("第四题 " + y2.show(f));  //到这里挂了？？？    fu fu
        System.out.println("第五题 " + y2.show(z));    //fu fu
        System.out.println("第六题 " + y2.show(s));    //ye sun
        System.out.println("第七题 " + f.show(f));     //fu fu
        System.out.println("第八题 " + f.show(z));     //fu fu
        System.out.println("第九题 " + f.show(s));     //ye sun

    }
}