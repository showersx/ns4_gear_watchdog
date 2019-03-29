package com.creditease.ns4.gear.watchdog.common;import com.creditease.ns.log.NsLog;import com.creditease.ns4.gear.watchdog.common.constant.ServerVendor;import com.creditease.ns4.gear.watchdog.common.log.NsLogger;/** * @author 杨红岩 * @description 命令行 * @date 2019/1/15 */public class CommandLine {    private static final NsLog logger = NsLogger.getWatchdogPluginLogger();    /**     * ping 端口     */    private int pingPort;    public int getPingPort() {        return pingPort;    }    public void setPingPort(int pingPort) {        this.pingPort = pingPort;    }    /**     * 转换命令行     *     * @param args 命令行参数参数     */    public static CommandLine parse(Object[] args) {        CommandLine commandLine = new CommandLine();        try {            if (args == null) {                return commandLine;            }            int len = args.length;            for (int i = 0; i < len; i++) {                Object name = args[i];                if (name instanceof ServerVendor) {                } else {                    String nameStr = String.valueOf(name);                    if ("-ping".equals(nameStr)) {                        //ping端口                        i++;                        try {                            String params = String.valueOf(args[i]).trim();                            logger.info("command line ===> " + nameStr + " -> " + params);                            commandLine.setPingPort(Integer.valueOf(params));                        } catch (NumberFormatException e) {                            throw new RuntimeException(nameStr + " ping端口参数错误");                        } catch (RuntimeException e) {                            throw e;                        }                    }                }            }        } catch (RuntimeException re) {            logger.error("command line ===> 解析命令错误：" + re.getMessage());            System.exit(1);        }        return commandLine;    }    @Override    public String toString() {        return "CommandLine{" +                "pingPort=" + pingPort +                '}';    }}