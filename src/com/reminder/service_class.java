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
		mBuilder.setContentTitle("15�����������i��U�B!")//�]�m�q������D  
	    .setContentText("�O�o�a���� >.0")  
	    .setTicker("�n�U�B�հհ�!!!") //�q�������X�{�b�q����A�a�W�ɰʵe�ĪG��  
	    .setWhen(System.currentTimeMillis())//�q�����ͪ��ɶ��A�|�b�q���H������ܡA�@��O�t������쪺�ɶ�  
	    .setPriority(Notification.PRIORITY_DEFAULT) //�]�m�ӳq���u����      
	    //.setOngoing(false)//ture�A�]�m�L���@�ӥ��b�i�檺�q���C�L�̳q�`�O�ΨӪ�ܤ@�ӫ�x����,�Τ�n���ѻP(�p���񭵼�)�ΥH�Y�ؤ覡���b����,�]�����γ]��(�p�@�Ӥ��U��,�P�B�ާ@,�D�ʺ����s��)  
	    //.setDefaults(Notification.DEFAULT_VIBRATE)//�V�q���K�[�n���B�{�O�M���ʮĪG����²��B�̤@�P���覡�O�ϥη�e���Τ��q�{�]�m�A�ϥ�defaults�ݩʡA�i�H�զX   
	    .setSmallIcon(R.drawable.ic_launcher);//�]�m�q���pICON
		
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