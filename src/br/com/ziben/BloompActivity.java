package br.com.ziben;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BloompActivity extends Activity {
	
	EditText textOut;
	TextView textIn;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        textOut = (EditText)findViewById(R.id.textout);
        Button buttonSend = (Button)findViewById(R.id.send);
        textIn = (TextView)findViewById(R.id.textin);
        buttonSend.setOnClickListener(buttonSendOnClickListener);
    }
    
    Button.OnClickListener buttonSendOnClickListener = new Button.OnClickListener() {

	   @Override
	   public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Socket socket = null;
			ObjectOutputStream dataOutputStream = null;
			ObjectInputStream dataInputStream = null;
	
			try {
				 socket = new Socket("192.168.1.5", 2004);
				 dataOutputStream = new ObjectOutputStream(socket.getOutputStream());
				 dataInputStream = new ObjectInputStream(socket.getInputStream());
				 dataOutputStream.writeObject(textOut.getText().toString());
				 String myText = dataInputStream.readObject().toString();
				 //textIn.setText(dataInputStream.readObject());
				 textIn.setText(myText);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	
				if (dataOutputStream != null) {
					try {
						dataOutputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	
				if (dataInputStream != null) {
					try {
						dataInputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
   		}
    };
}
