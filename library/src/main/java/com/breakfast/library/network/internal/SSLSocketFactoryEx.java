package com.breakfast.library.network.internal;

import org.apache.http.conn.ssl.SSLSocketFactory;

import java.io.IOException;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by Steven on 16/4/12.
 */
public class SSLSocketFactoryEx extends SSLSocketFactory {

  SSLContext sslContext = SSLContext.getInstance("TLS");

  public SSLSocketFactoryEx(KeyStore truststore, char[] arry)
      throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException,
      UnrecoverableKeyException {
    super(truststore);
    KeyManagerFactory localKeyManagerFactory =
        KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
    localKeyManagerFactory.init(truststore, arry);
    KeyManager[] arrayOfKeyManager = localKeyManagerFactory.getKeyManagers();
    TrustManager tm = new X509TrustManager() {

      @Override public X509Certificate[] getAcceptedIssuers() {
        return null;
      }

      @Override public void checkServerTrusted(X509Certificate[] chain, String authType)
          throws java.security.cert.CertificateException {

      }

      @Override public void checkClientTrusted(X509Certificate[] chain, String authType)
          throws java.security.cert.CertificateException {

      }
    };

    sslContext.init(arrayOfKeyManager, new TrustManager[] { tm }, new java.security.SecureRandom());
  }

  @Override public Socket createSocket(Socket socket, String host, int port, boolean autoClose)
      throws IOException {
    return sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
  }

  @Override public Socket createSocket() throws IOException {
    return sslContext.getSocketFactory().createSocket();
  }
}