package io.renren.common.utils;

public class SqlUtils {

    /**
     * 生成大于等于某时间的sql， eg: date_format(startTime, '%Y-%m-%d %H:%i') >= value
     * @param column 数据库字段名称
     * @param timeVal 具体时间
     * @param timeFormat 时间格式
     * @return sql语句
     */
    public static String getGtTimeSql(String column, Object timeVal, String timeFormat) {
        return "date_format (" + column + ",'" + timeFormat +
                "') >= '" + timeVal + "'";
    }

    /**
     * 生成小于等于某时间的sql， eg: date_format(startTime, '%Y-%m-%d %H:%i') <= value
     * @param column 数据库字段名称
     * @param timeVal 具体时间
     * @param timeFormat 时间格式
     * @return sql语句
     */
    public static String getLtTimeSql(String column, Object timeVal, String timeFormat) {
        return "date_format (" + column + ",'" + timeFormat +
                "') <= '" + timeVal + "'";
    }

    /**
     * 生成等于某时间的sql， eg: date_format(startTime, '%Y-%m-%d %H:%i') == value
     * @param column 数据库字段名称
     * @param timeVal 具体时间
     * @param timeFormat 时间格式
     * @return sql语句
     */
    public static String getEtTimeSql(String column, Object timeVal, String timeFormat) {
        return "date_format (" + column + ",'" + timeFormat +
                "') = '" + timeVal + "'";
    }
}
