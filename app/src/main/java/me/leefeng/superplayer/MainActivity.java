package me.leefeng.superplayer;

import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.superplayer.library.SuperPlayer;

public class MainActivity extends AppCompatActivity implements SuperPlayer.OnNetChangeListener {

    private SuperPlayer player;
    private boolean isLive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPlayer();
    }

    private void initPlayer(){
//        setVolumeControlStream(AudioManager.STREAM_MUSIC); //让音量键固定为媒体音量控制
        player = (SuperPlayer) findViewById(R.id.view_super_player);
        if(isLive){
            player.setLive(true);//设置该地址是直播的地址
        }
        player.setNetChangeListener(true)//设置监听手机网络的变化
                .setOnNetChangeListener(this)//实现网络变化的回调
                .setShowNavIcon(true)//设置小屏幕是否显示返回按钮
                .showCenterControl(true)//中心是否显示播放暂停
                .setCompleteToSmall(true)
                .setOrientationChangeListener(false)
                .onPrepared(new SuperPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared() {
                        /**
                         * 监听视频是否已经准备完成开始播放。（可以在这里处理视频封面的显示跟隐藏）
                         */
                    }
                }).onComplete(new Runnable() {
            @Override
            public void run() {
                /**
                 * 监听视频是否已经播放完成了。（可以在这里处理视频播放完成进行的操作）
                 */
            }
        }).onInfo(new SuperPlayer.OnInfoListener() {
            @Override
            public void onInfo(int what, int extra) {
                /**
                 * 监听视频的相关信息。
                 */

            }
        }).onError(new SuperPlayer.OnErrorListener() {
            @Override
            public void onError(int what, int extra) {
                /**
                 * 监听视频播放失败的回调
                 */

            }
        }).setTitle("嘎嘎嘎")//设置视频的titleName
        .setUrl("http://baobab.wandoujia.com/api/v1/playUrl?vid=2614&editionType=normal")
        .play(500);
//                .play("http://baobab.wandoujia.com/api/v1/playUrl?vid=2614&editionType=normal",true)
        ;//开始播放视频
        player.setScaleType(SuperPlayer.SCALETYPE_FITXY);
        player.setPlayerWH(0,player.getMeasuredHeight());//设置竖屏的时候屏幕的高度，如果不设置会切换后按照16:9的高度重置
        Glide.with(this)
                .load("http://g.hiphotos.baidu.com/image/pic/item/91529822720e0cf349fadbfb0846f21fbe09aaa5.jpg")
                .into(player.getCoverView());
        player.getCoverView().setScaleType(ImageView.ScaleType.FIT_XY);
    }


    //setOnNetChangeListener
    @Override
    public void onWifi() {

    }

    @Override
    public void onMobile() {

    }

    @Override
    public void onDisConnect() {

    }

    @Override
    public void onNoAvailable() {

    }

    /**
     * 下面的这几个Activity的生命状态很重要
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null) {
            player.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.onDestroy();
        }
    }
    @Override
    public void onBackPressed() {
        if (player != null && player.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }
}
