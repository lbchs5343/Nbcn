/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  java.io.IOException
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.Runnable
 *  java.lang.Runtime
 *  java.lang.String
 *  java.lang.Thread
 *  java.net.DatagramPacket
 *  java.net.DatagramSocket
 *  java.net.InetAddress
 *  java.net.SocketException
 *  java.util.concurrent.ExecutorService
 *  java.util.concurrent.Executors
 */
package lbchs.view;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class 数据报
        extends 组件 {
    private static String ip;
    private static int port;
    private final UDPBuild udpBuild;
    private 接收到数据 $event_internal_接收到数据;

    public 数据报(String string, int n) {
        ip = string;
        port = n;
        this.udpBuild = UDPBuild.getUdpBuild();
        this.udpBuild.setUdpReceiveCallback(new UDPBuild.OnUDPReceiveCallbackBlock() {

            @Override
            public void OnParserComplete(DatagramPacket datagramPacket) {
                if (数据报.this.$event_internal_接收到数据 != null) {
                    数据报.this.$event_internal_接收到数据.接收到数据(datagramPacket.getData());
                }
            }
        });
    }

    public void 发送数据(byte[] byArray) {
        this.udpBuild.sendMessage(byArray);
    }

    public void 关闭连接() {
        this.udpBuild.disConnect();
    }

    public boolean 是否连接() {
        return this.udpBuild.isConnected();
    }

    public boolean 是否关闭() {
        return this.udpBuild.isClosed();
    }

    public int 取端口() {
        return this.udpBuild.getPort();
    }

    public int 取本地端口() {
        return this.udpBuild.getLocalPort();
    }

    public void 置接收到数据(接收到数据 接收到数据2) {
        this.$event_internal_接收到数据 = 接收到数据2;
    }

    @Override
    public void 初始化事件() {
        super.初始化事件();
    }

    public interface 接收到数据 {
        void 接收到数据(byte[] var1);
    }

    static class UDPBuild {
        private static final String TAG = "UDPBuild";
        private static final int POOL_SIZE = 5;
        private static final int BUFFER_LENGTH = 1024;
        private static UDPBuild udpBuild;
        private final byte[] receiveByte = new byte[1024];
        private boolean isThreadRunning = false;
        private final ExecutorService mThreadPool;
        private Thread clientThread;
        private DatagramSocket client;
        private DatagramPacket receivePacket;
        private OnUDPReceiveCallbackBlock udpReceiveCallback;

        private UDPBuild() {
            int n = Runtime.getRuntime().availableProcessors();
            this.mThreadPool = Executors.newFixedThreadPool(n * 5);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public static UDPBuild getUdpBuild() {
            if (udpBuild != null) return udpBuild;
            Class<UDPBuild> clazz = UDPBuild.class;
            synchronized (UDPBuild.class) {
                if (udpBuild != null) return udpBuild;
                udpBuild = new UDPBuild();
                // ** MonitorExit[var0] (shouldn't be in output)
                return udpBuild;
            }
        }

        public void startUDPSocket() {
            if (this.client != null) {
                return;
            }
            try {
                this.client = new DatagramSocket(port);
                if (this.receivePacket == null) {
                    this.receivePacket = new DatagramPacket(this.receiveByte, 1024);
                }
                this.startSocketThread();
            } catch (SocketException socketException) {
                socketException.printStackTrace();
            }
        }

        private void startSocketThread() {
            this.clientThread = new Thread(this::receiveMessage);
            this.isThreadRunning = true;
            this.clientThread.start();
        }

        private void receiveMessage() {
            while (this.isThreadRunning) {
                if (this.client != null) {
                    try {
                        this.client.receive(this.receivePacket);
                    } catch (IOException iOException) {
                        this.stopUDPSocket();
                        iOException.printStackTrace();
                        return;
                    }
                }
                if (this.receivePacket == null || this.receivePacket.getLength() == 0) continue;
                String string = new String(this.receivePacket.getData(), 0, this.receivePacket.getLength());
                if (this.udpReceiveCallback != null) {
                    this.udpReceiveCallback.OnParserComplete(this.receivePacket);
                }
                if (this.receivePacket == null) continue;
                this.receivePacket.setLength(1024);
            }
        }

        public void stopUDPSocket() {
            this.isThreadRunning = false;
            this.receivePacket = null;
            if (this.clientThread != null) {
                this.clientThread.interrupt();
            }
            if (this.client != null) {
                this.client.close();
                this.client = null;
            }
            this.removeCallback();
        }

        public void sendMessage(final byte[] byArray) {
            if (this.client == null) {
                this.startUDPSocket();
            }
            this.mThreadPool.execute(new Runnable() {

                public void run() {
                    try {
                        InetAddress inetAddress = InetAddress.getByName(ip);
                        DatagramPacket datagramPacket = new DatagramPacket(byArray, byArray.length, inetAddress, port);
                        client.send(datagramPacket);
                    } catch (IOException iOException) {
                        iOException.printStackTrace();
                    }
                }
            });
        }

        public void setUdpReceiveCallback(OnUDPReceiveCallbackBlock onUDPReceiveCallbackBlock) {
            this.udpReceiveCallback = onUDPReceiveCallbackBlock;
        }

        public void removeCallback() {
            this.udpReceiveCallback = null;
        }

        public void disConnect() {
            this.client.disconnect();
        }

        public boolean isConnected() {
            return this.client.isConnected();
        }

        public boolean isClosed() {
            return this.client.isClosed();
        }

        public int getPort() {
            return this.client.getPort();
        }

        public int getLocalPort() {
            return this.client.getLocalPort();
        }

        public interface OnUDPReceiveCallbackBlock {
            void OnParserComplete(DatagramPacket var1);
        }
    }
}

