package com.datasource.demo.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * String工具类
 * 记录一些常用的针对字符串的通用处理函数
 *
 */
public class StringUtil {

    /**
     * 将数组转成用cutStr隔开的字符串
     *
     * @param strArray 字符串数组
     * @param cutStr   分割字符串
     * @return String
     */
    public static String ArrayToStr(String[] strArray, String cutStr) {
        String res = null;
        if (strArray == null || strArray.length == 0) {
            res = null;
        } else {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < strArray.length; i++) {
                sb.append(strArray[i]);
                if (i != strArray.length - 1) {
                    sb.append(cutStr);
                }
            }
            res = sb.toString();
        }

        return res;
    }

    /**
     * 替换字符串中的html标签
     *
     * @param str
     * @return String
     * Jul 8, 2009 2:10:34 PM
     */
    public static String htmlReplace(String str) {
        if (str != null && !"".equals(str)) {
            str = str.replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&#34;").replace("\'", "&#39;").trim();
        }
        return str;
    }

    /**
     * 替换换行符
     *
     * @param str 源字符串
     * @return String 将文本域换行符替换为html换行标签
     * Jul 23, 2010 2:51:24 PM
     */
    public static String brReplace(String str) {
        if (str != null && !"".equals(str)) {
            str = str.replace("\r\n", "<br/>").replace("\n", "<br/>");
        }
        return str;
    }

    /**
     * 替换字符串中的javascript标签
     *
     * @param str
     * @return String
     * Jul 8, 2009 2:11:09 PM
     */
    public static String jsReplace(String str) {
        if (str != null && !"".equals(str)) {
            str = str.replace("<script", "&lt;script").replace("</script>", "&lt;/script&gt;").trim();
        }
        return str;
    }

    /**
     * 比较字符串值是否相同(非绝对相同，宽松判断):比较会清理首尾空字符，并且空值及null判定为相同;
     *
     * @param compareStr 比较值，可为null
     * @param strs       多个字符串可为null
     * @return 均为null或所有值均相同返回true，否则返回false boolean
     * May 29, 2012 11:04:15 AM
     */
    public static boolean isEqualByLoose(String compareStr, String... strs) {
        boolean check = false;
        if (strs != null) {
            compareStr = compareStr == null ? "" : compareStr.trim();
            for (String str : strs) {
                str = str == null ? "" : str.trim();
                if (!(check = compareStr.equals(str))) {
                    break;
                }
            }

        } else {
            check = isEmptyTrim(compareStr);
        }

        return check;
    }

    /**
     * 验证字符串类型的参数是否为空
     *
     * @param strs
     * @return boolean  多个同时为空 true
     */
    public static boolean isEmpty(String... strs) {
        boolean b = false;
        if (strs == null) {
            b = true;
        } else {
            b = true;
            for (String str : strs) {
                b = b && (str == null || "".equals(str));
            }
        }
        return b;
    }

    /**
     * 验证字符串类型的参数是否不为空
     *
     * @param strs
     * @return boolean  多个同时不为空 true
     */
    public static boolean isNotEmpty(String... strs) {
        boolean b = false;
        if (strs == null) {
            b = false;
        } else {
            b = true;
            for (String str : strs) {
                b = b && !isEmpty(str);
            }
        }
        return b;
    }

    /**
     * 验证字符串类型的参数是否为空(去除首尾空格)
     *
     * @param strs 多个同时trim后为空
     * @return boolean
     */
    public static boolean isEmptyTrim(String... strs) {
        return isEmpty(trim(strs));
    }

    /**
     * 验证字符串类型的参数是否不为空(去除首尾空格)
     *
     * @param strs 多个同时trim后不为空
     * @return boolean
     */
    public static boolean isNotEmptyTrim(String... strs) {
        return isNotEmpty(trim(strs));
    }

    /**
     * 字符串数组值trim
     *
     * @param strs
     * @return String[] 返回trim后的字符串数组
     * 2013-12-23 下午4:26:31
     */
    public static String[] trim(String... strs) {
        if (strs != null && strs.length > 0) {
            for (int i = 0; i < strs.length; i++) {
                if (strs[i] != null) {
                    strs[i] = strs[i].trim();
                }
            }
        }
        return strs;
    }

    /**
     * 去掉html标签
     *
     * @param source 源
     * @return String
     * May 10, 2012 1:21:38 PM
     */
    public static String removeHTML(String source) {

        if (!isEmpty(source)) {
            // 过滤所有html标签
            source = source.replaceAll("<.*?>", "");
        }
        return source;
    }

    /**
     * 去除所有的html标签
     *
     * @param input
     * @return
     */
    public static String htmlReplaceAll(String input) {
        if (input == null || input.trim().equals("")) {
            return "";
        }
        // 去掉所有html元素,
        String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");
        str = str.replaceAll("/>|<", "");
        return str;
    }

    /**
     * 以句比文，比较语句重复比例
     *
     * @param source         源数据
     * @param compareStr     比较数据
     * @param mark           是否标记
     * @param separatorArray 分离符，注意特殊字符的转义，如{ '.', '?' }
     * @return Object[] [0]语句重复占自身总句比例 [1]标记源内容 (mark==true时)[2]标记比较内容 (mark==true时)
     * Dec 21, 2012 3:13:57 PM
     */
    public static Object[] compareForSentence(String source, String compareStr, boolean mark, char... separatorArray) {

        // 语句重复比例
        float sameRate = 0;

        if (!StringUtil.isEmptyTrim(source) && !StringUtil.isEmptyTrim(compareStr)) {
            // 拆句子
            List<String> sentenceArray;
            if (separatorArray != null && separatorArray.length > 0) {
                sentenceArray = separator(compareStr, true, separatorArray);
            } else {
                sentenceArray = separator(compareStr, true, ' ', '　', '、', ',', '，', '.', '。', '?', '？', '!', '！', ';', '；', ':', '：', '\'', '"', '“', '”');
            }
            if (sentenceArray != null) {

                // 总句数
                int sumCount = sentenceArray.size();
                if (sumCount > 0) {

                    // 找到相同句数
                    int sameCount = 0;
                    // 是否连续
                    boolean continuation = false;
                    // 多样式
                    int styleIndex = 0;
                    String className = "same_sentence";
                    for (String sentence : sentenceArray) {
                        if (source.indexOf(sentence) > -1) {
                            // 找到相同
                            sameCount++;
                            if (mark) {
                                // 标记源字符串中相同语句
                                source = source.replace(sentence, "<span class=\"" + className + "_" + styleIndex + "\">" + sentence + "</span>");
                                // 标记比较字符串中相同语句
                                compareStr = compareStr.replace(sentence, "<span class=\"" + className + "_" + styleIndex + "\">" + sentence + "</span>");

                                if (!continuation) {
                                    // 非连续相同，5个样式区分标记，连续相同则用相同样式
                                    styleIndex = styleIndex < 5 ? ++styleIndex : 0;
                                }

                            }
                            continuation = true;
                        } else {
                            continuation = false;
                        }
                    }
                    // 计算比例
                    sameRate = sameCount / (float) sumCount;
                }
            }
        }

        return new Object[]{sameRate, source, compareStr};
    }

    /**
     * 字符串拆分器
     *
     * @param sourceStr      待拆分源字符串
     * @param noRepeat       是否去重
     * @param separatorArray 分离符，以每个字符进行分割
     * @return List 拆出的语句集
     * Dec 21, 2012 3:26:55 PM
     */
    public static List<String> separator(String sourceStr, boolean noRepeat, char... separatorArray) {

        List<String> strList = null;

        if (separatorArray != null && separatorArray.length > 0 && !StringUtil.isEmptyTrim(sourceStr)) {
            // 拼接分离符号
            String separatorStr = "";
            for (char joinChar : separatorArray) {
                separatorStr += joinChar;
            }
            // 拆分临时字符串数组
            String[] splitArrayTemp = sourceStr.split("[" + separatorStr + "]");
            if (splitArrayTemp != null && splitArrayTemp.length > 0) {
                strList = new ArrayList<String>();
                // 存储拆分信息
                for (String str : splitArrayTemp) {
                    // 排除重复语句
                    if (!StringUtil.isEmptyTrim(str) && (!noRepeat || !strList.contains(str))) {
                        strList.add(str);
                    }
                }
            }

        }

        return strList;
    }

    /**
     * 是否为url格式
     *
     * @param urlStr 验证字符串
     * @return boolean
     * Sep 17, 2013 11:20:04 AM
     */
    public static boolean isURL(String urlStr) {
        boolean res;
        if (res = !StringUtil.isEmptyTrim(urlStr)) {
            String regEx =
                    "^((https|http|ftp|rtsp|mms)?://)" + "+(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" + "|" + "([0-9a-z_!~*'()-]+\\.)*"
                            + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." + "[a-z]{2,6})" + "(:[0-9]{1,4})?" + "((/?)|" + "(/[\\s0-9a-zA-Z_!~*'().;?:@&=+$,%#-]+)+/?)$";
            res = urlStr.matches(regEx);
        }
        return res;
    }

    /**
     * 特殊字符过滤
     *
     * @param str 处理语句
     * @return String 特殊字符被替换或删除
     * 2013-12-18 下午9:05:26
     */
    public static String filterStr(String str) {
        if (str == null) {
            return null;
        }
        str = str.replace(";", "");
        str = str.replace("&", "");
        str = str.replace("<", "");
        str = str.replace(">", "");
        str = str.replace("'", "");
        str = str.replace("--", "");
        str = str.replace("/", "");
        str = str.replace("%", "");
        return str;
    }

    /**
     * @param src
     * @return
     * @author: fanzd
     * Feb 8, 2014 1:29:27 PM
     * 字串全角转半角的函数(DBC case)
     * 全角空格为12288，半角空格为32
     * 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
     */
    public static char toDbcCase(char src) {
        if (src == 12288) {
            src = (char) 32;
        } else if (src > 65280 && src < 65375) {
            src = (char) (src - 65248);
        }
        return src;
    }

    /**
     * 字符串半角转全角的函数(SBC case)
     *
     * @param src
     * @return String
     * @author: fanzd
     * Feb 10, 2014 10:59:27 AM
     */
    public static String toSBCCaseStr(String src) {
        char c[] = src.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ' ') {
                c[i] = '\u3000';
            } else if (c[i] < '\177') {
                c[i] = (char) (c[i] + 65248);
            }
        }
        return new String(c);
    }

    /**
     * 将字符串转成unicode
     *
     * @param str 待转字符串
     * @return unicode字符串
     */
    public static String unicode(String str) {
        if (isNotEmptyTrim(str)) {
            String tmp;
            char c;
            int i, j;
            StringBuffer sb = new StringBuffer();
            for (i = 0; i < str.length(); i++) {
                sb.append("\\u");
                c = str.charAt(i);

                j = (c >>> 8); //取出高8位
                tmp = Integer.toHexString(j);
                if (tmp.length() == 1) {
                    sb.append("0");
                }
                sb.append(tmp);

                j = (c & 0xFF); //取出低8位
                tmp = Integer.toHexString(j);
                if (tmp.length() == 1) {
                    sb.append("0");
                }
                sb.append(tmp);

            }
            str = sb.toString();
        }
        return str;
    }

    /**
     * 将unicode转为普通字符串
     *
     * @param str 待转字符串应全部为unicode
     * @return 普通字符串
     */
    public static String unUnicode(String str) {
        if (isNotEmptyTrim(str) && str.contains("\\u")) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < str.length() - 6; i += 6) {
                String strTemp = str.substring(i, i + 6);
                String value = strTemp.substring(2);
                int c = 0;
                for (int j = 0; j < value.length(); j++) {
                    char tempChar = value.charAt(j);
                    int t = 0;
                    switch (tempChar) {
                        case 'a':
                            t = 10;
                            break;
                        case 'b':
                            t = 11;
                            break;
                        case 'c':
                            t = 12;
                            break;
                        case 'd':
                            t = 13;
                            break;
                        case 'e':
                            t = 14;
                            break;
                        case 'f':
                            t = 15;
                            break;
                        default:
                            t = tempChar - 48;
                            break;
                    }

                    c += t * ((int) Math.pow(16, (value.length() - j - 1)));
                }
                sb.append((char) c);
            }
            str = sb.toString();
        }
        return str;
    }

    /**
     * 检测是否有emoji字符
     *
     * @param source
     * @return boolean true包含 false不包含
     * 2014-5-27 上午10:45:57
     */
    public static boolean hasEmoji(String source) {
        boolean hasEmoj = false;
        if (isNotEmptyTrim(source)) {
            int len = source.length();
            for (int i = 0; i < len; i++) {
                char codePoint = source.charAt(i);
                if (hasEmoj = isEmojiCharacter(codePoint)) {
                    break;
                }
            }
        }
        return hasEmoj;
    }

    /**
     * 是否是emoji字符
     *
     * @param codePoint
     * @return boolean
     * 2014-5-27 上午11:26:49
     */
    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * 清除emoji 或者 其他非文字类型的字符
     *
     * @param source
     * @return String
     * 2014-5-27 上午11:25:25
     */
    public static String clearEmoji(String source) {
        if (hasEmoji(source)) {
            int len = source.length();
            StringBuilder buf = new StringBuilder(source.length());
            for (int i = 0; i < len; i++) {
                char codePoint = source.charAt(i);
                if (isEmojiCharacter(codePoint)) {
                    buf.append(codePoint);
                }
            }
            source = buf.toString();
        }
        return source;
    }

    /**
     * 按字节截取字符串，遇到半个中文舍弃
     *
     * @param str        源字符串
     * @param byteLength 截取字节长度
     * @param charset    编码UTF-8中文3字节，GBK中文2字节
     * @return
     * @throws UnsupportedEncodingException String
     * 2016-10-20 下午1:21:02
     */
    public static String substring(String str, int byteLength, String charset) throws UnsupportedEncodingException {
        String sub = str;
        if (isNotEmptyTrim(str)) {
            byte[] bytes = str.getBytes(charset);
            if (str.length() > byteLength)
                sub = str.substring(0, byteLength);
            while (bytes.length > str.length() && sub.getBytes(charset).length > byteLength) {
                sub = sub.substring(0, sub.length() - 1);
            }
        }
        return sub;
    }
}
