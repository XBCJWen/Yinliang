package com.example.a17374.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private  SeekBar sb_yl;
    private  SeekBar SB_mt;
    private  SeekBar SB_mz;
    private  SeekBar SB_th;
    private  TextView tv_ls;
    private   TextView tv_mt;
    private   TextView tv_nz;
    private   TextView tv_th;
    private  AudioManager audioManager;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initaudio();
        initListener();

    }

    private void init() {
         sb_yl=findViewById(R.id.SB_ls);
         SB_mt=findViewById(R.id.SB_mt);
         SB_mz=findViewById(R.id.SB_mz);
         SB_th=findViewById(R.id.SB_th);
         tv_ls=findViewById(R.id.tv_ls);
         tv_mt=findViewById(R.id.tv_mt);
         tv_nz=findViewById(R.id.tv_nz);
         tv_th=findViewById(R.id.tv_th);
         audioManager= (AudioManager) getSystemService(Context.AUDIO_SERVICE);
    }

    private void initaudio() {
        int ls_max = audioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
        int ls_dq = audioManager.getStreamVolume(AudioManager.STREAM_RING);
        sb_yl.setMax(ls_max+1);
        sb_yl.setProgress(ls_dq);
        tv_ls.append(String.valueOf(ls_dq));


        int mt_max = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int mt_dq = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        SB_mt.setMax(mt_max);
        SB_mt.setProgress(mt_dq);
        tv_mt.append(String.valueOf(mt_dq));

        int th_max = audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL);
        int th_dq = audioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL);
        SB_th.setMax(th_max+1);
        SB_th.setProgress(th_dq);
        tv_th.append(String.valueOf(th_dq));

        int nz_max = audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM);
        int nz_dq = audioManager.getStreamVolume(AudioManager.STREAM_ALARM);
        SB_mz.setMax(nz_max);
        SB_mz.setProgress(nz_dq);
        tv_nz.append(String.valueOf(nz_dq));

    }
    private void initListener() {
        SB_mz.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM);
                tv_nz.setText("闹钟-" + String.valueOf(SB_mz.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                audioManager.setStreamVolume(AudioManager.STREAM_ALARM, Integer.valueOf(SB_mz.getProgress()), 0);
                tv_nz.setText("闹钟-" + String.valueOf(SB_mz.getProgress()));
            }
        });

        SB_th.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL);
                tv_th.setText("通话-" + String.valueOf(SB_th.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, Integer.valueOf(SB_th.getProgress()), 0);
                tv_th.setText("通话-" + String.valueOf(SB_th.getProgress()));
            }
        });

        SB_mt.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                tv_mt.setText("媒体-" + String.valueOf(SB_mt.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, Integer.valueOf(SB_mt.getProgress()), 0);
                tv_mt.setText("媒体-" + String.valueOf(SB_mt.getProgress()));

            }
        });

        sb_yl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.getStreamVolume(AudioManager.STREAM_RING);
                tv_ls.setText("铃声-" + String.valueOf(sb_yl.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(audioManager.getRingerMode()==audioManager.RINGER_MODE_SILENT  ){

                    tv_ls.setText("铃声-已静音");
                }else {
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    audioManager.setStreamVolume(AudioManager.STREAM_RING, Integer.valueOf(sb_yl.getProgress()), 0);
                    tv_ls.setText("铃声-" + String.valueOf(sb_yl.getProgress()));

                }
            }
        });
    }
}
