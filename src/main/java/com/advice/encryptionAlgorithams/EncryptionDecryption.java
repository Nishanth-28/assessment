package com.advice.encryptionAlgorithams;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import org.springframework.stereotype.Component;

@Component
public class EncryptionDecryption {

    private static final Logger logger = Logger.getLogger(EncryptionDecryption.class);
    private static final String KEY = "chakra61CloudJ16"; // 128 bit key
    private static final String INITVECTOR = "RandomInitVector"; // 16 bytes IV

    private static boolean encryptionEnable;

    public static void setEncryptionEnable(boolean encryptionEnable) {
        EncryptionDecryption.encryptionEnable = encryptionEnable;
    }

    /*
	 * This method is responsible for encrypt the object
     */
    public void encryptObject(Object object) throws InvalidKeyException, IllegalBlockSizeException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, BadPaddingException, IntrospectionException, IllegalAccessException, InvalidAlgorithmParameterException, InvocationTargetException, Exception {
        logger.info("Start runnnign encryptObject method");
        if (!encryptionEnable) {
            return;
        }
        try {
            BeanInfo info = Introspector.getBeanInfo(object.getClass(), Object.class);
            PropertyDescriptor[] props = info.getPropertyDescriptors();
            for (PropertyDescriptor pd : props) {
                String name = pd.getName();
                String type = pd.getPropertyType().getSimpleName();
                Object value = pd.getReadMethod().invoke(object);
                Method setter = pd.getWriteMethod();

                if (value != null) {
                    if (type.equals("Map")) {
                        Map<String, String> mapValue = (Map<String, String>) value;
                        Map<String, String> storedMap = new HashMap<>();

                        for (String keys : mapValue.keySet()) {
                            String values = mapValue.get(keys);
                            String encryptedValue = encrypt(values);
                            String encryptedKey = encrypt(keys);
                            storedMap.put(encryptedKey, encryptedValue);
                        }
                        setter.invoke(object, new Object[]{storedMap});
                    } else {

                        String Output = value.toString();
                        byte[] byteString = Output.getBytes();
                        IvParameterSpec iv = new IvParameterSpec(INITVECTOR.getBytes("UTF-8"));
                        SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");
                        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
                        byte[] encrypted = cipher.doFinal(byteString);
                        PropertySetter propertySetter = new PropertySetter(object);
                        propertySetter.setProperty(name, Base64.encodeBase64String(encrypted));

                    }
                }

            }
            logger.info("End of EncryptionDecryption encryptObject method");
        } catch (BadPaddingException e) {
            logger.error("BadPaddingException in EncryptionDecryption of encryptObject method", e);
            throw e;
        } catch (IntrospectionException ie) {
            logger.error("IntrospectionException in EncryptionDecryption of encryptObject method", ie);
            throw ie;
        } catch (InvalidAlgorithmParameterException e) {
            logger.error("InvalidAlgorithmParameterException in EncryptionDecryption of encryptObject method", e);
            throw e;
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException in EncryptionDecryption of encryptObject method", e);
            throw e;
        } catch (UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException in EncryptionDecryption of encryptObject method", e);
            throw e;
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException in EncryptionDecryption of encryptObject method", e);
            throw e;
        } catch (IllegalArgumentException e) {
            logger.error("IllegalArgumentException in EncryptionDecryption of encryptObject method", e);
            throw e;
        } catch (SecurityException e) {
            logger.error("SecurityException in EncryptionDecryption of encryptObject method", e);
            throw e;
        } catch (Exception e) {
            logger.error("Exception in EncryptionDecryption of encryptObject method", e);
            throw e;
        }

    }

