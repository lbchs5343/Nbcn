/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  java.io.File
 *  java.io.FileInputStream
 *  java.io.FileOutputStream
 *  java.io.IOException
 *  java.io.InputStream
 *  java.io.OutputStream
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.Enumeration
 *  java.util.zip.ZipEntry
 *  java.util.zip.ZipFile
 *  java.util.zip.ZipOutputStream
 */
package lbchs;

import android.os.Build;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.nio.file.Files;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class 压缩操作 {
    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean zip压缩(String string, String string2) {
        boolean bl;
        ZipOutputStream zipOutputStream = null;
        try {
            File file = new File(string2);
            File file2 = new File(string);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                zipOutputStream = new ZipOutputStream(Files.newOutputStream(file.toPath()));
            }
            if (file2.isFile()) {
                压缩操作.zipFileOrDirectory(zipOutputStream, file2);
            } else {
                File[] fileArray = file2.listFiles();
                if (fileArray != null) {
                    for (File value : fileArray) {
                        压缩操作.zipFileOrDirectory(zipOutputStream, value);
                    }
                }
            }
            bl = true;
        } catch (IOException iOException) {
            iOException.printStackTrace();
            bl = false;
        } finally {
            if (zipOutputStream != null) {
                try {
                    zipOutputStream.close();
                } catch (IOException iOException) {
                    iOException.printStackTrace();
                    bl = false;
                }
            }
        }
        return bl;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean zip解压(String 路径, String 输出路径) {
        boolean flag = false;
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(路径);
            Enumeration<? extends ZipEntry> ze = zipFile.entries();
            ZipEntry zipEntry;
            File dest = new File(输出路径);
            dest.mkdirs();
            while (ze.hasMoreElements()) {
                zipEntry = ze.nextElement();
                String entryName = zipEntry.getName();
                InputStream in;
                FileOutputStream out = null;
                try {
                    if (zipEntry.isDirectory()) {
                        String name = zipEntry.getName();
                        name = name.substring(0, name.length() - 1);
                        File f = new File(输出路径 + File.separator + name);
                        f.mkdirs();
                        flag = true;
                    } else {
                        int index = entryName.lastIndexOf("\\");
                        if (index != - 1) {
                            File df = new File(输出路径 + File.separator + entryName.substring(0, index));
                            df.mkdirs();
                        }
                        index = entryName.lastIndexOf("/");
                        if (index != - 1) {
                            File df = new File(输出路径 + File.separator + entryName.substring(0, index));
                            df.mkdirs();
                        }
                        File f = new File(输出路径 + File.separator + zipEntry.getName());

                        in = zipFile.getInputStream(zipEntry);
                        try {
                            out = new FileOutputStream(f);

                            byte[] by = new byte[1024];
                            int c;
                            while ((c = in.read(by)) != - 1) {
                                out.write(by, 0, c);
                            }
                            out.flush();
                            flag = true;
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            if (out != null) {
                                try {
                                    out.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    flag = false;
                }

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException ex) {
                    flag = false;
                }
            }
        }
        return flag;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void zipFileOrDirectory(ZipOutputStream out, File fileOrDirectory) throws IOException {
        FileInputStream in = null;
        try {
            if (!fileOrDirectory.isDirectory()) {
                byte[] buffer = new byte[4096];

                in = new FileInputStream(fileOrDirectory);

                ZipEntry entry = new ZipEntry(fileOrDirectory.getName());

                out.putNextEntry(entry);
                int bytes_read;
                while ((bytes_read = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytes_read);
                }
                out.closeEntry();
            } else {
                File[] entries = fileOrDirectory.listFiles();

                if (entries != null && entries.length == 0) {
                    ZipEntry zipEntry = new ZipEntry(fileOrDirectory.getName() + "/");
                    out.putNextEntry(zipEntry);
                    out.closeEntry();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
        }
    }

    public void 初始化事件() {
    }
}

