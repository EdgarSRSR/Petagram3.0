package com.solrom.edgar.petagram30.Activity;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.solrom.edgar.petagram30.R;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by edgarsr on 01/10/17.
 */

public class FormularioActivity extends AppCompatActivity {

    String correo;
    String password;
    EditText etNombre;
    EditText etMail;
    EditText etMensaje;
    Button btnEnviarComentario;
    Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etNombre = (EditText) findViewById(R.id.etNombre);
        etMail = (EditText) findViewById(R.id.etEmail);
        etMensaje = (EditText) findViewById(R.id.etMensaje);
        btnEnviarComentario = (Button) findViewById(R.id.btnEnviarComentario);

        correo = "tu-correo-electrónico";
        password = "tucontraseña";

        btnEnviarComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aviso de correo que se está procesando
                Snackbar.make(v, etNombre.getText().toString()+" "+getResources().getString(R.string.correo_enproceso), Snackbar.LENGTH_SHORT).show();

                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                //Propiedades ajustadas para gmail
                Properties properties = new Properties();
                properties.put("mail.smtp.host", "smtp.googlemail.com");
                properties.put("mail.smtp.socketFactory.port", "465");
                properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.port", "465");
                try {

                    session = Session.getDefaultInstance(properties, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(correo, password);
                        }
                    });

                    if (session != null) {
                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(correo)); //Correo del emisor
                        message.setSubject("Tarea 4, correo de pruebas"); //Asunto del msj
                        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(etMail.getText().toString()));//Destinatarios
                        message.setContent(etMensaje.getText().toString(), "text/html; charset=utf-8");// Tipo de contenido que vamos a enviar

                        //Envia el correo
                        Transport.send(message);

                        // el correo se ha enviado
                        Snackbar.make(v, getResources().getString(R.string.correo_enviado)+" "+etMail.getText().toString(), Snackbar.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    // el correo no se pudo enviar
                    Snackbar.make(v, getResources().getString(R.string.error_correo), Snackbar.LENGTH_SHORT).show();
                }
            }

        });
    }
}
