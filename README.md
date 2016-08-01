# ProgressbarLoad #
##### 在找设计素材时，看到这个网站[http://thepatternlibrary.com/](http://thepatternlibrary.com/) ，打开居然是这个效果，以为网页还没加载出来，等了半天，居然还是这个，觉得这加载还不错，就自己做了一个####
##### 效果图 #####
![](http://i.imgur.com/qjnoOLA.gif)
### 思路 ###
#####可以把灰色圆环看成是不动的，变化的是黑色圆环，所以把先把灰色圆环绘制好，根据变化去绘制黑色圆环。我用的是`addArc`，`drawPath` 来绘制圆环，因为`Arc`的起始角度是为零的话，是从三点钟方向开始，想要从0点钟方向开始就得为-90度 #####
### 步骤 ###



- ####声明，初始化 ####
	##### 写一个继承View的类，声明好要使用的参数 #####

    `public class LoadView extends View`

    `private Paint paintB, paintG;//黑色和灰色画笔`

    `private RectF rectF;//矩形区域`

    `private float value = 0f, last = 0f;//初始值`

    `private boolean isState = false;//状态`

    `private Path pathG;//路径`


    //初始化方法
    private void initial() {
    paintB = new Paint();
    paintB.setStyle(Paint.Style.STROKE);
    paintB.setAntiAlias(true);
    paintB.setStrokeWidth(6);
    paintB.setColor(Color.BLACK);
    
    paintG = new Paint();
    paintG.setStyle(Paint.Style.STROKE);
    paintG.setAntiAlias(true);
    paintG.setStrokeWidth(6);
    paintG.setColor(Color.parseColor("#EEEEEE"));
    
    rectF = new RectF(10, 10, 200, 200);
    
    pathG = new Path();
    pathG.addArc(rectF, -90, 360);
    
    doAnimator();
    }


- #### 设置属性动画 ####


    private void doAnimator() {
    ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 360f);
    valueAnimator.addUpdateListener(new addUpdate());
    valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
    valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
    valueAnimator.setDuration(3000);
    valueAnimator.start();
    }

    class addUpdate implements ValueAnimator.AnimatorUpdateListener {
    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
    value = (float) animation.getAnimatedValue();
    if (value - last > 0.0) {
    isState = true;
    } else {
    isState = false;
    }
    last = value;
    invalidate();
    }
    }



我写得很简单，只是把基本做好了，没有去计算大小，位置这些，写得是固定值，可以自己去改。推荐一篇大神的博客，里面有讲动画和自定义控件，讲的通俗易懂，写得很好[http://blog.csdn.net/harvic880925/article/details/50995268](http://blog.csdn.net/harvic880925/article/details/50995268)
