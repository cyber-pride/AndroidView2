package com.bluapp.androidview2.DeviceFeatures;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.bluapp.androidview2.R;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.AccessController;
import java.security.Provider;
import java.security.Security;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.DataSource;



public class DeviceFeaturesActivity4 extends AppCompatActivity {
    private EditText usernameEdt;
    private EditText passwordEdt;
    private EditText useremailEdt;
    private EditText toEdt;
    private EditText subjectEdt;
    private EditText messageEdt;
    private Button sendEmailBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_features4);
        usernameEdt = (EditText) findViewById(R.id.usernameEdt);
        passwordEdt = (EditText) findViewById(R.id.passwordEdt);
        useremailEdt = (EditText) findViewById(R.id.useremailEdt);
        toEdt = (EditText) findViewById(R.id.toEdt);
        subjectEdt = (EditText) findViewById(R.id.subjectEdt);
        messageEdt = (EditText) findViewById(R.id.messageEdt);
        sendEmailBtn = (Button) findViewById(R.id.sendEmailBtn);

        sendEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail(usernameEdt.getText().toString(), passwordEdt.getText().toString(),  subjectEdt.getText().toString(), messageEdt.getText().toString(), toEdt.getText().toString(), useremailEdt.getText().toString());
            }
        });
    }

    private void sendEmail(final String email, final String password, final String subject, final String content, final String recipient, final String senderemail) {
        final ProgressDialog dialog = new ProgressDialog(DeviceFeaturesActivity4.this);
        dialog.setTitle("Sending Email...");
        dialog.setMessage("Please wait");
        dialog.show();
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GMailSender sender = new GMailSender(email, password);
                    sender.sendMail(subject, content, senderemail, recipient);
                    dialog.dismiss();
                } catch (Exception e) {
                    Log.e("AndroidView2", "Error: " + e.getMessage());
                }
            }
        });
        sender.start();
    }

    public class GMailSender extends javax.mail.Authenticator {
        private String mailhost = "smtp.gmail.com";
        private String user;
        private String password;
        private Session session;

        {
            Security.addProvider(new JSSEProvider());
        }

        public GMailSender(String user, String password) {
            this.user = user;
            this.password = password;

            Properties props = new Properties();
            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.host", mailhost);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.quitwait", "false");

            session = Session.getDefaultInstance(props, this);
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, password);
        }

        public synchronized void sendMail(String subject, String body, String sender, String recipients) throws Exception {
            try{
                MimeMessage message = new MimeMessage(session);
                DataHandler handler = new DataHandler(new ByteArrayDataSource(body.getBytes(), "text/plain"));
                message.setSender(new InternetAddress(sender));
                message.setSubject(subject);
                message.setDataHandler(handler);
                if (recipients.indexOf(',') > 0)
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
                else
                    message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
                Transport.send(message);
            }catch(Exception e){
                Log.d("AndroidView2", "Error in sending: " + e.toString());
            }
        }

        public class ByteArrayDataSource implements DataSource {
            private byte[] data;
            private String type;
            public ByteArrayDataSource(byte[] data, String type) {
                super();
                this.data = data;
                this.type = type;
            }
            public ByteArrayDataSource(byte[] data) {
                super();
                this.data = data;
            }
            public void setType(String type) {
                this.type = type;
            }
            public String getContentType() {
                if (type == null)
                    return "application/octet-stream";
                else
                    return type;
            }
            public InputStream getInputStream() throws IOException {
                return new ByteArrayInputStream(data);
            }
            public String getName() {
                return "ByteArrayDataSource";
            }
            public OutputStream getOutputStream() throws IOException {
                throw new IOException("Not Supported");
            }
        }
    }

    public final class JSSEProvider extends Provider {
        public JSSEProvider() {
            super("HarmonyJSSE", 1.0, "Harmony JSSE Provider");
            AccessController.doPrivileged(new java.security.PrivilegedAction<Void>() {
                public Void run() {
                    put("SSLContext.TLS", "org.apache.harmony.xnet.provider.jsse.SSLContextImpl");
                    put("Alg.Alias.SSLContext.TLSv1", "TLS");
                    put("KeyManagerFactory.X509", "org.apache.harmony.xnet.provider.jsse.KeyManagerFactoryImpl");
                    put("TrustManagerFactory.X509", "org.apache.harmony.xnet.provider.jsse.TrustManagerFactoryImpl");
                    return null;
                }
            });
        }
    }



}