    public void decryptObject(Object encryptedObject) throws InvalidKeyException, IllegalBlockSizeException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, BadPaddingException, IntrospectionException, IllegalAccessException, InvalidAlgorithmParameterException, InvocationTargetException, Exception {
        logger.info("Going to run  EncryptionDecryption decryptObject method");
        if (!encryptionEnable) {
            return;
        }
        try {
            BeanInfo info = Introspector.getBeanInfo(encryptedObject.getClass(), Object.class);
            PropertyDescriptor[] props = info.getPropertyDescriptors();

            for (PropertyDescriptor pd : props) {
                String name = pd.getName();
                String type = pd.getPropertyType().getSimpleName();
                Object value = pd.getReadMethod().invoke(encryptedObject);
                Method setter = pd.getWriteMethod();

                if (value != null) {

                    if (type.equals("Map")) {
                        Map<String, String> mapValue = (Map<String, String>) value;
                        Map<String, String> storedMap = new HashMap<>();

                        for (String keys : mapValue.keySet()) {

                            String values = mapValue.get(keys);
                            if (values != null) {
                                String encryptedValue = decrypt(values);
                                String encryptedKey = decrypt(keys);
                                storedMap.put(encryptedKey, encryptedValue);
                            }
                        }

                        setter.invoke(encryptedObject, new Object[]{storedMap});

                    } else {

                        String output = value.toString();

                        IvParameterSpec iv = new IvParameterSpec(INITVECTOR.getBytes("UTF-8"));
                        SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");

                        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
                        byte[] original = cipher.doFinal(Base64.decodeBase64(output));

                        PropertySetter propertySetter = new PropertySetter(encryptedObject);
                        propertySetter.setProperty(name, new String(original, "UTF-8"));
                    }

                }
            }

        } catch (BadPaddingException e) {
            logger.error("BadPaddingException in EncryptionDecryption of decryptObject method", e);
            throw e;
        } catch (IntrospectionException ie) {
            logger.error("IntrospectionException in EncryptionDecryption of decryptObject method", ie);
            throw ie;
        } catch (InvalidAlgorithmParameterException e) {
            logger.error("InvalidAlgorithmParameterException in EncryptionDecryption of decryptObject method", e);
            throw e;
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException in EncryptionDecryption of decryptObject method", e);
            throw e;
        } catch (UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException in EncryptionDecryption of decryptObject method", e);
            throw e;
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException in EncryptionDecryption of decryptObject method", e);
            throw e;
        } catch (IllegalArgumentException e) {
            logger.error("IllegalArgumentException in EncryptionDecryption of decryptObject method", e);
            throw e;
        } catch (SecurityException e) {
            logger.error("SecurityException in EncryptionDecryption of decryptObject method", e);
            throw e;
        } catch (Exception e) {
            logger.error("Exception in EncryptionDecryption of decryptObject method", e);
            throw e;
        }

        logger.info("End of EncryptionDecryption decryptObject method");
    }

    public String encrypt(String value) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, Exception {
        if (!encryptionEnable) {
            return value;
        }
        try {
            logger.info("Going to run  EncryptionDecryption encrypt method");
            IvParameterSpec iv = new IvParameterSpec(INITVECTOR.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            logger.info("End of EncryptionDecryption encrypt method");
            return Base64.encodeBase64String(encrypted);
        } catch (UnsupportedEncodingException e) {
            logger.error("IllegalArgumentException in EncryptionDecryption of decryptObject method", e);
            throw e;
        } catch (NoSuchAlgorithmException e) {
            logger.error("IllegalArgumentException in EncryptionDecryption of decryptObject method", e);
            throw e;
        } catch (NoSuchPaddingException e) {
            logger.error("IllegalArgumentException in EncryptionDecryption of decryptObject method", e);
            throw e;
        } catch (InvalidKeyException e) {
            logger.error("IllegalArgumentException in EncryptionDecryption of decryptObject method", e);
            throw e;
        } catch (InvalidAlgorithmParameterException e) {
            logger.error("IllegalArgumentException in EncryptionDecryption of decryptObject method", e);
            throw e;
        } catch (IllegalBlockSizeException e) {
            logger.error("IllegalArgumentException in EncryptionDecryption of decryptObject method", e);
            throw e;
        } catch (BadPaddingException e) {
            logger.error("IllegalArgumentException in EncryptionDecryption of decryptObject method", e);
            throw e;
        } catch (Exception e) {
            logger.error("IllegalArgumentException in EncryptionDecryption of decryptObject method", e);
            throw e;
        }

    }

    public String decrypt(String encrypted) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, Exception {
        logger.info("Going to run  EncryptionDecryption decrypt method");
        if (!encryptionEnable) {
            return encrypted;
        }
        try {

            IvParameterSpec iv = new IvParameterSpec(INITVECTOR.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));
            return new String(original, "UTF-8");

        } catch (BadPaddingException e) {
            logger.error("BadPaddingException in EncryptionDecryption of encryptObject method", e);
            throw e;
        } catch (InvalidAlgorithmParameterException e) {
            logger.error("InvalidAlgorithmParameterException in EncryptionDecryption of encryptObject method", e);
            throw e;
        } catch (UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException in EncryptionDecryption of encryptObject method", e);
            throw e;
        } catch (IllegalArgumentException e) {
            logger.error("IllegalArgumentException in EncryptionDecryption of encryptObject method", e);
            throw e;
        } catch (SecurityException e) {
            logger.error("SecurityException in EncryptionDecryption of encryptObject method", e);
            throw e;
        } catch (Exception e) {
            logger.error("Exception in EncryptionDecryption of encryptObject method", e);
            throw e;
        }

    }

}
