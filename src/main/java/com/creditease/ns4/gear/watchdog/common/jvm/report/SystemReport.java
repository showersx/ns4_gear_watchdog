package com.creditease.ns4.gear.watchdog.common.jvm.report;import com.creditease.ns4.gear.watchdog.common.jvm.report.monitors.SystemMonitor;import java.util.Map;/** * @author 杨红岩 * @description 系统指标 * @date 2019/3/5 */public class SystemReport {    /**     * 采集系统信息     *     * @return a Map with the current process report     */    public static Map<String, Object> generate() {        return SystemMonitor.detect().toMap();    }}