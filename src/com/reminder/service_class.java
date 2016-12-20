package com.reminder;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class service_class extends Service{
	private Intent intent;
	private Post post ;
	private NotificationManager mNotifyManager;
	private NotificationCompat.Builder mBuilder;
	private String data = "";
	private boolean threadWork = true;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
 
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		post = new Post();
		
		mNotifyManager =
		        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mBuilder = new NotificationCompat.Builder(this);
		mBuilder.setContentTitle("15分鐘內極有可能下雨!")//設置通知欄標題  
	    .setContentText("記得帶傘呦 >.0")  
	    .setTicker("要下雨啦啦啦!!!") //通知首次出現在通知欄，帶上升動畫效果的  
	    .setWhen(System.currentTimeMillis())//通知產生的時間，會在通知信息裡顯示，一般是系統獲取到的時間  
	    .setPriority(Notification.PRIORITY_DEFAULT) //設置該通知優先級      
	    //.setOngoing(false)//ture，設置他為一個正在進行的通知。他們通常是用來表示一個後台任務,用戶積極參與(如播放音樂)或以某種方式正在等待,因此佔用設備(如一個文件下載,同步操作,主動網絡連接)  
	    //.setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加聲音、閃燈和振動效果的最簡單、最一致的方式是使用當前的用戶默認設置，使用defaults屬性，可以組合   
	    .setSmallIcon(R.drawable.ic_launcher);//設置通知小ICON
		
		// Start a lengthy operation in a background thread
		new Thread(
		    new Runnable() {
		        @Override
		        public void run() {
		        	//post.Set("http://140.116.245.149/WWW/test.php");
		        	data = post.SendHttpPost("HI");
		        	
		        	//int i = 0;
		        	while (threadWork) {
		        		//i ++;
		        		if( data.contains("1") ){
		        			mNotifyManager.notify(50, mBuilder.build());
		        		}
		        		try {
                            // Sleep for 30 seconds
                            Thread.sleep(30*1000);
                        } catch (InterruptedException e) {
                            Log.d("TAG", "sleep failure");
                        }
		        	}
		        	
		        }
		    }
		// Starts the thread by calling the run() method in its Runnable
		).start();
		Toast.makeText(this, "Service start", Toast.LENGTH_SHORT).show();
	}
	
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		threadWork = false;
		Toast.makeText(this, "Service stop", Toast.LENGTH_SHORT).show();  
	}
}