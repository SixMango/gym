package com.lanjiao.gym.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.*;

@Slf4j
@Component
public class SerializeUtil {
    public static String object2String(Object obj) {
        String objBody = null;
        ByteArrayOutputStream baops = null;
        ObjectOutputStream oos = null;

        try {
            baops = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baops);
            oos.writeObject(obj);
            byte[] bytes = baops.toByteArray();
            objBody = toHexString(bytes);
        } catch (IOException e) {
            log.error("序列化发生错误"+e);
        } finally {
            try {
                if (oos != null)
                    oos.close();
                if (baops != null)
                    baops.close();
            } catch (IOException e) {
                log.error("关闭出现错误"+e);
            }
        }
        return objBody;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T getObjectFromString
            (String objBody, Class<T> clazz) {
        byte[] bytes = toByteArray(objBody);
        ObjectInputStream ois = null;
        T obj = null;
        try {
            ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
            obj = (T) ois.readObject();
        } catch (IOException e) {
            log.error("得到序列化对象时发生错误"+e);
        } catch (ClassNotFoundException e) {
            log.error("找不到相关类"+e);
        } finally {

            try {
                if (ois != null)
                    ois.close();
            } catch (IOException e) {
                log.error("关闭流出错"+e);
            }
        }
        return obj;
    }

    /**
     * 字节数组转成16进制表示格式的字符串
     *
     * @param byteArray 需要转换的字节数组
     * @return 16进制表示格式的字符串
     **/
    public static String toHexString(byte[] byteArray) {
        if (byteArray == null || byteArray.length < 1)
            throw new IllegalArgumentException("this byteArray must not be null or empty");

        final StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            if ((byteArray[i] & 0xff) < 0x10)//0~F前面不零
                hexString.append("0");
            hexString.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return hexString.toString().toLowerCase();
    }

    public static byte[] toByteArray(String hexString) {
        if (StringUtils.isEmpty(hexString))
            throw new IllegalArgumentException("this hexString must not be empty");

        hexString = hexString.toLowerCase();
        final byte[] byteArray = new byte[hexString.length() / 2];
        int k = 0;
        for (int i = 0; i < byteArray.length; i++) {//因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
            byteArray[i] = (byte) (high << 4 | low);
            k += 2;
        }
        return byteArray;
    }


}
