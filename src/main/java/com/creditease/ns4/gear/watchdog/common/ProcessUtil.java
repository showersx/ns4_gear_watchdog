package com.creditease.ns4.gear.watchdog.common;import com.creditease.ns.log.NsLog;import com.creditease.ns4.gear.watchdog.common.log.NsLogger;import java.io.BufferedReader;import java.io.IOException;import java.io.InputStreamReader;import java.lang.management.ManagementFactory;import java.lang.management.RuntimeMXBean;/** * @author 杨红岩 * @description 进程工具类 * @date 2019/1/21 */public class ProcessUtil {    private static final NsLog logger = NsLogger.getWatchdogLogger();    /**     * 系统类型名称     */    private static String osName = System.getProperty("os.name").toLowerCase();    /**     * 获取当前系统名称     */    public static String getOsName() {        return osName.toUpperCase();    }    /**     * 当前系统OS -> 是否为 linux     *     * @return 是 true     */    public static boolean isLinux() {        return osName.contains("linux");    }    /**     * 当前系统OS -> 是否为 windows     *     * @return 是 true     */    public static boolean isWindows() {        return osName.contains("windows");    }    /**     * 当前系统OS -> 是否为 MacOS     *     * @return 是 true     */    public static boolean isMacOS() {        return osName.contains("mac");    }    /**     * 获取当前进程ID     */    public static int getProcessID() {        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();        return Integer.valueOf(runtimeMXBean.getName().split("@")[0]);    }    public static int getProcess(String name) {        return getProcess(name, true);    }    /**     * 根据class名称获取进程PID     *     * @param name 名称     */    public static int getProcess(String name, boolean isJava) {        int pProcessID = getProcessID();        Runtime runtime = Runtime.getRuntime();        Process process = null;        BufferedReader br = null;        try {            if (isJava) {                process = runtime.exec("jps -l -m");            } else {                if (ProcessUtil.isLinux() || ProcessUtil.isMacOS()) {                    process = runtime.exec("ps -ef");                } else if (ProcessUtil.isWindows()) {                    process = runtime.exec("tasklist");                }            }            br = new BufferedReader(new InputStreamReader(process.getInputStream()));            String s = "";            int pid = -1, ppid = -1;            while ((s = br.readLine()) != null) {                if ("".equals(s.trim())) {                    continue;                }                if (s.contains(name)) {                    s = s.replaceAll(" +", " ");                    String[] pmsg = s.split(" ");                    try {                        if (isJava) {                            //得到的进程Id                            pid = Integer.valueOf(pmsg[0].trim());                            return pid;                        } else {                            //得到的进程Id                            pid = Integer.valueOf(pmsg[2].trim());                            //父进程ID                            ppid = Integer.valueOf(pmsg[3].trim());                            //父进程id和当前进程id相等，找到了该进程的正确信息                            if (ppid == pProcessID) {                                break;                            }                        }                    } catch (NumberFormatException e) {                        //忽略                    }                }            }            return pid;        } catch (Exception e) {            logger.error("获取进程ID错误:{}", e.getMessage());        } finally {            if (br != null) {                try {                    br.close();                    br = null;                } catch (IOException e) {                }            }        }        return -1;    }}