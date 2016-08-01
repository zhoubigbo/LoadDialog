# ProgressbarLoad
##### 在找设计素材时，看到这个网站[http://thepatternlibrary.com/](http://thepatternlibrary.com/) ，打开居然是这个效果，以为网页还没加载出来，等了半天，居然还是这个，觉得这加载还不错，就自己做了一个####
##### 效果图
![](http://i.imgur.com/qjnoOLA.gif)
### 思路
#####可以把灰色圆环看成是不动的，变化的是黑色圆环，所以把先把灰色圆环绘制好，根据变化去绘制黑色圆环。我用的是`addArc`，`drawPath` 来绘制圆环，因为`Arc`的起始角度是为零的话，是从三点钟方向开始，想要从0点钟方向开始就得为-90度 #####
### 步骤
- ####声明，初始化
##### 写一个继承View的类，声明好要使用的参数
    `public class LoadView extends View`

    `private Paint paintB, paintG;//黑色和灰色画笔`

    `private RectF rectF;//矩形区域`

    `private float value = 0f, last = 0f;//初始值`

    `private boolean isState = false;//状态`

    `private Path pathG;//路径`
