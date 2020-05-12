package com.yijv.util;


import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.File;

/**
 * @Author Administrator
 * @Date 2020/5/12 11:10
 **/
public class YijvUtil {

    public static void main(String[] args) {
        //1.连接七牛云
        //AK,SK是密匙
        final String AK = "KugjUcqJGb4HOSW-a4zqp88f7yjnd7sNlFDVBv1s";
        final String SK = "gB6u1wVCfe2CPMpmvjmQiz4JTYtsGHsHAhfauino";
        final String BUCKET = "czl666";
        Auth auth = Auth.create(AK,SK);
        String token = auth.uploadToken(BUCKET);
        Configuration cfg = new Configuration(Zone.zone1());

        //2.进行图片上传
        UploadManager uploadManager = new UploadManager(cfg);
        String Local = "C:\\Users\\Administrator\\Desktop\\meinvjiejie.jpg";
        File file = new File(Local);
        String upload = "meinv1.jpg";
        try {
            uploadManager.put(file,upload,token);
            System.out.println("上传成功");
        } catch (QiniuException e) {
            e.printStackTrace();
        }
    }

}
